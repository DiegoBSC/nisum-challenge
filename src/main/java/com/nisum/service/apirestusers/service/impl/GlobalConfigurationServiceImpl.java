package com.nisum.service.apirestusers.service.impl;

import com.nisum.service.apirestusers.dto.configuration.GlobalConfigurationDto;
import com.nisum.service.apirestusers.dto.configuration.GlobalConfigurationMapper;
import com.nisum.service.apirestusers.enums.GlobalConfigurationEnum;
import com.nisum.service.apirestusers.model.GlobalConfiguration;
import com.nisum.service.apirestusers.repository.GlobalConfigurationRepository;
import com.nisum.service.apirestusers.service.GlobalConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GlobalConfigurationServiceImpl implements GlobalConfigurationService {

    private final GlobalConfigurationRepository globalConfigurationRepository;

    private  final GlobalConfigurationMapper globalConfigurationMapper;

    @Override
    public GlobalConfigurationDto createGlobalConfiguration(GlobalConfigurationDto globalConfigurationDto) {
        Optional<GlobalConfiguration> existingGlobalConfiguration = globalConfigurationRepository.findByName(globalConfigurationDto.getName());
        existingGlobalConfiguration.ifPresent(x -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una Configuración con el mismo nombre");
        });
        GlobalConfiguration globalConfiguration  = globalConfigurationMapper.toModel(globalConfigurationDto);
        return globalConfigurationMapper.toDto(globalConfigurationRepository.save(globalConfiguration));
    }

    @Override
    public GlobalConfigurationDto updateGlobalConfiguration(UUID globalConfigurationId, GlobalConfigurationDto globalConfigurationDto) {
        GlobalConfiguration globalConfiguration = globalConfigurationRepository.findById(globalConfigurationId)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "El registro no existe"));

        globalConfiguration.setName(globalConfigurationDto.getName());
        globalConfiguration.setDescription(globalConfigurationDto.getDescription());
        globalConfiguration.setPattern(globalConfigurationDto.getPattern());
        return globalConfigurationMapper.toDto(globalConfigurationRepository.save(globalConfiguration));
    }

    @Override
    public String getPatternByName(GlobalConfigurationEnum name) {
        GlobalConfiguration globalConfiguration = globalConfigurationRepository.findByName(name)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Error: debe configurar el patron: %s", name)));

        return globalConfiguration.getPattern();
    }
}
