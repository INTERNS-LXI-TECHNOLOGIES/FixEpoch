package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.FirmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Firm} and its DTO {@link FirmDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, TimeSlotMapper.class, CategoryMapper.class, CustomerMapper.class})
public interface FirmMapper extends EntityMapper<FirmDTO, Firm> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "customer.id", target = "customerId")
    FirmDTO toDto(Firm firm);

    @Mapping(source = "addressId", target = "address")
    @Mapping(target = "providedServices", ignore = true)
    @Mapping(target = "removeProvidedService", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "removeAppointments", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "removeEmployee", ignore = true)
    @Mapping(target = "removeTimeslot", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "customerId", target = "customer")
    Firm toEntity(FirmDTO firmDTO);

    default Firm fromId(Long id) {
        if (id == null) {
            return null;
        }
        Firm firm = new Firm();
        firm.setId(id);
        return firm;
    }
}
