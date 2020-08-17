package com.lxisoft.repository;

import com.lxisoft.domain.Firm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Firm entity.
 */
@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {

    @Query(value = "select distinct firm from Firm firm left join fetch firm.timeslots",
        countQuery = "select count(distinct firm) from Firm firm")
    Page<Firm> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct firm from Firm firm left join fetch firm.timeslots")
    List<Firm> findAllWithEagerRelationships();

    @Query("select firm from Firm firm left join fetch firm.timeslots where firm.id =:id")
    Optional<Firm> findOneWithEagerRelationships(@Param("id") Long id);
}
