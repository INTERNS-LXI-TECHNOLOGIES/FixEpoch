package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.lxisoft.domain.ProvidedService} entity.
 */
public class ProvidedServiceDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @Lob
    private byte[] serviceImage;

    private String serviceImageContentType;

    private Long firmId;
    
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

    public byte[] getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(byte[] serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getServiceImageContentType() {
        return serviceImageContentType;
    }

    public void setServiceImageContentType(String serviceImageContentType) {
        this.serviceImageContentType = serviceImageContentType;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProvidedServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((ProvidedServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProvidedServiceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceImage='" + getServiceImage() + "'" +
            ", firmId=" + getFirmId() +
            "}";
    }
}
