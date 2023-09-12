package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import org.mapstruct.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(uses = BoardGameSlimMapper.class)
public interface PublisherMapper {

    //@Mapping(target= "games", qualifiedByName = "toEntitiesIgnoreChildLists")
    public Publisher toEntity (PublisherDto publisherDto);

    //@Mapping(target= "games", qualifiedByName = "toDtosIgnoreChildLists")
    public PublisherDto toDto(Publisher publisher);

    Set<Publisher> toEntities (Set<PublisherDto> publisherDtos);
    Set<PublisherDto> toDtos (Set<Publisher> publishers);


    //METODOS A USAR POR OTROS MAPPERS DE OTRAS ENTIDADES

    @Named("toDtoIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public PublisherDto toDtoIgnoreGames(Publisher publisher);

    @Named("toEntityIgnoreGames")
    @Mapping(target = "games", ignore = true)
    public Publisher toEntityIgnoreGames(PublisherDto publisherDto);

    @Named("toDtosIgnoreGames")
    @IterableMapping(qualifiedByName = "toDtoIgnoreGames")
    public Set<PublisherDto> toDtosIgnoreGames(Set<Publisher> publishers);

    @Named("toEntitiesIgnoreGames")
    @IterableMapping(qualifiedByName = "toEntityIgnoreGames")
    public Set<Publisher> toEntitiesIgnoreGames(Set<PublisherDto> publishersDtos);

}
