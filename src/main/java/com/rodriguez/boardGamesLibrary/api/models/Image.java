package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    private Long id;
    private String url;

    @Column(name = "img_base64")
    private String img;

    @Column(unique = true)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "id_game")
    @JsonIgnoreProperties({"publishers", "designers", "artists", "images"})
    //@JsonBackReference
    private BoardGame game;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardGame getGame() {
        return game;
    }

    public void setGame(BoardGame game) {
        this.game = game;
        if(!game.getImages().contains(this)){
            game.getImages().add(this);
        }
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(name, image.name) && Objects.equals(game, image.game) && Objects.equals(img,image.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, img);
    }
}
