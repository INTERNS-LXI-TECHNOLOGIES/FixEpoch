package com.lxisoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class PostelCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostelCodeDTO.class);
        PostelCodeDTO postelCodeDTO1 = new PostelCodeDTO();
        postelCodeDTO1.setId(1L);
        PostelCodeDTO postelCodeDTO2 = new PostelCodeDTO();
        assertThat(postelCodeDTO1).isNotEqualTo(postelCodeDTO2);
        postelCodeDTO2.setId(postelCodeDTO1.getId());
        assertThat(postelCodeDTO1).isEqualTo(postelCodeDTO2);
        postelCodeDTO2.setId(2L);
        assertThat(postelCodeDTO1).isNotEqualTo(postelCodeDTO2);
        postelCodeDTO1.setId(null);
        assertThat(postelCodeDTO1).isNotEqualTo(postelCodeDTO2);
    }
}
