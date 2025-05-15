package com.example.hiringapp.mappers;

import com.example.hiringapp.dto.EducationDTO;
import com.example.hiringapp.entity.EducationEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface EducationMapper {
    @Mapping( target="id",ignore=true)
    @Mapping( target ="candidate", ignore=true)
    EducationEntity toEntity(EducationDTO dto);
    EducationDTO toDto(EducationEntity entity);
}

