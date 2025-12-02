package moviestreamingservice.domain.trending;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendingMovieId implements Serializable {
    private Long tmdbId;
    private TrendingType type;
}
