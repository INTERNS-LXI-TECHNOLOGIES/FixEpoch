package com.lxisoft.web.rest;

import com.lxisoft.service.TimeSlotService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.service.dto.TimeSlotDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.domain.TimeSlot}.
 */
@RestController
@RequestMapping("/api")
public class TimeSlotResource {

    private final Logger log = LoggerFactory.getLogger(TimeSlotResource.class);

    private static final String ENTITY_NAME = "timeSlot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimeSlotService timeSlotService;

    public TimeSlotResource(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    /**
     * {@code POST  /time-slots} : Create a new timeSlot.
     *
     * @param timeSlotDTO the timeSlotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timeSlotDTO, or with status {@code 400 (Bad Request)} if the timeSlot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/time-slots")
    public ResponseEntity<TimeSlotDTO> createTimeSlot(@Valid @RequestBody TimeSlotDTO timeSlotDTO) throws URISyntaxException {
        log.debug("REST request to save TimeSlot : {}", timeSlotDTO);
        if (timeSlotDTO.getId() != null) {
            throw new BadRequestAlertException("A new timeSlot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimeSlotDTO result = timeSlotService.save(timeSlotDTO);
        return ResponseEntity.created(new URI("/api/time-slots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /time-slots} : Updates an existing timeSlot.
     *
     * @param timeSlotDTO the timeSlotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timeSlotDTO,
     * or with status {@code 400 (Bad Request)} if the timeSlotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timeSlotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/time-slots")
    public ResponseEntity<TimeSlotDTO> updateTimeSlot(@Valid @RequestBody TimeSlotDTO timeSlotDTO) throws URISyntaxException {
        log.debug("REST request to update TimeSlot : {}", timeSlotDTO);
        if (timeSlotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TimeSlotDTO result = timeSlotService.save(timeSlotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timeSlotDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /time-slots} : get all the timeSlots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timeSlots in body.
     */
    @GetMapping("/time-slots")
    public ResponseEntity<List<TimeSlotDTO>> getAllTimeSlots(Pageable pageable) {
        log.debug("REST request to get a page of TimeSlots");
        Page<TimeSlotDTO> page = timeSlotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /time-slots/:id} : get the "id" timeSlot.
     *
     * @param id the id of the timeSlotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timeSlotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/time-slots/{id}")
    public ResponseEntity<TimeSlotDTO> getTimeSlot(@PathVariable Long id) {
        log.debug("REST request to get TimeSlot : {}", id);
        Optional<TimeSlotDTO> timeSlotDTO = timeSlotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timeSlotDTO);
    }

    /**
     * {@code DELETE  /time-slots/:id} : delete the "id" timeSlot.
     *
     * @param id the id of the timeSlotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/time-slots/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        log.debug("REST request to delete TimeSlot : {}", id);
        timeSlotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
