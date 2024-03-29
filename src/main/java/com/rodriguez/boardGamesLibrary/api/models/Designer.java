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
@Table(name = "designers",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "lastname"})}
)
public class Designer implements Serializable {
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
    @ManyToMany(mappedBy = "designers"/*, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}*/)
    @Getter private Set<BoardGame> games;

    public Designer() {
        this.games = new HashSet<>();
    }

    public void setGames(Set<BoardGame> games) {
        this.games.addAll(games);
        games.forEach(c->c.getDesigners().add(this));
    }

    public void clearBoardGames(){
        //al ser no-owner de la relacion ManyToMany no serviria solo con limpiar
        games.forEach(c->c.getDesigners().remove(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designer designer = (Designer) o;
        return Objects.equals(id, designer.id)
                && Objects.equals(name, designer.name)
                && Objects.equals(lastName, designer.lastName)
                && Objects.equals(country, designer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, country);
    }
}
