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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQueries({
	@NamedQuery(name = "customer.count",
				query = "Select count(c) from Customer c ")	
})
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    @Size(max = 20)
    private String mobileNum;

    @Size(max = 20)
    private String homeNum;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date birthDate;
    
    private Boolean isBanned;
    
    @OneToMany(mappedBy="customer")
    private Collection<Reservation> reservations;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
	
	public String getFirstName() {
        return this.firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getMobileNum() {
        return this.mobileNum;
    }

	public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

	public String getHomeNum() {
        return this.homeNum;
    }

	public void setHomeNum(String homeNum) {
        this.homeNum = homeNum;
    }

	public Date getBirthDate() {
        return this.birthDate;
    }

	public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
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
