package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 90)
    @Column(name = "location_address_line_one", length = 90)
    private String locationAddressLineOne;

    @Size(max = 90)
    @Column(name = "location_address_line_two", length = 90)
    private String locationAddressLineTwo;

    @Size(max = 90)
    @Column(name = "land_mark", length = 90)
    private String landMark;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "addresses", allowSetters = true)
    private PostelCode postalCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "addresses", allowSetters = true)
    private City city;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "addresses", allowSetters = true)
    private State state;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationAddressLineOne() {
        return locationAddressLineOne;
    }

    public Address locationAddressLineOne(String locationAddressLineOne) {
        this.locationAddressLineOne = locationAddressLineOne;
        return this;
    }

    public void setLocationAddressLineOne(String locationAddressLineOne) {
        this.locationAddressLineOne = locationAddressLineOne;
    }

    public String getLocationAddressLineTwo() {
        return locationAddressLineTwo;
    }

    public Address locationAddressLineTwo(String locationAddressLineTwo) {
        this.locationAddressLineTwo = locationAddressLineTwo;
        return this;
    }

    public void setLocationAddressLineTwo(String locationAddressLineTwo) {
        this.locationAddressLineTwo = locationAddressLineTwo;
    }

    public String getLandMark() {
        return landMark;
    }

    public Address landMark(String landMark) {
        this.landMark = landMark;
        return this;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public PostelCode getPostalCode() {
        return postalCode;
    }

    public Address postalCode(PostelCode postelCode) {
        this.postalCode = postelCode;
        return this;
    }

    public void setPostalCode(PostelCode postelCode) {
        this.postalCode = postelCode;
    }

    public City getCity() {
        return city;
    }

    public Address city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public Address state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Address userExtra(UserExtra userExtra) {
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
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", locationAddressLineOne='" + getLocationAddressLineOne() + "'" +
            ", locationAddressLineTwo='" + getLocationAddressLineTwo() + "'" +
            ", landMark='" + getLandMark() + "'" +
            "}";
    }
}
