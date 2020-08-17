package com.lxisoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 11)
    @Pattern(regexp = "^\\d+$")
    @Column(name = "phone", length = 11, unique = true)
    private String phone;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "userExtra")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Appointment> appointments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public UserExtra phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public UserExtra address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public UserExtra appointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    public UserExtra addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setUserExtra(this);
        return this;
    }

    public UserExtra removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setUserExtra(null);
        return this;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtra)) {
            return false;
        }
        return id != null && id.equals(((UserExtra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
