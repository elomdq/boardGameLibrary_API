package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;

    @Setter @Getter private String url;

    /*@Setter @Getter private String img;*/

    @NotNull(message = "El archivo debe tener un nombre.")
    @NotBlank(message = "El archivo debe tener un nombre.")
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
