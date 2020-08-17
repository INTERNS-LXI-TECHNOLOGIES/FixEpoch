package com.lxisoft.service.impl;

import com.lxisoft.service.ProvidedServiceService;
import com.lxisoft.domain.ProvidedService;
import com.lxisoft.repository.ProvidedServiceRepository;
import com.lxisoft.service.dto.ProvidedServiceDTO;
import com.lxisoft.service.mapper.ProvidedServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProvidedService}.
 */
@Service
@Transactional
public class ProvidedServiceServiceImpl implements ProvidedServiceService {

    private final Logger log = LoggerFactory.getLogger(ProvidedServiceServiceImpl.class);

    private final ProvidedServiceRepository providedServiceRepository;

    private final ProvidedServiceMapper providedServiceMapper;

    public ProvidedServiceServiceImpl(ProvidedServiceRepository providedServiceRepository, ProvidedServiceMapper providedServiceMapper) {
        this.providedServiceRepository = providedServiceRepository;
        this.providedServiceMapper = providedServiceMapper;
    }

    /**
     * Save a providedService.
     *
     * @param providedServiceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProvidedServiceDTO save(ProvidedServiceDTO providedServiceDTO) {
        log.debug("Request to save ProvidedService : {}", providedServiceDTO);
        ProvidedService providedService = providedServiceMapper.toEntity(providedServiceDTO);
        providedService = providedServiceRepository.save(providedService);
        return providedServiceMapper.toDto(providedService);
    }

    /**
     * Get all the providedServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProvidedServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProvidedServices");
        return providedServiceRepository.findAll(pageable)
            .map(providedServiceMapper::toDto);
    }


    /**
     * Get one providedService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProvidedServiceDTO> findOne(Long id) {
        log.debug("Request to get ProvidedService : {}", id);
        return providedServiceRepository.findById(id)
            .map(providedServiceMapper::toDto);
    }

    /**
     * Delete the providedService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProvidedService : {}", id);
        providedServiceRepository.deleteById(id);
    }
}
