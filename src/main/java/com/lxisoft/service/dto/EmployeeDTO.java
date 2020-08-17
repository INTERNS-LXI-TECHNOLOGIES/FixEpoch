package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.lxisoft.domain.Employee} entity.
 */
public class EmployeeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String firstName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @Lob
    private byte[] employeImage;

    private String employeImageContentType;
    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$")
    private String email;

    @NotNull
    @Size(max = 11)
    @Pattern(regexp = "^\\d+$")
    private String phone;

    @Size(max = 5)
    private String registerdEmployeeId;


    private Long firmId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getEmployeImage() {
        return employeImage;
    }

    public void setEmployeImage(byte[] employeImage) {
        this.employeImage = employeImage;
    }

    public String getEmployeImageContentType() {
        return employeImageContentType;
    }

    public void setEmployeImageContentType(String employeImageContentType) {
        this.employeImageContentType = employeImageContentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterdEmployeeId() {
        return registerdEmployeeId;
    }

    public void setRegisterdEmployeeId(String registerdEmployeeId) {
        this.registerdEmployeeId = registerdEmployeeId;
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
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", employeImage='" + getEmployeImage() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", registerdEmployeeId='" + getRegisterdEmployeeId() + "'" +
            ", firmId=" + getFirmId() +
            "}";
    }
}
