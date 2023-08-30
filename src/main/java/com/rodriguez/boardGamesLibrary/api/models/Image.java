package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "images")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_img")
    @Setter @Getter private Long id;
    @Setter @Getter private String url;

    /*@Column(name = "img_base64")
    @Setter @Getter private String img;*/

    @Column(unique = true)
    @Setter @Getter private String name;

    @ManyToOne()
    @JoinColumn(name = "id_game")
    @JsonIncludeProperties({"name", "id"})
    @Getter private BoardGame game;

    public void setGame(BoardGame game) {
        this.game = game;
        if(!game.getImages().contains(this)){
            game.getImages().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return /*Objects.equals(id, image.id)
                &&*/ Objects.equals(url, image.url)
                && Objects.equals(name, image.name)
                && Objects.equals(game, image.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*id,*/ url, name);
    }
}
