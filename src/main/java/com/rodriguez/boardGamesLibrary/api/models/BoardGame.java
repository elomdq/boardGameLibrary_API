package com.rodriguez.boardGamesLibrary.api.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
@Table(name = "boardgames")
public class BoardGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @Getter @Setter private int minPlayers;
    @Getter @Setter private int maxPlayers;
    @Getter @Setter private int minAge;
    @Getter @Setter private int bgYear;
    @Getter @Setter private String bgg;

    @Getter @Setter
    @Transient
    private int likes;

    @OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonIgnore
    @Getter private Set<Image> images;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    @Getter private Set<Publisher> publishers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    @Getter private Set<Designer> designers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"games"})
    @Getter private Set<Artist> artists;

    public BoardGame() {
        this.publishers = new HashSet<>();
        this.designers = new HashSet<>();
        this.artists = new HashSet<>();
        this.images = new HashSet<>();
    }
    public void setDesigners(Set<Designer> designers) {
        getDesigners().addAll(designers);
    }
    public void setPublishers(Set<Publisher> publishers) {
        getPublishers().addAll(publishers);
    }
    public void setArtists(Set<Artist> artists) {
        getArtists().addAll(artists);
    }
    public void setImages(Set<Image> images) {
        getImages().addAll(images);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return minPlayers == boardGame.minPlayers
                && maxPlayers == boardGame.maxPlayers
                && minAge == boardGame.minAge
                && bgYear == boardGame.bgYear
                && Objects.equals(id, boardGame.id)
                && Objects.equals(name, boardGame.name)
                && Objects.equals(bgg, boardGame.bgg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, minPlayers, maxPlayers, minAge, bgYear, bgg, likes);
    }
}
