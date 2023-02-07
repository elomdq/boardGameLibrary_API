package com.rodriguez.boardGamesLibrary.api.dto;
import com.rodriguez.boardGamesLibrary.api.models.*;

import java.io.Serializable;
import java.util.*;

public class BoardGameDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int minPlayers;
    private int maxPlayers;
    private int minAge;
    private int bgYear;
    private int likes;
    private String bgg;

    private List<Image> images;
    private List<Publisher> publishers;
    private List<Designer> designers;
    private List<Artist> artists;

    public BoardGameDTO() {
        this.publishers = new ArrayList<>();
        this.designers = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public BoardGameDTO(String name, int minPlayers, int maxPlayers, int minAge, int bgYear, String bgg) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(List<Designer> designers) {
        this.designers = designers;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof com.rodriguez.boardGamesLibrary.api.models.BoardGame)) return false;
        com.rodriguez.boardGamesLibrary.api.models.BoardGame boardGame = (com.rodriguez.boardGamesLibrary.api.models.BoardGame) o;
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
