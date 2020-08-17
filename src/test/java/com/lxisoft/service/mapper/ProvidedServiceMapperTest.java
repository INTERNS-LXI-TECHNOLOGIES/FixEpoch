package com.lxisoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProvidedServiceMapperTest {

    private ProvidedServiceMapper providedServiceMapper;

    @BeforeEach
    public void setUp() {
        providedServiceMapper = new ProvidedServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(providedServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(providedServiceMapper.fromId(null)).isNull();
    }
}
