package com.lxisoft.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lxisoft.domain.PostelCode} entity.
 */
public class PostelCodeDTO implements Serializable {
    
    private Long id;

    private String postelCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostelCode() {
        return postelCode;
    }

    public void setPostelCode(String postelCode) {
        this.postelCode = postelCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostelCodeDTO)) {
            return false;
        }

        return id != null && id.equals(((PostelCodeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostelCodeDTO{" +
            "id=" + getId() +
            ", postelCode='" + getPostelCode() + "'" +
            "}";
    }
}
