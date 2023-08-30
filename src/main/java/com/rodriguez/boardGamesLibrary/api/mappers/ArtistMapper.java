package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.ArtistDto;
import com.rodriguez.boardGamesLibrary.api.models.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ArtistMapper {
    public Artist toEntity (ArtistDto artistDto);
    public ArtistDto toDto (Artist artist);

    List<Artist> toEntities (List<ArtistDto> artistDtos);
    List<ArtistDto> toDtos (List<Artist> artists);
}
