package com.lxisoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FirmMapperTest {

    private FirmMapper firmMapper;

    @BeforeEach
    public void setUp() {
        firmMapper = new FirmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(firmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(firmMapper.fromId(null)).isNull();
    }
}
