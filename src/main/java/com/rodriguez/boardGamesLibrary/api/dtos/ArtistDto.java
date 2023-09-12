package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
public class ArtistDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;
    @Setter @Getter private String name;
    @Setter @Getter private String lastName;
    @Setter @Getter private String country;

    @JsonIncludeProperties({"name", "id"})
    @Getter private Set<BoardGameDto> games;

    public ArtistDto() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGameDto> games) {
        this.games.addAll(games);
        games.forEach(c->c.getArtists().add(this));
    }
}
