package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.lxisoft.domain.enumeration.TimeSlotLabel;

/**
 * A TimeSlot.
 */
@Entity
@Table(name = "time_slot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TimeSlot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_slot_label")
    private TimeSlotLabel timeSlotLabel;

    @ManyToMany(mappedBy = "timeslots")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Firm> firms = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public TimeSlot startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public TimeSlot endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public TimeSlotLabel getTimeSlotLabel() {
        return timeSlotLabel;
    }

    public TimeSlot timeSlotLabel(TimeSlotLabel timeSlotLabel) {
        this.timeSlotLabel = timeSlotLabel;
        return this;
    }

    public void setTimeSlotLabel(TimeSlotLabel timeSlotLabel) {
        this.timeSlotLabel = timeSlotLabel;
    }

    public Set<Firm> getFirms() {
        return firms;
    }

    public TimeSlot firms(Set<Firm> firms) {
        this.firms = firms;
        return this;
    }

    public TimeSlot addFirm(Firm firm) {
        this.firms.add(firm);
        firm.getTimeslots().add(this);
        return this;
    }

    public TimeSlot removeFirm(Firm firm) {
        this.firms.remove(firm);
        firm.getTimeslots().remove(this);
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
        if (!(o instanceof TimeSlot)) {
            return false;
        }
        return id != null && id.equals(((TimeSlot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeSlot{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", timeSlotLabel='" + getTimeSlotLabel() + "'" +
            "}";
    }
}
