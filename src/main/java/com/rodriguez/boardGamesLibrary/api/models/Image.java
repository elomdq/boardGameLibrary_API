package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_img")
    private Long id;
    private String url;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"publishers", "designers", "artists", "images"})
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(name, image.name) && Objects.equals(game, image.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, game);
    }
}
