package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.ArtistDto;
import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import org.mapstruct.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Set;

@Mapper(uses = BoardGameSlimMapper.class/*, injectionStrategy = InjectionStrategy.FIELD*/)
public interface ArtistMapper {
    //@Mapping(target= "games", qualifiedByName = "toDtosIgnoreChildLists")
    public ArtistDto toDto(Artist artist);

   // @Mapping(target= "games", qualifiedByName = "toEntitiesIgnoreChildLists")
    public Artist toEntity(ArtistDto artistDto);

    public Set<Artist> toEntities (Set<ArtistDto> artistDtos);
    public Set<ArtistDto> toDtos(Set<Artist> artists);


    //METODOS PARA USO DE OTRAS ENTIDADES

    /*Metodo para ignorar los child BoardGame de las entidades Artist y asi evitar la recursividad en el mappeo de los BoardGame*/
    @Named("toDtosIgnoreGames")
    @IterableMapping(qualifiedByName = "toDtoIgnoreGames")
    public Set<ArtistDto> toDtosIgnoreGames(Set<Artist> artists);

    @Named("toEntitiesIgnoreGames")
    @IterableMapping(qualifiedByName = "toEntityIgnoreGames")
    public Set<Artist> toEntitiesIgnoreGames(Set<ArtistDto> artistDtos);

    @Named("toDtoIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public ArtistDto toDtoIgnoreGames(Artist artist);
    @Named("toEntityIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public Artist toEntityIgnoreGames(ArtistDto artistDto);
}
