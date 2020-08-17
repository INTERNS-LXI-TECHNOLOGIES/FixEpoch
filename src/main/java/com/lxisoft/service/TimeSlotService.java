package com.lxisoft.service;

import com.lxisoft.service.dto.TimeSlotDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lxisoft.domain.TimeSlot}.
 */
public interface TimeSlotService {

    /**
     * Save a timeSlot.
     *
     * @param timeSlotDTO the entity to save.
     * @return the persisted entity.
     */
    TimeSlotDTO save(TimeSlotDTO timeSlotDTO);

    /**
     * Get all the timeSlots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TimeSlotDTO> findAll(Pageable pageable);


    /**
     * Get the "id" timeSlot.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TimeSlotDTO> findOne(Long id);

    /**
     * Delete the "id" timeSlot.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
