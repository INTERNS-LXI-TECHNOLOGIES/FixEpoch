package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProvidedService.
 */
@Entity
@Table(name = "provided_service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProvidedService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Lob
    @Column(name = "service_image")
    private byte[] serviceImage;

    @Column(name = "service_image_content_type")
    private String serviceImageContentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "providedServices", allowSetters = true)
    private Firm firm;

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

    public ProvidedService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getServiceImage() {
        return serviceImage;
    }

    public ProvidedService serviceImage(byte[] serviceImage) {
        this.serviceImage = serviceImage;
        return this;
    }

    public void setServiceImage(byte[] serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getServiceImageContentType() {
        return serviceImageContentType;
    }

    public ProvidedService serviceImageContentType(String serviceImageContentType) {
        this.serviceImageContentType = serviceImageContentType;
        return this;
    }

    public void setServiceImageContentType(String serviceImageContentType) {
        this.serviceImageContentType = serviceImageContentType;
    }

    public Firm getFirm() {
        return firm;
    }

    public ProvidedService firm(Firm firm) {
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
        if (!(o instanceof ProvidedService)) {
            return false;
        }
        return id != null && id.equals(((ProvidedService) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProvidedService{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceImage='" + getServiceImage() + "'" +
            ", serviceImageContentType='" + getServiceImageContentType() + "'" +
            "}";
    }
}
