package com.lxisoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class ProvidedServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvidedServiceDTO.class);
        ProvidedServiceDTO providedServiceDTO1 = new ProvidedServiceDTO();
        providedServiceDTO1.setId(1L);
        ProvidedServiceDTO providedServiceDTO2 = new ProvidedServiceDTO();
        assertThat(providedServiceDTO1).isNotEqualTo(providedServiceDTO2);
        providedServiceDTO2.setId(providedServiceDTO1.getId());
        assertThat(providedServiceDTO1).isEqualTo(providedServiceDTO2);
        providedServiceDTO2.setId(2L);
        assertThat(providedServiceDTO1).isNotEqualTo(providedServiceDTO2);
        providedServiceDTO1.setId(null);
        assertThat(providedServiceDTO1).isNotEqualTo(providedServiceDTO2);
    }
}
