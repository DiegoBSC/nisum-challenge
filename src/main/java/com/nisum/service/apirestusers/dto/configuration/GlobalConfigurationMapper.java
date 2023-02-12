package com.nisum.service.apirestusers.dto.configuration;

import com.nisum.service.apirestusers.model.GlobalConfiguration;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface GlobalConfigurationMapper {

    GlobalConfigurationDto toDto(GlobalConfiguration model);

    GlobalConfiguration toModel(GlobalConfigurationDto dto);
}
