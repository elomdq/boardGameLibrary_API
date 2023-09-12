package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
public class DesignerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;
    @Setter @Getter private String name;
    @Setter @Getter private String lastName;
    @Setter @Getter private String country;

    @JsonIncludeProperties({"name", "id"})
    @Getter private Set<BoardGameDto> games;

    public DesignerDto() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGameDto> games) {
        getGames().addAll(games);
        games.forEach(c->c.getDesigners().add(this));
    }
}
