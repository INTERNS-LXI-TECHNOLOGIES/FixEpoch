package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.PostelCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostelCode} and its DTO {@link PostelCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostelCodeMapper extends EntityMapper<PostelCodeDTO, PostelCode> {



    default PostelCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        PostelCode postelCode = new PostelCode();
        postelCode.setId(id);
        return postelCode;
    }
}
