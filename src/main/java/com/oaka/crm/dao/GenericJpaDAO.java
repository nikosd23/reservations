package com.oaka.crm.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericJpaDAO<T, PK extends Serializable> implements GenericDAO<T, PK> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<? extends T> persistentClass;
	private Method idGetter;

	// --- Constructors --- //

	public GenericJpaDAO(Class<? extends T> persistentClass) {
		this.persistentClass = persistentClass;
		initId();
	}

	/**
	 * Constructor that create a GenericJpaDAO for the provided persistent class name.
	 *
	 * @param persistentClassName
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public GenericJpaDAO(String persistentClassName) throws ClassNotFoundException {
		this.persistentClass = (Class<T>) Class.forName(persistentClassName);
		initId();
	}

	@SuppressWarnings("unchecked")
	public GenericJpaDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		initId();
	}

	// --- Support methods --- //

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null)
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		return entityManager;
	}

	protected void initId() {
		for (Method method : persistentClass.getMethods()) {
			if (method.isAnnotationPresent(Id.class) || method.isAnnotationPresent(EmbeddedId.class)) {
				idGetter = method;
				return;
			}
		}
		for (Field field : persistentClass.getDeclaredFields() /* getFields()? */) {
			if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
				try {
					idGetter = persistentClass.getMethod(getPropertyGetter(field.getName()));
					return;
				} catch (SecurityException e) {
					throw new RuntimeException("Could not find persistent Id accessor", e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException("Could not find persistent Id accessor", e);
				}
			}
		}
	}

	protected String getPropertyGetter(String versionProperty) {
		return "get" + Character.toUpperCase(versionProperty.charAt(0)) + versionProperty.substring(1);
	}


	// --- Inherited methods --- //

	@SuppressWarnings("unchecked")
	public PK create(T entity) {
		getEntityManager().persist(entity);
		flush();
		try {
			return (PK) idGetter.invoke(entity);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Error getting persistent Id", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error getting persistent Id", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Error getting persistent Id", e);
		}
	}

	public T read(PK id) {
		return getEntityManager().getReference(persistentClass, id);
	}

	public T find(PK id) {
		return getEntityManager().find(persistentClass, id);
	}

	public T find(PK id, LockModeType lockMode) {
		return getEntityManager().find(persistentClass, id, lockMode);
	}

	public void update(T entity) {
		merge(entity);
	}

	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	public void/*T*/ delete(T entity) {
		getEntityManager().remove(entity);
		//return entity;
	}

	public void/*PK*/ deleteById(PK id) {
		delete(read(id));
		//return id;
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	public void clear() {
		getEntityManager().clear();
	}

	public void evict(T entity) {
		getEntityManager().detach(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return getEntityManager().createQuery("from " + persistentClass.getSimpleName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listByNamedQuery(String namedQuery, Map<String, Object> queryParameters) {
		Query q = getEntityManager().createNamedQuery(namedQuery);
		for (String key : queryParameters.keySet()) {
			q.setParameter(key, queryParameters.get(key));
		}
		return q.getResultList();
	}


	@SuppressWarnings("unchecked")
	/**
	 * Returns a single result of a named query.
	 */
	public T findByNamedQuery(String namedQuery, Map<String, Object> queryParameters) {

		Query q = getEntityManager().createNamedQuery(namedQuery);
		for (String key : queryParameters.keySet()) {
			q.setParameter(key, queryParameters.get(key));
		}
		try {
			return (T) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// --- JPA DAO methods --- //

	/**
	 * Lists entities by namedQuery
	 *
	 * @param namedQuery
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByNamedQuery(String namedQuery) {
		return getEntityManager().createNamedQuery(namedQuery).getResultList();
	}

	/**
	 * Lists entities by query
	 *
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByQuery(String query) {
		return getEntityManager().createQuery(query).getResultList();
	}

	/**
	 * Lists entities by query with additional parameters
	 *
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByQuery(String query, Map<String, Object> queryParameters) {
		Query q = getEntityManager().createQuery(query);
		for (String key : queryParameters.keySet()) {
			q.setParameter(key, queryParameters.get(key));
		}
		return q.getResultList();
	}

	/**
	 * Batch insert
	 *
	 * @param entities  to be saved
	 * @param batchSize (To use the batch processing feature, first set hibernate.jdbc.batch_size as this parameter)
	 */
	public void batchInsert(List<T> entities, int batchSize) {
		int cnt = 0;
		for (T entity : entities) {
			getEntityManager().persist(entity);
			cnt++;
			if (cnt % batchSize /*  Same as the JDBC batch size */ == 0) {
				flush(); // Flush a batch of inserts and release memory
				clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String finder, Object... args) {
		Query query = getEntityManager().createNamedQuery(getQueryName(finder));
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return (List<T>) query.getResultList();
	}

	protected String getQueryName(String query) {
		return persistentClass.getSimpleName() + "." + query;
	}

	@Override
	public List<T> list(int first, int count) {
		return getEntityManager().createQuery("from " + persistentClass.getSimpleName()).setFirstResult(first).setMaxResults(count).getResultList();
	}
}
