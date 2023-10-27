package com.valantic;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KategorieMapper {
    Kategorie toEntity(KategorieDto dto);

    KategorieDto toDto(Kategorie entity);
}
