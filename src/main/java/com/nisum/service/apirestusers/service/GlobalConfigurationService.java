package com.nisum.service.apirestusers.service;

import com.nisum.service.apirestusers.dto.configuration.GlobalConfigurationDto;
import com.nisum.service.apirestusers.enums.GlobalConfigurationEnum;

import java.util.List;
import java.util.UUID;

public interface GlobalConfigurationService {
    GlobalConfigurationDto createGlobalConfiguration(GlobalConfigurationDto globalConfigurationDto);

    List<GlobalConfigurationDto> getAllGlobalConfigurations();

    GlobalConfigurationDto updateGlobalConfiguration(UUID globalConfigurationId, GlobalConfigurationDto globalConfigurationDto);

    String getPatternByName(GlobalConfigurationEnum name);
}
