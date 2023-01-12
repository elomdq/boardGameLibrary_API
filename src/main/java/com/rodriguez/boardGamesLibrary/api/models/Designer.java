package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "designers",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "lastname"})}
)
public class Designer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String country;

    //@JsonIgnoreProperties({"designers", "publishers", "artists", "images"})
    @JsonIncludeProperties({"name", "id"})
    @ManyToMany(mappedBy = "designers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.})
    private Set<BoardGame> games;

    public Designer() {
        this.games = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<BoardGame> getGames() {
        return games;
    }

    public void setGames(Set<BoardGame> games) {
        this.games.addAll(games);
        games.forEach(c->c.getDesigners().add(this));
    }

    @Override
    public String toString() {
        return  "Name: " + name +
                "LastName: " + lastName +
                "Country: " + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Designer)) return false;
        Designer designer = (Designer) o;
        return Objects.equals(id, designer.id) &&
                Objects.equals(name, designer.name) &&
                Objects.equals(lastName, designer.lastName) &&
                Objects.equals(country, designer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, country);
    }
}
