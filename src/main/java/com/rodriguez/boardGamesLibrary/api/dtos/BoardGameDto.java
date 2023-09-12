package com.rodriguez.boardGamesLibrary.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.models.Image;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@ToString(exclude = {"likes", "id", "publishers.games", "designers.games", "artists.games", "images"})
public class BoardGameDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter private Long id;
    @Setter @Getter private String name;
    @Setter @Getter private int minPlayers;
    @Setter @Getter private int maxPlayers;
    @Setter @Getter private int minAge;
    @Setter @Getter private int bgYear;
    @Setter @Getter private int likes;
    @Setter @Getter private String bgg;

    public BoardGameDto() {
        this.publishers = new HashSet<>();
        this.designers = new HashSet<>();
        this.artists = new HashSet<>();
        this.images = new HashSet<>();
    }

    @JsonIgnore
    @Getter private Set<ImageDto> images;
    @JsonIgnoreProperties({"games"})
    @Getter private Set<PublisherDto> publishers;
    @JsonIgnoreProperties({"games"})
    @Getter private Set<DesignerDto> designers;
    @JsonIgnoreProperties({"games"})
    @Getter private Set<ArtistDto> artists;

    public void setDesigners(Set<DesignerDto> designers) {
        getDesigners().addAll(designers);
    }
    public void setPublishers(Set<PublisherDto> publishers) {
        getPublishers().addAll(publishers);
    }
    public void setArtists(Set<ArtistDto> artists) {
        getArtists().addAll(artists);
    }
    public void setImages(Set<ImageDto> images) {
        getImages().addAll(images);
    }
}
