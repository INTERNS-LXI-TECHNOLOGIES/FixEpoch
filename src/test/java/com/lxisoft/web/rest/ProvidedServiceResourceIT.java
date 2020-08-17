package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.ProvidedService;
import com.lxisoft.domain.Firm;
import com.lxisoft.repository.ProvidedServiceRepository;
import com.lxisoft.service.ProvidedServiceService;
import com.lxisoft.service.dto.ProvidedServiceDTO;
import com.lxisoft.service.mapper.ProvidedServiceMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProvidedServiceResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProvidedServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SERVICE_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SERVICE_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SERVICE_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SERVICE_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private ProvidedServiceRepository providedServiceRepository;

    @Autowired
    private ProvidedServiceMapper providedServiceMapper;

    @Autowired
    private ProvidedServiceService providedServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProvidedServiceMockMvc;

    private ProvidedService providedService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProvidedService createEntity(EntityManager em) {
        ProvidedService providedService = new ProvidedService()
            .name(DEFAULT_NAME)
            .serviceImage(DEFAULT_SERVICE_IMAGE)
            .serviceImageContentType(DEFAULT_SERVICE_IMAGE_CONTENT_TYPE);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        providedService.setFirm(firm);
        return providedService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProvidedService createUpdatedEntity(EntityManager em) {
        ProvidedService providedService = new ProvidedService()
            .name(UPDATED_NAME)
            .serviceImage(UPDATED_SERVICE_IMAGE)
            .serviceImageContentType(UPDATED_SERVICE_IMAGE_CONTENT_TYPE);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createUpdatedEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        providedService.setFirm(firm);
        return providedService;
    }

    @BeforeEach
    public void initTest() {
        providedService = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvidedService() throws Exception {
        int databaseSizeBeforeCreate = providedServiceRepository.findAll().size();
        // Create the ProvidedService
        ProvidedServiceDTO providedServiceDTO = providedServiceMapper.toDto(providedService);
        restProvidedServiceMockMvc.perform(post("/api/provided-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providedServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ProvidedService in the database
        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ProvidedService testProvidedService = providedServiceList.get(providedServiceList.size() - 1);
        assertThat(testProvidedService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProvidedService.getServiceImage()).isEqualTo(DEFAULT_SERVICE_IMAGE);
        assertThat(testProvidedService.getServiceImageContentType()).isEqualTo(DEFAULT_SERVICE_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createProvidedServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providedServiceRepository.findAll().size();

        // Create the ProvidedService with an existing ID
        providedService.setId(1L);
        ProvidedServiceDTO providedServiceDTO = providedServiceMapper.toDto(providedService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvidedServiceMockMvc.perform(post("/api/provided-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providedServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProvidedService in the database
        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = providedServiceRepository.findAll().size();
        // set the field null
        providedService.setName(null);

        // Create the ProvidedService, which fails.
        ProvidedServiceDTO providedServiceDTO = providedServiceMapper.toDto(providedService);


        restProvidedServiceMockMvc.perform(post("/api/provided-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providedServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProvidedServices() throws Exception {
        // Initialize the database
        providedServiceRepository.saveAndFlush(providedService);

        // Get all the providedServiceList
        restProvidedServiceMockMvc.perform(get("/api/provided-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(providedService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].serviceImageContentType").value(hasItem(DEFAULT_SERVICE_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].serviceImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_SERVICE_IMAGE))));
    }
    
    @Test
    @Transactional
    public void getProvidedService() throws Exception {
        // Initialize the database
        providedServiceRepository.saveAndFlush(providedService);

        // Get the providedService
        restProvidedServiceMockMvc.perform(get("/api/provided-services/{id}", providedService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(providedService.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.serviceImageContentType").value(DEFAULT_SERVICE_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.serviceImage").value(Base64Utils.encodeToString(DEFAULT_SERVICE_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingProvidedService() throws Exception {
        // Get the providedService
        restProvidedServiceMockMvc.perform(get("/api/provided-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvidedService() throws Exception {
        // Initialize the database
        providedServiceRepository.saveAndFlush(providedService);

        int databaseSizeBeforeUpdate = providedServiceRepository.findAll().size();

        // Update the providedService
        ProvidedService updatedProvidedService = providedServiceRepository.findById(providedService.getId()).get();
        // Disconnect from session so that the updates on updatedProvidedService are not directly saved in db
        em.detach(updatedProvidedService);
        updatedProvidedService
            .name(UPDATED_NAME)
            .serviceImage(UPDATED_SERVICE_IMAGE)
            .serviceImageContentType(UPDATED_SERVICE_IMAGE_CONTENT_TYPE);
        ProvidedServiceDTO providedServiceDTO = providedServiceMapper.toDto(updatedProvidedService);

        restProvidedServiceMockMvc.perform(put("/api/provided-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providedServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ProvidedService in the database
        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeUpdate);
        ProvidedService testProvidedService = providedServiceList.get(providedServiceList.size() - 1);
        assertThat(testProvidedService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProvidedService.getServiceImage()).isEqualTo(UPDATED_SERVICE_IMAGE);
        assertThat(testProvidedService.getServiceImageContentType()).isEqualTo(UPDATED_SERVICE_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProvidedService() throws Exception {
        int databaseSizeBeforeUpdate = providedServiceRepository.findAll().size();

        // Create the ProvidedService
        ProvidedServiceDTO providedServiceDTO = providedServiceMapper.toDto(providedService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvidedServiceMockMvc.perform(put("/api/provided-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providedServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProvidedService in the database
        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvidedService() throws Exception {
        // Initialize the database
        providedServiceRepository.saveAndFlush(providedService);

        int databaseSizeBeforeDelete = providedServiceRepository.findAll().size();

        // Delete the providedService
        restProvidedServiceMockMvc.perform(delete("/api/provided-services/{id}", providedService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProvidedService> providedServiceList = providedServiceRepository.findAll();
        assertThat(providedServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
