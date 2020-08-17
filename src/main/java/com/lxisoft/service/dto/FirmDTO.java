package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.lxisoft.domain.Firm} entity.
 */
public class FirmDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    private String description;

    @Lob
    private byte[] image;

    private String imageContentType;

    private Long addressId;
    private Set<TimeSlotDTO> timeslots = new HashSet<>();

    private Long categoryId;

    private Long customerId;
    
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Set<TimeSlotDTO> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(Set<TimeSlotDTO> timeSlots) {
        this.timeslots = timeSlots;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirmDTO)) {
            return false;
        }

        return id != null && id.equals(((FirmDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FirmDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", addressId=" + getAddressId() +
            ", timeslots='" + getTimeslots() + "'" +
            ", categoryId=" + getCategoryId() +
            ", customerId=" + getCustomerId() +
            "}";
    }
}
