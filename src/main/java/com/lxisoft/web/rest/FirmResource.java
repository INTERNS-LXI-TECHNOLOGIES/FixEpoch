package com.lxisoft.web.rest;

import com.lxisoft.service.FirmService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.service.dto.FirmDTO;

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
 * REST controller for managing {@link com.lxisoft.domain.Firm}.
 */
@RestController
@RequestMapping("/api")
public class FirmResource {

    private final Logger log = LoggerFactory.getLogger(FirmResource.class);

    private static final String ENTITY_NAME = "firm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FirmService firmService;

    public FirmResource(FirmService firmService) {
        this.firmService = firmService;
    }

    /**
     * {@code POST  /firms} : Create a new firm.
     *
     * @param firmDTO the firmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new firmDTO, or with status {@code 400 (Bad Request)} if the firm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/firms")
    public ResponseEntity<FirmDTO> createFirm(@Valid @RequestBody FirmDTO firmDTO) throws URISyntaxException {
        log.debug("REST request to save Firm : {}", firmDTO);
        if (firmDTO.getId() != null) {
            throw new BadRequestAlertException("A new firm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FirmDTO result = firmService.save(firmDTO);
        return ResponseEntity.created(new URI("/api/firms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /firms} : Updates an existing firm.
     *
     * @param firmDTO the firmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated firmDTO,
     * or with status {@code 400 (Bad Request)} if the firmDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the firmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/firms")
    public ResponseEntity<FirmDTO> updateFirm(@Valid @RequestBody FirmDTO firmDTO) throws URISyntaxException {
        log.debug("REST request to update Firm : {}", firmDTO);
        if (firmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FirmDTO result = firmService.save(firmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, firmDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /firms} : get all the firms.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of firms in body.
     */
    @GetMapping("/firms")
    public ResponseEntity<List<FirmDTO>> getAllFirms(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Firms");
        Page<FirmDTO> page;
        if (eagerload) {
            page = firmService.findAllWithEagerRelationships(pageable);
        } else {
            page = firmService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /firms/:id} : get the "id" firm.
     *
     * @param id the id of the firmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the firmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/firms/{id}")
    public ResponseEntity<FirmDTO> getFirm(@PathVariable Long id) {
        log.debug("REST request to get Firm : {}", id);
        Optional<FirmDTO> firmDTO = firmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(firmDTO);
    }

    /**
     * {@code DELETE  /firms/:id} : delete the "id" firm.
     *
     * @param id the id of the firmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/firms/{id}")
    public ResponseEntity<Void> deleteFirm(@PathVariable Long id) {
        log.debug("REST request to delete Firm : {}", id);
        firmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
