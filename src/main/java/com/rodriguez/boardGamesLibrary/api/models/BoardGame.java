package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "boardgames")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class BoardGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private int minPlayers;
    private int maxPlayers;
    private int minAge;
    private int bgYear;

    @Transient
    private int likes;

    private String bgg;

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    //@JsonIgnoreProperties({"game"})
    //@JsonManagedReference
    @JsonIgnore
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    private Set<Publisher> publishers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    private Set<Designer> designers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    private Set<Artist> artists;

    public BoardGame() {
        this.publishers = new HashSet<>();
        this.designers = new HashSet<>();
        this.artists = new HashSet<>();
        this.images = new HashSet<>();
    }

    public BoardGame(String name, int minPlayers, int maxPlayers, int minAge, int bgYear, String bgg) {
        this();
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minAge = minAge;
        this.bgYear = bgYear;
        this.bgg = bgg;
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

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getBgYear() {
        return bgYear;
    }

    public void setBgYear(int bgYear) {
        this.bgYear = bgYear;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getBgg() {
        return bgg;
    }

    public void setBgg(String bgg) {
        this.bgg = bgg;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images.addAll(images);
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers.addAll(publishers);
    }

    public Set<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(Set<Designer> designers) {
        this.designers.addAll(designers);
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists.addAll(artists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof BoardGame)) return false;
        BoardGame boardGame = (BoardGame) o;
        return id== boardGame.getId() &&
                name.equals(boardGame.getName()) &&
                maxPlayers==boardGame.getMaxPlayers() &&
                minPlayers==boardGame.getMinPlayers() &&
                minAge==boardGame.getMinAge() &&
                bgYear==boardGame.getBgYear() &&
                bgg.equals(boardGame.getBgg()) &&
                designers.equals(boardGame.getDesigners()) &&
                publishers.equals(boardGame.getPublishers()) &&
                artists.equals(boardGame.getArtists()) &&
                images.equals(boardGame.getImages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,bgYear,maxPlayers,minPlayers,minAge,bgg);
    }
}
