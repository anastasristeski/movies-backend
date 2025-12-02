package moviestreamingservice.domain.trending;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(TrendingMovieId.class)
@Table(name="trending_movies")
public class TrendingMovie {
    @Id
    private long tmdbId;
    @Id
    @Enumerated(EnumType.STRING)
    private TrendingType type;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String overview;

    private String posterUrl;

    private String backdropUrl;

    private double rating;

    private double popularity;

    private String releaseDate;

}
