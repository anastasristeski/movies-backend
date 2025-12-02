package moviestreamingservice.domain.trending.dto;


import lombok.Data;

@Data
public class TrendingMovieRequest {
    private Long tmdbId;
    private String type;
}
