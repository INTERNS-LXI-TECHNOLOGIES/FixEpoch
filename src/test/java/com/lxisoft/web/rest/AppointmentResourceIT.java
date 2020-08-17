package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.Appointment;
import com.lxisoft.domain.TimeSlot;
import com.lxisoft.domain.Employee;
import com.lxisoft.domain.ProvidedService;
import com.lxisoft.domain.Firm;
import com.lxisoft.domain.UserExtra;
import com.lxisoft.repository.AppointmentRepository;
import com.lxisoft.service.AppointmentService;
import com.lxisoft.service.dto.AppointmentDTO;
import com.lxisoft.service.mapper.AppointmentMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lxisoft.domain.enumeration.AppointmentVerifiedStatus;
/**
 * Integration tests for the {@link AppointmentResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AppointmentResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final AppointmentVerifiedStatus DEFAULT_APPOINTMENT_STATUS = AppointmentVerifiedStatus.APPROVED;
    private static final AppointmentVerifiedStatus UPDATED_APPOINTMENT_STATUS = AppointmentVerifiedStatus.PENDING;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppointmentMockMvc;

    private Appointment appointment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Appointment createEntity(EntityManager em) {
        Appointment appointment = new Appointment()
            .date(DEFAULT_DATE)
            .appointmentStatus(DEFAULT_APPOINTMENT_STATUS);
        // Add required entity
        TimeSlot timeSlot;
        if (TestUtil.findAll(em, TimeSlot.class).isEmpty()) {
            timeSlot = TimeSlotResourceIT.createEntity(em);
            em.persist(timeSlot);
            em.flush();
        } else {
            timeSlot = TestUtil.findAll(em, TimeSlot.class).get(0);
        }
        appointment.setTimeSlot(timeSlot);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        appointment.setEmployee(employee);
        // Add required entity
        ProvidedService providedService;
        if (TestUtil.findAll(em, ProvidedService.class).isEmpty()) {
            providedService = ProvidedServiceResourceIT.createEntity(em);
            em.persist(providedService);
            em.flush();
        } else {
            providedService = TestUtil.findAll(em, ProvidedService.class).get(0);
        }
        appointment.setProvidedService(providedService);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        appointment.setFirm(firm);
        // Add required entity
        UserExtra userExtra;
        if (TestUtil.findAll(em, UserExtra.class).isEmpty()) {
            userExtra = UserExtraResourceIT.createEntity(em);
            em.persist(userExtra);
            em.flush();
        } else {
            userExtra = TestUtil.findAll(em, UserExtra.class).get(0);
        }
        appointment.setUserExtra(userExtra);
        return appointment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Appointment createUpdatedEntity(EntityManager em) {
        Appointment appointment = new Appointment()
            .date(UPDATED_DATE)
            .appointmentStatus(UPDATED_APPOINTMENT_STATUS);
        // Add required entity
        TimeSlot timeSlot;
        if (TestUtil.findAll(em, TimeSlot.class).isEmpty()) {
            timeSlot = TimeSlotResourceIT.createUpdatedEntity(em);
            em.persist(timeSlot);
            em.flush();
        } else {
            timeSlot = TestUtil.findAll(em, TimeSlot.class).get(0);
        }
        appointment.setTimeSlot(timeSlot);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createUpdatedEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        appointment.setEmployee(employee);
        // Add required entity
        ProvidedService providedService;
        if (TestUtil.findAll(em, ProvidedService.class).isEmpty()) {
            providedService = ProvidedServiceResourceIT.createUpdatedEntity(em);
            em.persist(providedService);
            em.flush();
        } else {
            providedService = TestUtil.findAll(em, ProvidedService.class).get(0);
        }
        appointment.setProvidedService(providedService);
        // Add required entity
        Firm firm;
        if (TestUtil.findAll(em, Firm.class).isEmpty()) {
            firm = FirmResourceIT.createUpdatedEntity(em);
            em.persist(firm);
            em.flush();
        } else {
            firm = TestUtil.findAll(em, Firm.class).get(0);
        }
        appointment.setFirm(firm);
        // Add required entity
        UserExtra userExtra;
        if (TestUtil.findAll(em, UserExtra.class).isEmpty()) {
            userExtra = UserExtraResourceIT.createUpdatedEntity(em);
            em.persist(userExtra);
            em.flush();
        } else {
            userExtra = TestUtil.findAll(em, UserExtra.class).get(0);
        }
        appointment.setUserExtra(userExtra);
        return appointment;
    }

    @BeforeEach
    public void initTest() {
        appointment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointment() throws Exception {
        int databaseSizeBeforeCreate = appointmentRepository.findAll().size();
        // Create the Appointment
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);
        restAppointmentMockMvc.perform(post("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeCreate + 1);
        Appointment testAppointment = appointmentList.get(appointmentList.size() - 1);
        assertThat(testAppointment.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAppointment.getAppointmentStatus()).isEqualTo(DEFAULT_APPOINTMENT_STATUS);
    }

    @Test
    @Transactional
    public void createAppointmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentRepository.findAll().size();

        // Create the Appointment with an existing ID
        appointment.setId(1L);
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentMockMvc.perform(post("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentRepository.findAll().size();
        // set the field null
        appointment.setDate(null);

        // Create the Appointment, which fails.
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);


        restAppointmentMockMvc.perform(post("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAppointmentStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentRepository.findAll().size();
        // set the field null
        appointment.setAppointmentStatus(null);

        // Create the Appointment, which fails.
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);


        restAppointmentMockMvc.perform(post("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppointments() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        // Get all the appointmentList
        restAppointmentMockMvc.perform(get("/api/appointments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointment.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].appointmentStatus").value(hasItem(DEFAULT_APPOINTMENT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        // Get the appointment
        restAppointmentMockMvc.perform(get("/api/appointments/{id}", appointment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appointment.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.appointmentStatus").value(DEFAULT_APPOINTMENT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAppointment() throws Exception {
        // Get the appointment
        restAppointmentMockMvc.perform(get("/api/appointments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        int databaseSizeBeforeUpdate = appointmentRepository.findAll().size();

        // Update the appointment
        Appointment updatedAppointment = appointmentRepository.findById(appointment.getId()).get();
        // Disconnect from session so that the updates on updatedAppointment are not directly saved in db
        em.detach(updatedAppointment);
        updatedAppointment
            .date(UPDATED_DATE)
            .appointmentStatus(UPDATED_APPOINTMENT_STATUS);
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(updatedAppointment);

        restAppointmentMockMvc.perform(put("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isOk());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeUpdate);
        Appointment testAppointment = appointmentList.get(appointmentList.size() - 1);
        assertThat(testAppointment.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAppointment.getAppointmentStatus()).isEqualTo(UPDATED_APPOINTMENT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointment() throws Exception {
        int databaseSizeBeforeUpdate = appointmentRepository.findAll().size();

        // Create the Appointment
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppointmentMockMvc.perform(put("/api/appointments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        int databaseSizeBeforeDelete = appointmentRepository.findAll().size();

        // Delete the appointment
        restAppointmentMockMvc.perform(delete("/api/appointments/{id}", appointment.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
