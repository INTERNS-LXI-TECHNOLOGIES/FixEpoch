package com.lxisoft.web.rest;

import com.lxisoft.FixEpochApp;
import com.lxisoft.domain.Address;
import com.lxisoft.domain.PostelCode;
import com.lxisoft.domain.City;
import com.lxisoft.domain.State;
import com.lxisoft.domain.UserExtra;
import com.lxisoft.repository.AddressRepository;
import com.lxisoft.service.AddressService;
import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.mapper.AddressMapper;

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
 * Integration tests for the {@link AddressResource} REST controller.
 */
@SpringBootTest(classes = FixEpochApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AddressResourceIT {

    private static final String DEFAULT_LOCATION_ADDRESS_LINE_ONE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ADDRESS_LINE_ONE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_ADDRESS_LINE_TWO = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ADDRESS_LINE_TWO = "BBBBBBBBBB";

    private static final String DEFAULT_LAND_MARK = "AAAAAAAAAA";
    private static final String UPDATED_LAND_MARK = "BBBBBBBBBB";

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressMockMvc;

    private Address address;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createEntity(EntityManager em) {
        Address address = new Address()
            .locationAddressLineOne(DEFAULT_LOCATION_ADDRESS_LINE_ONE)
            .locationAddressLineTwo(DEFAULT_LOCATION_ADDRESS_LINE_TWO)
            .landMark(DEFAULT_LAND_MARK);
        // Add required entity
        PostelCode postelCode;
        if (TestUtil.findAll(em, PostelCode.class).isEmpty()) {
            postelCode = PostelCodeResourceIT.createEntity(em);
            em.persist(postelCode);
            em.flush();
        } else {
            postelCode = TestUtil.findAll(em, PostelCode.class).get(0);
        }
        address.setPostalCode(postelCode);
        // Add required entity
        City city;
        if (TestUtil.findAll(em, City.class).isEmpty()) {
            city = CityResourceIT.createEntity(em);
            em.persist(city);
            em.flush();
        } else {
            city = TestUtil.findAll(em, City.class).get(0);
        }
        address.setCity(city);
        // Add required entity
        State state;
        if (TestUtil.findAll(em, State.class).isEmpty()) {
            state = StateResourceIT.createEntity(em);
            em.persist(state);
            em.flush();
        } else {
            state = TestUtil.findAll(em, State.class).get(0);
        }
        address.setState(state);
        // Add required entity
        UserExtra userExtra;
        if (TestUtil.findAll(em, UserExtra.class).isEmpty()) {
            userExtra = UserExtraResourceIT.createEntity(em);
            em.persist(userExtra);
            em.flush();
        } else {
            userExtra = TestUtil.findAll(em, UserExtra.class).get(0);
        }
        address.setUserExtra(userExtra);
        return address;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createUpdatedEntity(EntityManager em) {
        Address address = new Address()
            .locationAddressLineOne(UPDATED_LOCATION_ADDRESS_LINE_ONE)
            .locationAddressLineTwo(UPDATED_LOCATION_ADDRESS_LINE_TWO)
            .landMark(UPDATED_LAND_MARK);
        // Add required entity
        PostelCode postelCode;
        if (TestUtil.findAll(em, PostelCode.class).isEmpty()) {
            postelCode = PostelCodeResourceIT.createUpdatedEntity(em);
            em.persist(postelCode);
            em.flush();
        } else {
            postelCode = TestUtil.findAll(em, PostelCode.class).get(0);
        }
        address.setPostalCode(postelCode);
        // Add required entity
        City city;
        if (TestUtil.findAll(em, City.class).isEmpty()) {
            city = CityResourceIT.createUpdatedEntity(em);
            em.persist(city);
            em.flush();
        } else {
            city = TestUtil.findAll(em, City.class).get(0);
        }
        address.setCity(city);
        // Add required entity
        State state;
        if (TestUtil.findAll(em, State.class).isEmpty()) {
            state = StateResourceIT.createUpdatedEntity(em);
            em.persist(state);
            em.flush();
        } else {
            state = TestUtil.findAll(em, State.class).get(0);
        }
        address.setState(state);
        // Add required entity
        UserExtra userExtra;
        if (TestUtil.findAll(em, UserExtra.class).isEmpty()) {
            userExtra = UserExtraResourceIT.createUpdatedEntity(em);
            em.persist(userExtra);
            em.flush();
        } else {
            userExtra = TestUtil.findAll(em, UserExtra.class).get(0);
        }
        address.setUserExtra(userExtra);
        return address;
    }

    @BeforeEach
    public void initTest() {
        address = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddress() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();
        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);
        restAddressMockMvc.perform(post("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate + 1);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getLocationAddressLineOne()).isEqualTo(DEFAULT_LOCATION_ADDRESS_LINE_ONE);
        assertThat(testAddress.getLocationAddressLineTwo()).isEqualTo(DEFAULT_LOCATION_ADDRESS_LINE_TWO);
        assertThat(testAddress.getLandMark()).isEqualTo(DEFAULT_LAND_MARK);
    }

    @Test
    @Transactional
    public void createAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address with an existing ID
        address.setId(1L);
        AddressDTO addressDTO = addressMapper.toDto(address);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressMockMvc.perform(post("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get all the addressList
        restAddressMockMvc.perform(get("/api/addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(address.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationAddressLineOne").value(hasItem(DEFAULT_LOCATION_ADDRESS_LINE_ONE)))
            .andExpect(jsonPath("$.[*].locationAddressLineTwo").value(hasItem(DEFAULT_LOCATION_ADDRESS_LINE_TWO)))
            .andExpect(jsonPath("$.[*].landMark").value(hasItem(DEFAULT_LAND_MARK)));
    }
    
    @Test
    @Transactional
    public void getAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(address.getId().intValue()))
            .andExpect(jsonPath("$.locationAddressLineOne").value(DEFAULT_LOCATION_ADDRESS_LINE_ONE))
            .andExpect(jsonPath("$.locationAddressLineTwo").value(DEFAULT_LOCATION_ADDRESS_LINE_TWO))
            .andExpect(jsonPath("$.landMark").value(DEFAULT_LAND_MARK));
    }
    @Test
    @Transactional
    public void getNonExistingAddress() throws Exception {
        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Update the address
        Address updatedAddress = addressRepository.findById(address.getId()).get();
        // Disconnect from session so that the updates on updatedAddress are not directly saved in db
        em.detach(updatedAddress);
        updatedAddress
            .locationAddressLineOne(UPDATED_LOCATION_ADDRESS_LINE_ONE)
            .locationAddressLineTwo(UPDATED_LOCATION_ADDRESS_LINE_TWO)
            .landMark(UPDATED_LAND_MARK);
        AddressDTO addressDTO = addressMapper.toDto(updatedAddress);

        restAddressMockMvc.perform(put("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isOk());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getLocationAddressLineOne()).isEqualTo(UPDATED_LOCATION_ADDRESS_LINE_ONE);
        assertThat(testAddress.getLocationAddressLineTwo()).isEqualTo(UPDATED_LOCATION_ADDRESS_LINE_TWO);
        assertThat(testAddress.getLandMark()).isEqualTo(UPDATED_LAND_MARK);
    }

    @Test
    @Transactional
    public void updateNonExistingAddress() throws Exception {
        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressMockMvc.perform(put("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeDelete = addressRepository.findAll().size();

        // Delete the address
        restAddressMockMvc.perform(delete("/api/addresses/{id}", address.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
