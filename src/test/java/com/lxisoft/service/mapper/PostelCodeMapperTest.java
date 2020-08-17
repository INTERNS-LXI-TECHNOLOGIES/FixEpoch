package com.lxisoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PostelCodeMapperTest {

    private PostelCodeMapper postelCodeMapper;

    @BeforeEach
    public void setUp() {
        postelCodeMapper = new PostelCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(postelCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(postelCodeMapper.fromId(null)).isNull();
    }
}
