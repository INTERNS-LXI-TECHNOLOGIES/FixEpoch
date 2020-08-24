package com.lxisoft.repository;

import com.lxisoft.domain.ProvidedService;

import com.lxisoft.service.dto.ProvidedServiceDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProvidedService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvidedServiceRepository extends JpaRepository<ProvidedService, Long> {

}
