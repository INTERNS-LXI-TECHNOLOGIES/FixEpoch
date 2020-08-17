package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FirmMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "firm.id", target = "firmId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "firmId", target = "firm")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
