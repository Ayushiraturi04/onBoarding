package com.example.hiringapp.mappers;

import com.example.hiringapp.dto.PersonalDTO;
import com.example.hiringapp.entity.PersonalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface PersonalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    PersonalEntity toEntity(PersonalDTO personalDTO);
    PersonalDTO toDTO(PersonalEntity personalEntity);
}