package com.lxisoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer implements Serializable {

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
    @Column(name = "customer_image")
    private byte[] customerImage;

    @Column(name = "customer_image_content_type")
    private String customerImageContentType;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(max = 11)
    @Pattern(regexp = "^\\d+$")
    @Column(name = "phone", length = 11, nullable = false, unique = true)
    private String phone;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Firm> firms = new HashSet<>();

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

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getCustomerImage() {
        return customerImage;
    }

    public Customer customerImage(byte[] customerImage) {
        this.customerImage = customerImage;
        return this;
    }

    public void setCustomerImage(byte[] customerImage) {
        this.customerImage = customerImage;
    }

    public String getCustomerImageContentType() {
        return customerImageContentType;
    }

    public Customer customerImageContentType(String customerImageContentType) {
        this.customerImageContentType = customerImageContentType;
        return this;
    }

    public void setCustomerImageContentType(String customerImageContentType) {
        this.customerImageContentType = customerImageContentType;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Customer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public Customer address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Firm> getFirms() {
        return firms;
    }

    public Customer firms(Set<Firm> firms) {
        this.firms = firms;
        return this;
    }

    public Customer addFirm(Firm firm) {
        this.firms.add(firm);
        firm.setCustomer(this);
        return this;
    }

    public Customer removeFirm(Firm firm) {
        this.firms.remove(firm);
        firm.setCustomer(null);
        return this;
    }

    public void setFirms(Set<Firm> firms) {
        this.firms = firms;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", customerImage='" + getCustomerImage() + "'" +
            ", customerImageContentType='" + getCustomerImageContentType() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
