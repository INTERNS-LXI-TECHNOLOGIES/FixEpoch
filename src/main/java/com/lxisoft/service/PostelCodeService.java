package com.lxisoft.service;

import com.lxisoft.domain.PostelCode;
import com.lxisoft.domain.PostelCode_;
import com.lxisoft.service.dto.PostelCodeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lxisoft.domain.PostelCode}.
 */
public interface PostelCodeService {

    /**
     * Save a postelCode.
     *
     * @param postelCodeDTO the entity to save.
     * @return the persisted entity.
     */
    PostelCodeDTO save(PostelCodeDTO postelCodeDTO);

    /**
     * Get all the postelCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PostelCodeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" postelCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PostelCodeDTO> findOne(Long id);

    /**
     * Delete the "id" postelCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void savePostelCode(PostelCode postelCode);
}
