package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.StateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link State} and its DTO {@link StateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StateMapper extends EntityMapper<StateDTO, State> {



    default State fromId(Long id) {
        if (id == null) {
            return null;
        }
        State state = new State();
        state.setId(id);
        return state;
    }
}
