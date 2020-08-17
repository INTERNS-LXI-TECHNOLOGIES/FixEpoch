package com.lxisoft.web.rest;

import com.lxisoft.service.PostelCodeService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.service.dto.PostelCodeDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.domain.PostelCode}.
 */
@RestController
@RequestMapping("/api")
public class PostelCodeResource {

    private final Logger log = LoggerFactory.getLogger(PostelCodeResource.class);

    private static final String ENTITY_NAME = "postelCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostelCodeService postelCodeService;

    public PostelCodeResource(PostelCodeService postelCodeService) {
        this.postelCodeService = postelCodeService;
    }

    /**
     * {@code POST  /postel-codes} : Create a new postelCode.
     *
     * @param postelCodeDTO the postelCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postelCodeDTO, or with status {@code 400 (Bad Request)} if the postelCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/postel-codes")
    public ResponseEntity<PostelCodeDTO> createPostelCode(@RequestBody PostelCodeDTO postelCodeDTO) throws URISyntaxException {
        log.debug("REST request to save PostelCode : {}", postelCodeDTO);
        if (postelCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new postelCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PostelCodeDTO result = postelCodeService.save(postelCodeDTO);
        return ResponseEntity.created(new URI("/api/postel-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /postel-codes} : Updates an existing postelCode.
     *
     * @param postelCodeDTO the postelCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postelCodeDTO,
     * or with status {@code 400 (Bad Request)} if the postelCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the postelCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/postel-codes")
    public ResponseEntity<PostelCodeDTO> updatePostelCode(@RequestBody PostelCodeDTO postelCodeDTO) throws URISyntaxException {
        log.debug("REST request to update PostelCode : {}", postelCodeDTO);
        if (postelCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PostelCodeDTO result = postelCodeService.save(postelCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postelCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /postel-codes} : get all the postelCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postelCodes in body.
     */
    @GetMapping("/postel-codes")
    public ResponseEntity<List<PostelCodeDTO>> getAllPostelCodes(Pageable pageable) {
        log.debug("REST request to get a page of PostelCodes");
        Page<PostelCodeDTO> page = postelCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /postel-codes/:id} : get the "id" postelCode.
     *
     * @param id the id of the postelCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postelCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postel-codes/{id}")
    public ResponseEntity<PostelCodeDTO> getPostelCode(@PathVariable Long id) {
        log.debug("REST request to get PostelCode : {}", id);
        Optional<PostelCodeDTO> postelCodeDTO = postelCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postelCodeDTO);
    }

    /**
     * {@code DELETE  /postel-codes/:id} : delete the "id" postelCode.
     *
     * @param id the id of the postelCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/postel-codes/{id}")
    public ResponseEntity<Void> deletePostelCode(@PathVariable Long id) {
        log.debug("REST request to delete PostelCode : {}", id);
        postelCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
