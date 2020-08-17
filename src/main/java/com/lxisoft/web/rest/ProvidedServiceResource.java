package com.lxisoft.web.rest;

import com.lxisoft.service.ProvidedServiceService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.service.dto.ProvidedServiceDTO;

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
 * REST controller for managing {@link com.lxisoft.domain.ProvidedService}.
 */
@RestController
@RequestMapping("/api")
public class ProvidedServiceResource {

    private final Logger log = LoggerFactory.getLogger(ProvidedServiceResource.class);

    private static final String ENTITY_NAME = "providedService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProvidedServiceService providedServiceService;

    public ProvidedServiceResource(ProvidedServiceService providedServiceService) {
        this.providedServiceService = providedServiceService;
    }

    /**
     * {@code POST  /provided-services} : Create a new providedService.
     *
     * @param providedServiceDTO the providedServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new providedServiceDTO, or with status {@code 400 (Bad Request)} if the providedService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/provided-services")
    public ResponseEntity<ProvidedServiceDTO> createProvidedService(@Valid @RequestBody ProvidedServiceDTO providedServiceDTO) throws URISyntaxException {
        log.debug("REST request to save ProvidedService : {}", providedServiceDTO);
        if (providedServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new providedService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvidedServiceDTO result = providedServiceService.save(providedServiceDTO);
        return ResponseEntity.created(new URI("/api/provided-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /provided-services} : Updates an existing providedService.
     *
     * @param providedServiceDTO the providedServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated providedServiceDTO,
     * or with status {@code 400 (Bad Request)} if the providedServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the providedServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/provided-services")
    public ResponseEntity<ProvidedServiceDTO> updateProvidedService(@Valid @RequestBody ProvidedServiceDTO providedServiceDTO) throws URISyntaxException {
        log.debug("REST request to update ProvidedService : {}", providedServiceDTO);
        if (providedServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvidedServiceDTO result = providedServiceService.save(providedServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, providedServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /provided-services} : get all the providedServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of providedServices in body.
     */
    @GetMapping("/provided-services")
    public ResponseEntity<List<ProvidedServiceDTO>> getAllProvidedServices(Pageable pageable) {
        log.debug("REST request to get a page of ProvidedServices");
        Page<ProvidedServiceDTO> page = providedServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /provided-services/:id} : get the "id" providedService.
     *
     * @param id the id of the providedServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the providedServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/provided-services/{id}")
    public ResponseEntity<ProvidedServiceDTO> getProvidedService(@PathVariable Long id) {
        log.debug("REST request to get ProvidedService : {}", id);
        Optional<ProvidedServiceDTO> providedServiceDTO = providedServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providedServiceDTO);
    }

    /**
     * {@code DELETE  /provided-services/:id} : delete the "id" providedService.
     *
     * @param id the id of the providedServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/provided-services/{id}")
    public ResponseEntity<Void> deleteProvidedService(@PathVariable Long id) {
        log.debug("REST request to delete ProvidedService : {}", id);
        providedServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
