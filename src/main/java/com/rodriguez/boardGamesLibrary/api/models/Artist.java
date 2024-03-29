package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ToString
@Entity
@Table(name = "artists")
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @NotNull
    @NotBlank(message = "Se debe de agregar un nombre.")
    @Getter @Setter private String name;

    @NotNull
    @NotBlank(message = "Se debe de agregar un apellido.")
    @Getter @Setter private String lastName;

    @Getter @Setter private String country;

    @JsonIncludeProperties({"name", "id"})
    @ManyToMany(mappedBy = "artists")
    @Getter @Setter private Set<BoardGame> games;

    public Artist() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGame> games) {
        this.games.addAll(games);
        games.forEach(c->c.getArtists().add(this));
    }

    public void clearBoardGames(){
        //al ser no-owner de la relacion ManyToMany no serviria solo con limpiar
        games.forEach(c->c.getArtists().remove(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(lastName, artist.lastName) && Objects.equals(country, artist.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, country);
    }
}
