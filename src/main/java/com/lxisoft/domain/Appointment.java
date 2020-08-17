package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.lxisoft.domain.enumeration.AppointmentVerifiedStatus;

/**
 * A Appointment.
 */
@Entity
@Table(name = "appointment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false)
    private AppointmentVerifiedStatus appointmentStatus;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "appointments", allowSetters = true)
    private TimeSlot timeSlot;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "appointments", allowSetters = true)
    private Employee employee;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "appointments", allowSetters = true)
    private ProvidedService providedService;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "appointments", allowSetters = true)
    private Firm firm;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "appointments", allowSetters = true)
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Appointment date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppointmentVerifiedStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public Appointment appointmentStatus(AppointmentVerifiedStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
        return this;
    }

    public void setAppointmentStatus(AppointmentVerifiedStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Appointment timeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
        return this;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Appointment employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ProvidedService getProvidedService() {
        return providedService;
    }

    public Appointment providedService(ProvidedService providedService) {
        this.providedService = providedService;
        return this;
    }

    public void setProvidedService(ProvidedService providedService) {
        this.providedService = providedService;
    }

    public Firm getFirm() {
        return firm;
    }

    public Appointment firm(Firm firm) {
        this.firm = firm;
        return this;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Appointment userExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
        return this;
    }

    public void setUserExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appointment)) {
            return false;
        }
        return id != null && id.equals(((Appointment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Appointment{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", appointmentStatus='" + getAppointmentStatus() + "'" +
            "}";
    }
}
