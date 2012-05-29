package com.oaka.crm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQueries({
	@NamedQuery(name = "training.count",
				query = "Select count(t) from Training t ")	
})
public class Training {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;
	
	@NotNull
    @Size(max = 40)
	private String type;
	
	@NotNull
	@Size(max = 40)
	private String instructor;
	
	@NotNull
	private Long capacity;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date timeScheduled;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
	private Date timeToAllowBooking;
	
	@OneToMany(mappedBy="training")
	private Collection<Reservation> reservations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public Date getTimeScheduled() {
		return timeScheduled;
	}

	public void setTimeScheduled(Date timeScheduled) {
		this.timeScheduled = timeScheduled;
	} 
	
	public Date getTimeToAllowBooking() {
		return timeToAllowBooking;
	}

	public void setTimeToAllowBooking(Date timeToAllowBooking) {
		this.timeToAllowBooking = timeToAllowBooking;
	}
	

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
