package com.example.usertesthometask.mapper;

import com.example.usertesthometask.dto.MedicineRequestDto;
import com.example.usertesthometask.dto.MedicineResponseDto;
import com.example.usertesthometask.model.MedicineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(target = "id", ignore = true)
    MedicineEntity toEntity(MedicineRequestDto dto);

    MedicineResponseDto toDto(MedicineEntity entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget MedicineEntity entity, MedicineRequestDto dto);
}
