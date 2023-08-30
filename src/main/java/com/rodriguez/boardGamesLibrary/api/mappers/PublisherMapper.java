package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    public Publisher toEntity (PublisherDto publisherDto);
    public PublisherDto toDto (Publisher publisher);

    List<Publisher> toEntities (List<PublisherDto> publisherDtos);
    List<PublisherDto> toDtos (List<Publisher> publishers);

}
