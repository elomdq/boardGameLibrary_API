package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publishers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;
    private String web;

    @JsonIgnoreProperties({"designers", "publishers", "artists", "images"})
    @ManyToMany(mappedBy = "publishers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BoardGame> games;

    public Publisher() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }


    public Set<BoardGame> getGames() {
        return games;
    }

    public void setGames(Set<BoardGame> games) {
        this.games.addAll(games);
        games.forEach(c->c.getPublishers().add(this));
    }

    @Override
    public String toString() {
        return "{ " +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", web='" + web + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.getId()) &&
                Objects.equals(name, publisher.getName()) &&
                Objects.equals(country, publisher.getCountry()) &&
                Objects.equals(web, publisher.getWeb());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, web);
    }
}
