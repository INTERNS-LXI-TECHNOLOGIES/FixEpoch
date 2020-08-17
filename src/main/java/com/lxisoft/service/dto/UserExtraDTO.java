package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lxisoft.domain.UserExtra} entity.
 */
public class UserExtraDTO implements Serializable {
    
    private Long id;

    @Size(max = 11)
    @Pattern(regexp = "^\\d+$")
    private String phone;


    private Long addressId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtraDTO)) {
            return false;
        }

        return id != null && id.equals(((UserExtraDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtraDTO{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", addressId=" + getAddressId() +
            "}";
    }
}
