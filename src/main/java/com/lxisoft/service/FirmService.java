package com.lxisoft.service;

import com.lxisoft.domain.Firm;
import com.lxisoft.service.dto.FirmDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.lxisoft.domain.Firm}.
 */
public interface FirmService {

    /**
     * Save a firm.
     *
     * @param firmDTO the entity to save.
     * @return the persisted entity.
     */
    FirmDTO save(FirmDTO firmDTO);

    /**
     * Get all the firms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FirmDTO> findAll(Pageable pageable);

    /**
     * Get all the firms with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<FirmDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" firm.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FirmDTO> findOne(Long id);

    /**
     * Delete the "id" firm.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<Firm> findFirmByCategory(int id);


}
