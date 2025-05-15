package com.example.hiringapp.mappers;

import com.example.hiringapp.dto.DocumentDTO;
import com.example.hiringapp.entity.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target="candidate",ignore=true)
    @Mapping(target="id",ignore=true)
    @Mapping(target="fileUrl",ignore=true)
    @Mapping(target="verified",ignore=true)


        //DocumentDTO toDTO(DocumentEntity documentEntity);

    DocumentEntity toEntity(DocumentDTO documentDTO);

}
