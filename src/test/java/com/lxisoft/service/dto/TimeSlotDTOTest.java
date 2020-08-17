package com.lxisoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class TimeSlotDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimeSlotDTO.class);
        TimeSlotDTO timeSlotDTO1 = new TimeSlotDTO();
        timeSlotDTO1.setId(1L);
        TimeSlotDTO timeSlotDTO2 = new TimeSlotDTO();
        assertThat(timeSlotDTO1).isNotEqualTo(timeSlotDTO2);
        timeSlotDTO2.setId(timeSlotDTO1.getId());
        assertThat(timeSlotDTO1).isEqualTo(timeSlotDTO2);
        timeSlotDTO2.setId(2L);
        assertThat(timeSlotDTO1).isNotEqualTo(timeSlotDTO2);
        timeSlotDTO1.setId(null);
        assertThat(timeSlotDTO1).isNotEqualTo(timeSlotDTO2);
    }
}
