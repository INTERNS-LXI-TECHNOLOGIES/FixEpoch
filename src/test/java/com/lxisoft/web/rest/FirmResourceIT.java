package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.Firm;
import com.lxisoft.domain.Address;
import com.lxisoft.domain.TimeSlot;
import com.lxisoft.domain.Category;
import com.lxisoft.domain.Customer;
import com.lxisoft.repository.FirmRepository;
import com.lxisoft.service.FirmService;
import com.lxisoft.service.dto.FirmDTO;
import com.lxisoft.service.mapper.FirmMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FirmResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class FirmResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private FirmRepository firmRepository;

    @Mock
    private FirmRepository firmRepositoryMock;

    @Autowired
    private FirmMapper firmMapper;

    @Mock
    private FirmService firmServiceMock;

    @Autowired
    private FirmService firmService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFirmMockMvc;

    private Firm firm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Firm createEntity(EntityManager em) {
        Firm firm = new Firm()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        // Add required entity
        Address address;
        if (TestUtil.findAll(em, Address.class).isEmpty()) {
            address = AddressResourceIT.createEntity(em);
            em.persist(address);
            em.flush();
        } else {
            address = TestUtil.findAll(em, Address.class).get(0);
        }
        firm.setAddress(address);
        // Add required entity
        TimeSlot timeSlot;
        if (TestUtil.findAll(em, TimeSlot.class).isEmpty()) {
            timeSlot = TimeSlotResourceIT.createEntity(em);
            em.persist(timeSlot);
            em.flush();
        } else {
            timeSlot = TestUtil.findAll(em, TimeSlot.class).get(0);
        }
        firm.getTimeslots().add(timeSlot);
        // Add required entity
        Category category;
        if (TestUtil.findAll(em, Category.class).isEmpty()) {
            category = CategoryResourceIT.createEntity(em);
            em.persist(category);
            em.flush();
        } else {
            category = TestUtil.findAll(em, Category.class).get(0);
        }
        firm.setCategory(category);
        // Add required entity
        Customer customer;
        if (TestUtil.findAll(em, Customer.class).isEmpty()) {
            customer = CustomerResourceIT.createEntity(em);
            em.persist(customer);
            em.flush();
        } else {
            customer = TestUtil.findAll(em, Customer.class).get(0);
        }
        firm.setCustomer(customer);
        return firm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Firm createUpdatedEntity(EntityManager em) {
        Firm firm = new Firm()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        // Add required entity
        Address address;
        if (TestUtil.findAll(em, Address.class).isEmpty()) {
            address = AddressResourceIT.createUpdatedEntity(em);
            em.persist(address);
            em.flush();
        } else {
            address = TestUtil.findAll(em, Address.class).get(0);
        }
        firm.setAddress(address);
        // Add required entity
        TimeSlot timeSlot;
        if (TestUtil.findAll(em, TimeSlot.class).isEmpty()) {
            timeSlot = TimeSlotResourceIT.createUpdatedEntity(em);
            em.persist(timeSlot);
            em.flush();
        } else {
            timeSlot = TestUtil.findAll(em, TimeSlot.class).get(0);
        }
        firm.getTimeslots().add(timeSlot);
        // Add required entity
        Category category;
        if (TestUtil.findAll(em, Category.class).isEmpty()) {
            category = CategoryResourceIT.createUpdatedEntity(em);
            em.persist(category);
            em.flush();
        } else {
            category = TestUtil.findAll(em, Category.class).get(0);
        }
        firm.setCategory(category);
        // Add required entity
        Customer customer;
        if (TestUtil.findAll(em, Customer.class).isEmpty()) {
            customer = CustomerResourceIT.createUpdatedEntity(em);
            em.persist(customer);
            em.flush();
        } else {
            customer = TestUtil.findAll(em, Customer.class).get(0);
        }
        firm.setCustomer(customer);
        return firm;
    }

    @BeforeEach
    public void initTest() {
        firm = createEntity(em);
    }

    @Test
    @Transactional
    public void createFirm() throws Exception {
        int databaseSizeBeforeCreate = firmRepository.findAll().size();
        // Create the Firm
        FirmDTO firmDTO = firmMapper.toDto(firm);
        restFirmMockMvc.perform(post("/api/firms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(firmDTO)))
            .andExpect(status().isCreated());

        // Validate the Firm in the database
        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeCreate + 1);
        Firm testFirm = firmList.get(firmList.size() - 1);
        assertThat(testFirm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFirm.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFirm.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testFirm.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFirmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = firmRepository.findAll().size();

        // Create the Firm with an existing ID
        firm.setId(1L);
        FirmDTO firmDTO = firmMapper.toDto(firm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFirmMockMvc.perform(post("/api/firms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(firmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Firm in the database
        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = firmRepository.findAll().size();
        // set the field null
        firm.setName(null);

        // Create the Firm, which fails.
        FirmDTO firmDTO = firmMapper.toDto(firm);


        restFirmMockMvc.perform(post("/api/firms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(firmDTO)))
            .andExpect(status().isBadRequest());

        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFirms() throws Exception {
        // Initialize the database
        firmRepository.saveAndFlush(firm);

        // Get all the firmList
        restFirmMockMvc.perform(get("/api/firms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(firm.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFirmsWithEagerRelationshipsIsEnabled() throws Exception {
        when(firmServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFirmMockMvc.perform(get("/api/firms?eagerload=true"))
            .andExpect(status().isOk());

        verify(firmServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFirmsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(firmServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFirmMockMvc.perform(get("/api/firms?eagerload=true"))
            .andExpect(status().isOk());

        verify(firmServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFirm() throws Exception {
        // Initialize the database
        firmRepository.saveAndFlush(firm);

        // Get the firm
        restFirmMockMvc.perform(get("/api/firms/{id}", firm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(firm.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingFirm() throws Exception {
        // Get the firm
        restFirmMockMvc.perform(get("/api/firms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFirm() throws Exception {
        // Initialize the database
        firmRepository.saveAndFlush(firm);

        int databaseSizeBeforeUpdate = firmRepository.findAll().size();

        // Update the firm
        Firm updatedFirm = firmRepository.findById(firm.getId()).get();
        // Disconnect from session so that the updates on updatedFirm are not directly saved in db
        em.detach(updatedFirm);
        updatedFirm
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        FirmDTO firmDTO = firmMapper.toDto(updatedFirm);

        restFirmMockMvc.perform(put("/api/firms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(firmDTO)))
            .andExpect(status().isOk());

        // Validate the Firm in the database
        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeUpdate);
        Firm testFirm = firmList.get(firmList.size() - 1);
        assertThat(testFirm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFirm.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFirm.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testFirm.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFirm() throws Exception {
        int databaseSizeBeforeUpdate = firmRepository.findAll().size();

        // Create the Firm
        FirmDTO firmDTO = firmMapper.toDto(firm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFirmMockMvc.perform(put("/api/firms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(firmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Firm in the database
        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFirm() throws Exception {
        // Initialize the database
        firmRepository.saveAndFlush(firm);

        int databaseSizeBeforeDelete = firmRepository.findAll().size();

        // Delete the firm
        restFirmMockMvc.perform(delete("/api/firms/{id}", firm.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Firm> firmList = firmRepository.findAll();
        assertThat(firmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
