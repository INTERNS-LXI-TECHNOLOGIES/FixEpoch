package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.AppointmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Appointment} and its DTO {@link AppointmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TimeSlotMapper.class, EmployeeMapper.class, ProvidedServiceMapper.class, FirmMapper.class, UserExtraMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "timeSlot.id", target = "timeSlotId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "providedService.id", target = "providedServiceId")
    @Mapping(source = "firm.id", target = "firmId")
    @Mapping(source = "userExtra.id", target = "userExtraId")
    AppointmentDTO toDto(Appointment appointment);

    @Mapping(source = "timeSlotId", target = "timeSlot")
    @Mapping(source = "employeeId", target = "employee")
    @Mapping(source = "providedServiceId", target = "providedService")
    @Mapping(source = "firmId", target = "firm")
    @Mapping(source = "userExtraId", target = "userExtra")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    default Appointment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(id);
        return appointment;
    }
}
