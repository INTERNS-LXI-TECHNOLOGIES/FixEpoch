package com.lxisoft.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.lxisoft.domain.enumeration.AppointmentVerifiedStatus;

/**
 * A DTO for the {@link com.lxisoft.domain.Appointment} entity.
 */
public class AppointmentDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private AppointmentVerifiedStatus appointmentStatus;


    private Long timeSlotId;

    private Long employeeId;

    private Long providedServiceId;

    private Long firmId;

    private Long userExtraId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AppointmentVerifiedStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentVerifiedStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProvidedServiceId() {
        return providedServiceId;
    }

    public void setProvidedServiceId(Long providedServiceId) {
        this.providedServiceId = providedServiceId;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    public Long getUserExtraId() {
        return userExtraId;
    }

    public void setUserExtraId(Long userExtraId) {
        this.userExtraId = userExtraId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppointmentDTO)) {
            return false;
        }

        return id != null && id.equals(((AppointmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppointmentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", appointmentStatus='" + getAppointmentStatus() + "'" +
            ", timeSlotId=" + getTimeSlotId() +
            ", employeeId=" + getEmployeeId() +
            ", providedServiceId=" + getProvidedServiceId() +
            ", firmId=" + getFirmId() +
            ", userExtraId=" + getUserExtraId() +
            "}";
    }
}
