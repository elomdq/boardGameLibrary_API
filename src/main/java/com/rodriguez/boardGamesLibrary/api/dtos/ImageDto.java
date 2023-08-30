package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ImageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;
    @Setter @Getter private String url;
    @Setter @Getter private String img;
    @Setter @Getter private String name;

    @JsonIncludeProperties({"name", "id"})
    @Getter private BoardGameDto game;

    public void setGame(BoardGameDto game) {
        this.game = game;
        if(!game.getImages().contains(this)){
            game.getImages().add(this);
        }
    }
}
