package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lxisoft.domain.Address} entity.
 */
public class AddressDTO implements Serializable {
    
    private Long id;

    @Size(max = 90)
    private String locationAddressLineOne;

    @Size(max = 90)
    private String locationAddressLineTwo;

    @Size(max = 90)
    private String landMark;


    private Long postalCodeId;

    private Long cityId;

    private Long stateId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationAddressLineOne() {
        return locationAddressLineOne;
    }

    public void setLocationAddressLineOne(String locationAddressLineOne) {
        this.locationAddressLineOne = locationAddressLineOne;
    }

    public String getLocationAddressLineTwo() {
        return locationAddressLineTwo;
    }

    public void setLocationAddressLineTwo(String locationAddressLineTwo) {
        this.locationAddressLineTwo = locationAddressLineTwo;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public Long getPostalCodeId() {
        return postalCodeId;
    }

    public void setPostalCodeId(Long postelCodeId) {
        this.postalCodeId = postelCodeId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", locationAddressLineOne='" + getLocationAddressLineOne() + "'" +
            ", locationAddressLineTwo='" + getLocationAddressLineTwo() + "'" +
            ", landMark='" + getLandMark() + "'" +
            ", postalCodeId=" + getPostalCodeId() +
            ", cityId=" + getCityId() +
            ", stateId=" + getStateId() +
            "}";
    }
}
