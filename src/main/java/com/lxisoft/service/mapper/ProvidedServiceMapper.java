package com.lxisoft.service.mapper;


import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ProvidedServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProvidedService} and its DTO {@link ProvidedServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {FirmMapper.class})
public interface ProvidedServiceMapper extends EntityMapper<ProvidedServiceDTO, ProvidedService> {

    @Mapping(source = "firm.id", target = "firmId")
    ProvidedServiceDTO toDto(ProvidedService providedService);

    @Mapping(source = "firmId", target = "firm")
    ProvidedService toEntity(ProvidedServiceDTO providedServiceDTO);

    default ProvidedService fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProvidedService providedService = new ProvidedService();
        providedService.setId(id);
        return providedService;
    }
}
