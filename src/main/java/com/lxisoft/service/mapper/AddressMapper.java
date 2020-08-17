package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {PostelCodeMapper.class, CityMapper.class, StateMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "postalCode.id", target = "postalCodeId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "state.id", target = "stateId")
    AddressDTO toDto(Address address);

    @Mapping(source = "postalCodeId", target = "postalCode")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "stateId", target = "state")
    @Mapping(target = "userExtra", ignore = true)
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
