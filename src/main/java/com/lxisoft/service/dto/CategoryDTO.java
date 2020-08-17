package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.lxisoft.domain.Category} entity.
 */
public class CategoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @Size(max = 100)
    private String description;

    @Lob
    private byte[] categoryImage;

    private String categoryImageContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageContentType() {
        return categoryImageContentType;
    }

    public void setCategoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((CategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", categoryImage='" + getCategoryImage() + "'" +
            "}";
    }
}
