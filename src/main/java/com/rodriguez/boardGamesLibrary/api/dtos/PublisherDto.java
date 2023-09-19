package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
public class PublisherDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;

    @NotNull
    @NotBlank(message = "Se debe de agregar un nombre.")
    @Setter @Getter private String name;

    @Setter @Getter private String country;

    @Setter @Getter private String web;

    @JsonIncludeProperties({"name", "id"})
    @Getter private Set<BoardGameDto> games;

    public PublisherDto() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGameDto> games) {
        this.games.addAll(games);
        games.forEach(c->c.getPublishers().add(this));
    }
}
