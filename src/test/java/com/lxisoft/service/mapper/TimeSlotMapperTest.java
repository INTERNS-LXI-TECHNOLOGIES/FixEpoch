package com.lxisoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeSlotMapperTest {

    private TimeSlotMapper timeSlotMapper;

    @BeforeEach
    public void setUp() {
        timeSlotMapper = new TimeSlotMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(timeSlotMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(timeSlotMapper.fromId(null)).isNull();
    }
}
