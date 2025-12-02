package moviestreamingservice.domain.trending.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrendingMovieResponse {
    private Long tmdbId;
    private String title;
    private String overview;
    private String posterUrl;
    private String backdropUrl;
    private double rating;
    private double popularity;
    private String releaseDate;
    private String type;
}