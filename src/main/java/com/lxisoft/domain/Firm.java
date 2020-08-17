package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Firm.
 */
@Entity
@Table(name = "firm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Firm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "firm")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProvidedService> providedServices = new HashSet<>();

    @OneToMany(mappedBy = "firm")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "firm")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotNull
    @JoinTable(name = "firm_timeslot",
               joinColumns = @JoinColumn(name = "firm_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "timeslot_id", referencedColumnName = "id"))
    private Set<TimeSlot> timeslots = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "firms", allowSetters = true)
    private Category category;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "firms", allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Firm name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Firm description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public Firm image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Firm imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Address getAddress() {
        return address;
    }

    public Firm address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<ProvidedService> getProvidedServices() {
        return providedServices;
    }

    public Firm providedServices(Set<ProvidedService> providedServices) {
        this.providedServices = providedServices;
        return this;
    }

    public Firm addProvidedService(ProvidedService providedService) {
        this.providedServices.add(providedService);
        providedService.setFirm(this);
        return this;
    }

    public Firm removeProvidedService(ProvidedService providedService) {
        this.providedServices.remove(providedService);
        providedService.setFirm(null);
        return this;
    }

    public void setProvidedServices(Set<ProvidedService> providedServices) {
        this.providedServices = providedServices;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public Firm appointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    public Firm addAppointments(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setFirm(this);
        return this;
    }

    public Firm removeAppointments(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setFirm(null);
        return this;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Firm employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Firm addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setFirm(this);
        return this;
    }

    public Firm removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setFirm(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public Firm timeslots(Set<TimeSlot> timeSlots) {
        this.timeslots = timeSlots;
        return this;
    }

    public Firm addTimeslot(TimeSlot timeSlot) {
        this.timeslots.add(timeSlot);
        timeSlot.getFirms().add(this);
        return this;
    }

    public Firm removeTimeslot(TimeSlot timeSlot) {
        this.timeslots.remove(timeSlot);
        timeSlot.getFirms().remove(this);
        return this;
    }

    public void setTimeslots(Set<TimeSlot> timeSlots) {
        this.timeslots = timeSlots;
    }

    public Category getCategory() {
        return category;
    }

    public Firm category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Firm customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Firm)) {
            return false;
        }
        return id != null && id.equals(((Firm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Firm{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
