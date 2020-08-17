package com.lxisoft.service;

import com.lxisoft.service.dto.StateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lxisoft.domain.State}.
 */
public interface StateService {

    /**
     * Save a state.
     *
     * @param stateDTO the entity to save.
     * @return the persisted entity.
     */
    StateDTO save(StateDTO stateDTO);

    /**
     * Get all the states.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" state.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StateDTO> findOne(Long id);

    /**
     * Delete the "id" state.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
