package moviestreamingservice.utilities;


import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class TMDBSeeder {
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${tmdb.api.bearer}")
    private String tmdbBearerToken;
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    public TMDBSeeder(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.restTemplate = new RestTemplate();
    }
    public void fetchMovies() throws JsonProcessingException, InterruptedException {
        int page = 1;
        do{
            String url = "https://api.themoviedb.org/3/discover/movie?" +
                    "include_adult=false&include_video=false&language=en-US" +
                    "&page=" + page +
                    "&sort_by=popularity.desc";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tmdbBearerToken);
            headers.set("Accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            JsonNode response = objectMapper.readTree(responseEntity.getBody());
            List<Movie> movies = parseMovies(response);
            movieRepository.saveAll(movies);
            page++;
            Thread.sleep(300);
            System.out.println(movies);
        }while(page<=500);
    }
    private List<Movie> parseMovies(JsonNode response){
        List<Movie> movies = new ArrayList<>();
        JsonNode results = response.get("results");
        if(results != null && results.isArray()){
            for(JsonNode movieNode : results){
                Movie movie = new Movie();
                movie.setTmdbId(movieNode.get("id").asLong());
                movie.setTitle(movieNode.get("title").asText());
                movie.setOverview(movieNode.get("overview").asText());
                movie.setPosterUrl(IMAGE_BASE_URL + movieNode.get("poster_path").asText(""));
                movie.setBackdropUrl(IMAGE_BASE_URL + movieNode.get("backdrop_path").asText(""));
                movie.setVoteAverage(movieNode.get("vote_average").asDouble());
                movie.setVoteCount(movieNode.get("vote_count").asInt());
                movie.setPopularity(movieNode.get("popularity").asDouble());
                movie.setReleaseDate(movieNode.get("release_date").asText());
                List<String> genres = new ArrayList<>();
                JsonNode genreIdsNode = movieNode.get("genre_ids");
                if(genreIdsNode != null && genreIdsNode.isArray()){
                    for(JsonNode idNode: genreIdsNode){
                        int genreId = idNode.asInt();
                        String genreName = GENRE_MAP.get(genreId);
                        if(genreName != null) {
                            genres.add(genreName);
                        }
                    }
                }
                movie.setGenres(Collections.singletonList(String.join(",", genres)));
                movies.add(movie);
            }
        }
        return movies;
    }
    public static final Map<Integer, String> GENRE_MAP = Map.ofEntries(
            Map.entry(28, "Action"), Map.entry(12, "Adventure"), Map.entry(16, "Animation"),
            Map.entry(35, "Comedy"), Map.entry(80, "Crime"), Map.entry(99, "Documentary"),
            Map.entry(18, "Drama"), Map.entry(10751, "Family"), Map.entry(14, "Fantasy"),
            Map.entry(36, "History"), Map.entry(27, "Horror"), Map.entry(10402, "Music"),
            Map.entry(9648, "Mystery"), Map.entry(10749, "Romance"), Map.entry(878, "Science Fiction"),
            Map.entry(10770, "TV Movie"), Map.entry(53, "Thriller"), Map.entry(10752, "War"),
            Map.entry(37, "Western")
    );
}
