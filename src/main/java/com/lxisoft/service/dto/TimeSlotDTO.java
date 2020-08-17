package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.lxisoft.domain.enumeration.TimeSlotLabel;

/**
 * A DTO for the {@link com.lxisoft.domain.TimeSlot} entity.
 */
public class TimeSlotDTO implements Serializable {
    
    private Long id;

    private String startTime;

    private String endTime;

    private TimeSlotLabel timeSlotLabel;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public TimeSlotLabel getTimeSlotLabel() {
        return timeSlotLabel;
    }

    public void setTimeSlotLabel(TimeSlotLabel timeSlotLabel) {
        this.timeSlotLabel = timeSlotLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeSlotDTO)) {
            return false;
        }

        return id != null && id.equals(((TimeSlotDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeSlotDTO{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", timeSlotLabel='" + getTimeSlotLabel() + "'" +
            "}";
    }
}
