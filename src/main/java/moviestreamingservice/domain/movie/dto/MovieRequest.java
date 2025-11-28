package moviestreamingservice.domain.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovieRequest(
        @NotNull Long tmdbId,
        @NotBlank String title,
        @NotBlank String overview,
        @NotBlank String posterUrl,
        @NotBlank String backdropUrl,
        @NotNull double voteAverage,
        @NotNull int voteCount,
        @NotNull Double popularity,
        @NotBlank String releaseDate,
        List<String> genres
) {
}
