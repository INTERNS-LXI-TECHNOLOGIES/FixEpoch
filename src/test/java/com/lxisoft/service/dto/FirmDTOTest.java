package com.lxisoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class FirmDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FirmDTO.class);
        FirmDTO firmDTO1 = new FirmDTO();
        firmDTO1.setId(1L);
        FirmDTO firmDTO2 = new FirmDTO();
        assertThat(firmDTO1).isNotEqualTo(firmDTO2);
        firmDTO2.setId(firmDTO1.getId());
        assertThat(firmDTO1).isEqualTo(firmDTO2);
        firmDTO2.setId(2L);
        assertThat(firmDTO1).isNotEqualTo(firmDTO2);
        firmDTO1.setId(null);
        assertThat(firmDTO1).isNotEqualTo(firmDTO2);
    }
}
