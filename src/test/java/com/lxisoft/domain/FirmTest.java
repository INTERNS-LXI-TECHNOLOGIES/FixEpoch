package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class FirmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Firm.class);
        Firm firm1 = new Firm();
        firm1.setId(1L);
        Firm firm2 = new Firm();
        firm2.setId(firm1.getId());
        assertThat(firm1).isEqualTo(firm2);
        firm2.setId(2L);
        assertThat(firm1).isNotEqualTo(firm2);
        firm1.setId(null);
        assertThat(firm1).isNotEqualTo(firm2);
    }
}
