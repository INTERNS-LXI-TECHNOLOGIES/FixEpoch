package com.lxisoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PostelCode.
 */
@Entity
@Table(name = "postel_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PostelCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postel_code")
    private String postelCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostelCode() {
        return postelCode;
    }

    public PostelCode postelCode(String postelCode) {
        this.postelCode = postelCode;
        return this;
    }

    public void setPostelCode(String postelCode) {
        this.postelCode = postelCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostelCode)) {
            return false;
        }
        return id != null && id.equals(((PostelCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostelCode{" +
            "id=" + getId() +
            ", postelCode='" + getPostelCode() + "'" +
            "}";
    }
}
