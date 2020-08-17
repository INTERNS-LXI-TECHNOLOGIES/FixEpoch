package com.lxisoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Lob
    @Column(name = "category_image")
    private byte[] categoryImage;

    @Column(name = "category_image_content_type")
    private String categoryImageContentType;

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Firm> firms = new HashSet<>();

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public Category categoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
        return this;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageContentType() {
        return categoryImageContentType;
    }

    public Category categoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
        return this;
    }

    public void setCategoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
    }

    public Set<Firm> getFirms() {
        return firms;
    }

    public Category firms(Set<Firm> firms) {
        this.firms = firms;
        return this;
    }

    public Category addFirms(Firm firm) {
        this.firms.add(firm);
        firm.setCategory(this);
        return this;
    }

    public Category removeFirms(Firm firm) {
        this.firms.remove(firm);
        firm.setCategory(null);
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
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", categoryImage='" + getCategoryImage() + "'" +
            ", categoryImageContentType='" + getCategoryImageContentType() + "'" +
            "}";
    }
}
