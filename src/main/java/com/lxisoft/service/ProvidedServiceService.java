package com.lxisoft.service;

import com.lxisoft.service.dto.ProvidedServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lxisoft.domain.ProvidedService}.
 */
public interface ProvidedServiceService {

    /**
     * Save a providedService.
     *
     * @param providedServiceDTO the entity to save.
     * @return the persisted entity.
     */
    ProvidedServiceDTO save(ProvidedServiceDTO providedServiceDTO);

    /**
     * Get all the providedServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProvidedServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" providedService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProvidedServiceDTO> findOne(Long id);

    /**
     * Delete the "id" providedService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
