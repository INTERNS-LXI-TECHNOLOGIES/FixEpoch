package com.lxisoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lxisoft.domain.State} entity.
 */
public class StateDTO implements Serializable {
    
    private Long id;

    @Size(max = 30)
    private String state;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StateDTO)) {
            return false;
        }

        return id != null && id.equals(((StateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StateDTO{" +
            "id=" + getId() +
            ", state='" + getState() + "'" +
            "}";
    }
}
