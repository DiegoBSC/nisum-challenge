package com.nisum.service.apirestusers.dto.phone;

import com.nisum.service.apirestusers.model.Phone;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface PhoneMapper {
    PhoneDto toDto(Phone model);

    Phone toModel(PhoneDto dto);
}
