package com.nisum.service.apirestusers.repository;

import com.nisum.service.apirestusers.enums.GlobalConfigurationEnum;
import com.nisum.service.apirestusers.model.GlobalConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GlobalConfigurationRepository extends CrudRepository<GlobalConfiguration, UUID> {
    Optional<GlobalConfiguration> findByName(GlobalConfigurationEnum name);
}
