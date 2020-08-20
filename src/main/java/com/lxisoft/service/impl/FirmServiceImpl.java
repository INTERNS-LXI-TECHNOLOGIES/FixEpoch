package com.lxisoft.service.impl;

import com.lxisoft.service.FirmService;
import com.lxisoft.domain.Firm;
import com.lxisoft.repository.FirmRepository;
import com.lxisoft.service.dto.FirmDTO;
import com.lxisoft.service.mapper.FirmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Firm}.
 */
@Service
@Transactional
public class FirmServiceImpl implements FirmService {

    private final Logger log = LoggerFactory.getLogger(FirmServiceImpl.class);

    private final FirmRepository firmRepository;

    private final FirmMapper firmMapper;

    public FirmServiceImpl(FirmRepository firmRepository, FirmMapper firmMapper) {
        this.firmRepository = firmRepository;
        this.firmMapper = firmMapper;
    }

    /**
     * Save a firm.
     *
     * @param firmDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FirmDTO save(FirmDTO firmDTO) {
        log.debug("Request to save Firm : {}", firmDTO);
        Firm firm = firmMapper.toEntity(firmDTO);
        firm = firmRepository.save(firm);
        return firmMapper.toDto(firm);
    }

    /**
     * Get all the firms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FirmDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Firms");
        return firmRepository.findAll(pageable)
            .map(firmMapper::toDto);
    }


    /**
     * Get all the firms with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FirmDTO> findAllWithEagerRelationships(Pageable pageable) {
        return firmRepository.findAllWithEagerRelationships(pageable).map(firmMapper::toDto);
    }

    /**
     * Get one firm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FirmDTO> findOne(Long id) {
        log.debug("Request to get Firm : {}", id);
        return firmRepository.findOneWithEagerRelationships(id)
            .map(firmMapper::toDto);
    }

    /**
     * Delete the firm by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Firm : {}", id);
        firmRepository.deleteById(id);
    }

    @Override
    public List<Firm> findFirmByCategory(int id) {

        List<Firm> firmsList = firmRepository.findAll();
        List<Firm> firmByCategory = new ArrayList<Firm>();
        for(int i=0;i<firmsList.size();i++)
        {
            if (firmsList.get(i).getCategory().getId() == id)
            {
                firmByCategory.add(firmsList.get(i));
            }
        }
        return firmByCategory;
    }
}
