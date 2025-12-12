package moviestreamingservice.domain.movie;


import moviestreamingservice.domain.showtime.ShowTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movies")
public class Movie {
    @Id
    private Long tmdbId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String overview;
    private String posterUrl;
    private String backdropUrl;
    private double voteAverage;
    private int voteCount;
    private Double popularity;
    private String releaseDate;
    private Integer runtime;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowTime> showTimes = new ArrayList<>();
}