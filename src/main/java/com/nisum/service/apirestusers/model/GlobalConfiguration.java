package com.nisum.service.apirestusers.model;

import com.nisum.service.apirestusers.enums.GlobalConfigurationEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "global_configurations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalConfigurationEnum name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String pattern;
}
