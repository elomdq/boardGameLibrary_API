package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@ToString
@Entity
@Table(name = "publishers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Publisher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String country;
    @Getter @Setter private String web;

    @JsonIncludeProperties({"name", "id"})
    @ManyToMany(mappedBy = "publishers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter private Set<BoardGame> games;

    public Publisher() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGame> games) {
        this.games.addAll(games);
        games.forEach(c->c.getPublishers().add(this));
    }

    public void clearBoardGames(){
        //al ser no-owner de la relacion ManyToMany no serviria solo con limpiar
        games.forEach(c->c.getPublishers().remove(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id)
                && Objects.equals(name, publisher.name)
                && Objects.equals(country, publisher.country)
                && Objects.equals(web, publisher.web);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, web);
    }
}
