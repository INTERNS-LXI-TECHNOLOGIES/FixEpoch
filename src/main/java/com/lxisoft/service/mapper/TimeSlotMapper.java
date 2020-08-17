package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.TimeSlotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TimeSlot} and its DTO {@link TimeSlotDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TimeSlotMapper extends EntityMapper<TimeSlotDTO, TimeSlot> {


    @Mapping(target = "firms", ignore = true)
    @Mapping(target = "removeFirm", ignore = true)
    TimeSlot toEntity(TimeSlotDTO timeSlotDTO);

    default TimeSlot fromId(Long id) {
        if (id == null) {
            return null;
        }
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(id);
        return timeSlot;
    }
}
