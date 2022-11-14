package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "boardgames")
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
    private int likes;
    private String bgg;

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonIgnoreProperties({"game"})
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

    public int getYear() {
        return bgYear;
    }

    public void setYear(int bgYear) {
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
        this.artists = artists;
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
                bgYear==boardGame.getYear() &&
                bgg.equals(boardGame.getBgg()) &&
                designers.equals(boardGame.getDesigners()) &&
                publishers.equals(boardGame.getPublishers()) &&
                artists.equals(boardGame.getArtists()) &&
                images.equals(boardGame.getImages());
    }

    @Override
    public int hashCode() {
        /*int prime = 31;
        int result = 1;

        result = prime * result + ((id==null)?0:Long.hashCode(id));
        result = prime * result + ((name==null)?0:name.hashCode());
        result = prime * result + ((bgYear==0)?0:Integer.hashCode(bgYear));
        result = prime * result + ((maxPlayers==0)?0:Integer.hashCode(maxPlayers));
        result = prime * result + ((minPlayers==0)?0:Integer.hashCode(minPlayers));
        result = prime * result + ((minAge==0)?0:Integer.hashCode(minAge));
        result = prime * result + ((designers==null)?0:designers.hashCode());
        result = prime * result + ((artists==null)?0:artists.hashCode());
        result = prime * result + ((publishers==null)?0:publishers.hashCode());
        result = prime * result + ((images==null)?0:images.hashCode())*/;

        return Objects.hash(id,name,bgYear,maxPlayers,minPlayers,minAge,bgg,designers,artists,publishers,images);
    }
}
