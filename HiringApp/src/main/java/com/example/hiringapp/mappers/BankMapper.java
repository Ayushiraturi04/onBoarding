package com.example.hiringapp.mappers;

import com.example.hiringapp.dto.BankDTO;
import com.example.hiringapp.entity.BankEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {
    @Mapping(target="id", ignore = true)
    @Mapping(target="candidate", ignore = true)
    BankEntity toEntity(BankDTO dto);
    BankDTO toDTO(BankEntity entity);
}
