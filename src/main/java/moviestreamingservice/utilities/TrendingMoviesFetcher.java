package moviestreamingservice.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import moviestreamingservice.domain.trending.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrendingMoviesFetcher {

    private final TrendingMovieRepository trendingRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${tmdb.api.bearer}")
    private String tmdbBearerToken;

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private static final String TRENDING_DAY_URL =
            "https://api.themoviedb.org/3/trending/movie/day?language=en-US";

    private static final String TRENDING_WEEK_URL =
            "https://api.themoviedb.org/3/trending/movie/week?language=en-US";

    private final RestTemplate restTemplate = new RestTemplate();

    public void updateTrendingMovies() {
        trendingRepo.deleteAll();

        saveMovies(fetch(TRENDING_DAY_URL), TrendingType.DAY);
        saveMovies(fetch(TRENDING_WEEK_URL), TrendingType.WEEK);
    }

    private JsonNode fetch(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tmdbBearerToken);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        try {
            return mapper.readTree(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse TMDB response", e);
        }
    }

    private void saveMovies(JsonNode json, TrendingType type) {
        List<TrendingMovie> movies = new ArrayList<>();

        for (JsonNode movieNode : json.get("results")) {
            TrendingMovie movie = TrendingMovie.builder()
                    .tmdbId(movieNode.get("id").asLong())
                    .title(movieNode.get("title").asText())
                    .overview(movieNode.get("overview").asText())
                    .posterUrl(IMAGE_BASE_URL + movieNode.get("poster_path").asText(""))
                    .backdropUrl(IMAGE_BASE_URL + movieNode.get("backdrop_path").asText(""))
                    .rating(movieNode.get("vote_average").asDouble())
                    .popularity(movieNode.get("popularity").asDouble())
                    .releaseDate(movieNode.get("release_date").asText(""))
                    .type(type)
                    .build();

            movies.add(movie);
        }

        trendingRepo.saveAll(movies);
    }
}
