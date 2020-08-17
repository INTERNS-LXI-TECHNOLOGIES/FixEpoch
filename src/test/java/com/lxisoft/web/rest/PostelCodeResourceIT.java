package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.PostelCode;
import com.lxisoft.repository.PostelCodeRepository;
import com.lxisoft.service.PostelCodeService;
import com.lxisoft.service.dto.PostelCodeDTO;
import com.lxisoft.service.mapper.PostelCodeMapper;

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

/**
 * Integration tests for the {@link PostelCodeResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PostelCodeResourceIT {

    private static final String DEFAULT_POSTEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTEL_CODE = "BBBBBBBBBB";

    @Autowired
    private PostelCodeRepository postelCodeRepository;

    @Autowired
    private PostelCodeMapper postelCodeMapper;

    @Autowired
    private PostelCodeService postelCodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPostelCodeMockMvc;

    private PostelCode postelCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PostelCode createEntity(EntityManager em) {
        PostelCode postelCode = new PostelCode()
            .postelCode(DEFAULT_POSTEL_CODE);
        return postelCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PostelCode createUpdatedEntity(EntityManager em) {
        PostelCode postelCode = new PostelCode()
            .postelCode(UPDATED_POSTEL_CODE);
        return postelCode;
    }

    @BeforeEach
    public void initTest() {
        postelCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createPostelCode() throws Exception {
        int databaseSizeBeforeCreate = postelCodeRepository.findAll().size();
        // Create the PostelCode
        PostelCodeDTO postelCodeDTO = postelCodeMapper.toDto(postelCode);
        restPostelCodeMockMvc.perform(post("/api/postel-codes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(postelCodeDTO)))
            .andExpect(status().isCreated());

        // Validate the PostelCode in the database
        List<PostelCode> postelCodeList = postelCodeRepository.findAll();
        assertThat(postelCodeList).hasSize(databaseSizeBeforeCreate + 1);
        PostelCode testPostelCode = postelCodeList.get(postelCodeList.size() - 1);
        assertThat(testPostelCode.getPostelCode()).isEqualTo(DEFAULT_POSTEL_CODE);
    }

    @Test
    @Transactional
    public void createPostelCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = postelCodeRepository.findAll().size();

        // Create the PostelCode with an existing ID
        postelCode.setId(1L);
        PostelCodeDTO postelCodeDTO = postelCodeMapper.toDto(postelCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostelCodeMockMvc.perform(post("/api/postel-codes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(postelCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PostelCode in the database
        List<PostelCode> postelCodeList = postelCodeRepository.findAll();
        assertThat(postelCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPostelCodes() throws Exception {
        // Initialize the database
        postelCodeRepository.saveAndFlush(postelCode);

        // Get all the postelCodeList
        restPostelCodeMockMvc.perform(get("/api/postel-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(postelCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].postelCode").value(hasItem(DEFAULT_POSTEL_CODE)));
    }
    
    @Test
    @Transactional
    public void getPostelCode() throws Exception {
        // Initialize the database
        postelCodeRepository.saveAndFlush(postelCode);

        // Get the postelCode
        restPostelCodeMockMvc.perform(get("/api/postel-codes/{id}", postelCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(postelCode.getId().intValue()))
            .andExpect(jsonPath("$.postelCode").value(DEFAULT_POSTEL_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingPostelCode() throws Exception {
        // Get the postelCode
        restPostelCodeMockMvc.perform(get("/api/postel-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePostelCode() throws Exception {
        // Initialize the database
        postelCodeRepository.saveAndFlush(postelCode);

        int databaseSizeBeforeUpdate = postelCodeRepository.findAll().size();

        // Update the postelCode
        PostelCode updatedPostelCode = postelCodeRepository.findById(postelCode.getId()).get();
        // Disconnect from session so that the updates on updatedPostelCode are not directly saved in db
        em.detach(updatedPostelCode);
        updatedPostelCode
            .postelCode(UPDATED_POSTEL_CODE);
        PostelCodeDTO postelCodeDTO = postelCodeMapper.toDto(updatedPostelCode);

        restPostelCodeMockMvc.perform(put("/api/postel-codes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(postelCodeDTO)))
            .andExpect(status().isOk());

        // Validate the PostelCode in the database
        List<PostelCode> postelCodeList = postelCodeRepository.findAll();
        assertThat(postelCodeList).hasSize(databaseSizeBeforeUpdate);
        PostelCode testPostelCode = postelCodeList.get(postelCodeList.size() - 1);
        assertThat(testPostelCode.getPostelCode()).isEqualTo(UPDATED_POSTEL_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingPostelCode() throws Exception {
        int databaseSizeBeforeUpdate = postelCodeRepository.findAll().size();

        // Create the PostelCode
        PostelCodeDTO postelCodeDTO = postelCodeMapper.toDto(postelCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostelCodeMockMvc.perform(put("/api/postel-codes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(postelCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PostelCode in the database
        List<PostelCode> postelCodeList = postelCodeRepository.findAll();
        assertThat(postelCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePostelCode() throws Exception {
        // Initialize the database
        postelCodeRepository.saveAndFlush(postelCode);

        int databaseSizeBeforeDelete = postelCodeRepository.findAll().size();

        // Delete the postelCode
        restPostelCodeMockMvc.perform(delete("/api/postel-codes/{id}", postelCode.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PostelCode> postelCodeList = postelCodeRepository.findAll();
        assertThat(postelCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
