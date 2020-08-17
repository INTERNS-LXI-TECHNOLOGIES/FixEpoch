package com.lxisoft.service.impl;

import com.lxisoft.service.PostelCodeService;
import com.lxisoft.domain.PostelCode;
import com.lxisoft.repository.PostelCodeRepository;
import com.lxisoft.service.dto.PostelCodeDTO;
import com.lxisoft.service.mapper.PostelCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PostelCode}.
 */
@Service
@Transactional
public class PostelCodeServiceImpl implements PostelCodeService {

    private final Logger log = LoggerFactory.getLogger(PostelCodeServiceImpl.class);

    private final PostelCodeRepository postelCodeRepository;

    private final PostelCodeMapper postelCodeMapper;

    public PostelCodeServiceImpl(PostelCodeRepository postelCodeRepository, PostelCodeMapper postelCodeMapper) {
        this.postelCodeRepository = postelCodeRepository;
        this.postelCodeMapper = postelCodeMapper;
    }

    /**
     * Save a postelCode.
     *
     * @param postelCodeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PostelCodeDTO save(PostelCodeDTO postelCodeDTO) {
        log.debug("Request to save PostelCode : {}", postelCodeDTO);
        PostelCode postelCode = postelCodeMapper.toEntity(postelCodeDTO);
        postelCode = postelCodeRepository.save(postelCode);
        return postelCodeMapper.toDto(postelCode);
    }

    /**
     * Get all the postelCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostelCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PostelCodes");
        return postelCodeRepository.findAll(pageable)
            .map(postelCodeMapper::toDto);
    }


    /**
     * Get one postelCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PostelCodeDTO> findOne(Long id) {
        log.debug("Request to get PostelCode : {}", id);
        return postelCodeRepository.findById(id)
            .map(postelCodeMapper::toDto);
    }

    /**
     * Delete the postelCode by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PostelCode : {}", id);
        postelCodeRepository.deleteById(id);
    }
}
