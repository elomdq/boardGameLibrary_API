package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import org.mapstruct.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Mapper(uses = BoardGameSlimMapper.class)
public interface DesignerMapper {


    public DesignerDto toDto(Designer designer);
    public Designer toEntity(DesignerDto designerDto);
    public Set<Designer> toEntities (Set<DesignerDto> designerDtos);
    public Set<DesignerDto> toDtos(Set<Designer> designers);


    //METODOS PARA USO DE OTRAS ENTIDADES

    /*Metodo para ignorar los child BoardGame de las entidades Designer y asi evitar la recursividad en el mappeo de los BoardGame*/
    @Named("toDtosIgnoreGames")
    @IterableMapping(qualifiedByName = "toDtoIgnoreGames")
    public Set<DesignerDto> toDtosIgnoreGames(Set<Designer> designers);

    @Named("toEntitiesIgnoreGames")
    @IterableMapping(qualifiedByName = "toEntityIgnoreGames")
    public Set<Designer> toEntitiesIgnoreGames(Set<DesignerDto> designersDtos);

    @Named("toDtoIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public DesignerDto toDtoIgnoreGames(Designer designer);
    @Named("toEntityIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public Designer toEntityIgnoreGames(DesignerDto designerDto);
}
