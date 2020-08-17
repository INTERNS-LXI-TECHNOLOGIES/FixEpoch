package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.TimeSlot;
import com.lxisoft.domain.Firm;
import com.lxisoft.repository.TimeSlotRepository;
import com.lxisoft.service.TimeSlotService;
import com.lxisoft.service.dto.TimeSlotDTO;
import com.lxisoft.service.mapper.TimeSlotMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.domain.enumeration.TimeSlotLabel;
/**
 * Integration tests for the {@link TimeSlotResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TimeSlotResourceIT {

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_END_TIME = "BBBBBBBBBB";

    private static final TimeSlotLabel DEFAULT_TIME_SLOT_LABEL = TimeSlotLabel.MORNING;
    private static final TimeSlotLabel UPDATED_TIME_SLOT_LABEL = TimeSlotLabel.AFTERNOON;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTimeSlotMockMvc;

    private TimeSlot timeSlot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TimeSlot createEntity(EntityManager em) {
        TimeSlot timeSlot = new TimeSlot()
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .timeSlotLabel(DEFAULT_TIME_SLOT_LABEL);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        timeSlot.getFirms().add(firm);
        return timeSlot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TimeSlot createUpdatedEntity(EntityManager em) {
        TimeSlot timeSlot = new TimeSlot()
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .timeSlotLabel(UPDATED_TIME_SLOT_LABEL);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createUpdatedEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        timeSlot.getFirms().add(firm);
        return timeSlot;
    }

    @BeforeEach
    public void initTest() {
        timeSlot = createEntity(em);
    }

    @Test
    @Transactional
    public void createTimeSlot() throws Exception {
        int databaseSizeBeforeCreate = timeSlotRepository.findAll().size();
        // Create the TimeSlot
        TimeSlotDTO timeSlotDTO = timeSlotMapper.toDto(timeSlot);
        restTimeSlotMockMvc.perform(post("/api/time-slots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timeSlotDTO)))
            .andExpect(status().isCreated());

        // Validate the TimeSlot in the database
        List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
        assertThat(timeSlotList).hasSize(databaseSizeBeforeCreate + 1);
        TimeSlot testTimeSlot = timeSlotList.get(timeSlotList.size() - 1);
        assertThat(testTimeSlot.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testTimeSlot.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testTimeSlot.getTimeSlotLabel()).isEqualTo(DEFAULT_TIME_SLOT_LABEL);
    }

    @Test
    @Transactional
    public void createTimeSlotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = timeSlotRepository.findAll().size();

        // Create the TimeSlot with an existing ID
        timeSlot.setId(1L);
        TimeSlotDTO timeSlotDTO = timeSlotMapper.toDto(timeSlot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimeSlotMockMvc.perform(post("/api/time-slots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timeSlotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TimeSlot in the database
        List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
        assertThat(timeSlotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTimeSlots() throws Exception {
        // Initialize the database
        timeSlotRepository.saveAndFlush(timeSlot);

        // Get all the timeSlotList
        restTimeSlotMockMvc.perform(get("/api/time-slots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timeSlot.getId().intValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME)))
            .andExpect(jsonPath("$.[*].timeSlotLabel").value(hasItem(DEFAULT_TIME_SLOT_LABEL.toString())));
    }
    
    @Test
    @Transactional
    public void getTimeSlot() throws Exception {
        // Initialize the database
        timeSlotRepository.saveAndFlush(timeSlot);

        // Get the timeSlot
        restTimeSlotMockMvc.perform(get("/api/time-slots/{id}", timeSlot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(timeSlot.getId().intValue()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME))
            .andExpect(jsonPath("$.timeSlotLabel").value(DEFAULT_TIME_SLOT_LABEL.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTimeSlot() throws Exception {
        // Get the timeSlot
        restTimeSlotMockMvc.perform(get("/api/time-slots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTimeSlot() throws Exception {
        // Initialize the database
        timeSlotRepository.saveAndFlush(timeSlot);

        int databaseSizeBeforeUpdate = timeSlotRepository.findAll().size();

        // Update the timeSlot
        TimeSlot updatedTimeSlot = timeSlotRepository.findById(timeSlot.getId()).get();
        // Disconnect from session so that the updates on updatedTimeSlot are not directly saved in db
        em.detach(updatedTimeSlot);
        updatedTimeSlot
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .timeSlotLabel(UPDATED_TIME_SLOT_LABEL);
        TimeSlotDTO timeSlotDTO = timeSlotMapper.toDto(updatedTimeSlot);

        restTimeSlotMockMvc.perform(put("/api/time-slots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timeSlotDTO)))
            .andExpect(status().isOk());

        // Validate the TimeSlot in the database
        List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
        assertThat(timeSlotList).hasSize(databaseSizeBeforeUpdate);
        TimeSlot testTimeSlot = timeSlotList.get(timeSlotList.size() - 1);
        assertThat(testTimeSlot.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testTimeSlot.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testTimeSlot.getTimeSlotLabel()).isEqualTo(UPDATED_TIME_SLOT_LABEL);
    }

    @Test
    @Transactional
    public void updateNonExistingTimeSlot() throws Exception {
        int databaseSizeBeforeUpdate = timeSlotRepository.findAll().size();

        // Create the TimeSlot
        TimeSlotDTO timeSlotDTO = timeSlotMapper.toDto(timeSlot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimeSlotMockMvc.perform(put("/api/time-slots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timeSlotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TimeSlot in the database
        List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
        assertThat(timeSlotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTimeSlot() throws Exception {
        // Initialize the database
        timeSlotRepository.saveAndFlush(timeSlot);

        int databaseSizeBeforeDelete = timeSlotRepository.findAll().size();

        // Delete the timeSlot
        restTimeSlotMockMvc.perform(delete("/api/time-slots/{id}", timeSlot.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TimeSlot> timeSlotList = timeSlotRepository.findAll();
        assertThat(timeSlotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
