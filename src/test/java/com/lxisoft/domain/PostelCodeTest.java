package com.lxisoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lxisoft.web.rest.TestUtil;

public class PostelCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostelCode.class);
        PostelCode postelCode1 = new PostelCode();
        postelCode1.setId(1L);
        PostelCode postelCode2 = new PostelCode();
        postelCode2.setId(postelCode1.getId());
        assertThat(postelCode1).isEqualTo(postelCode2);
        postelCode2.setId(2L);
        assertThat(postelCode1).isNotEqualTo(postelCode2);
        postelCode1.setId(null);
        assertThat(postelCode1).isNotEqualTo(postelCode2);
    }
}
