package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 20)
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Lob
    @Column(name = "employe_image")
    private byte[] employeImage;

    @Column(name = "employe_image_content_type")
    private String employeImageContentType;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(max = 11)
    @Pattern(regexp = "^\\d+$")
    @Column(name = "phone", length = 11, nullable = false, unique = true)
    private String phone;

    @Size(max = 5)
    @Column(name = "registerd_employee_id", length = 5, unique = true)
    private String registerdEmployeeId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "employees", allowSetters = true)
    private Firm firm;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getEmployeImage() {
        return employeImage;
    }

    public Employee employeImage(byte[] employeImage) {
        this.employeImage = employeImage;
        return this;
    }

    public void setEmployeImage(byte[] employeImage) {
        this.employeImage = employeImage;
    }

    public String getEmployeImageContentType() {
        return employeImageContentType;
    }

    public Employee employeImageContentType(String employeImageContentType) {
        this.employeImageContentType = employeImageContentType;
        return this;
    }

    public void setEmployeImageContentType(String employeImageContentType) {
        this.employeImageContentType = employeImageContentType;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Employee phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterdEmployeeId() {
        return registerdEmployeeId;
    }

    public Employee registerdEmployeeId(String registerdEmployeeId) {
        this.registerdEmployeeId = registerdEmployeeId;
        return this;
    }

    public void setRegisterdEmployeeId(String registerdEmployeeId) {
        this.registerdEmployeeId = registerdEmployeeId;
    }

    public Firm getFirm() {
        return firm;
    }

    public Employee firm(Firm firm) {
        this.firm = firm;
        return this;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", employeImage='" + getEmployeImage() + "'" +
            ", employeImageContentType='" + getEmployeImageContentType() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", registerdEmployeeId='" + getRegisterdEmployeeId() + "'" +
            "}";
    }
}
