package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class ProvidedServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvidedService.class);
        ProvidedService providedService1 = new ProvidedService();
        providedService1.setId(1L);
        ProvidedService providedService2 = new ProvidedService();
        providedService2.setId(providedService1.getId());
        assertThat(providedService1).isEqualTo(providedService2);
        providedService2.setId(2L);
        assertThat(providedService1).isNotEqualTo(providedService2);
        providedService1.setId(null);
        assertThat(providedService1).isNotEqualTo(providedService2);
    }
}
