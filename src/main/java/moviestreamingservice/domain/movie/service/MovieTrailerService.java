package moviestreamingservice.domain.movie.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MovieTrailerService {
    @Value("${tmdb.api.bearer}")
    private  String tmdbBearerToken;
    private final RestTemplate restTemplate = new RestTemplate();
    public String getTrailerKey(Long tmdbId) {
        String url = "https://api.themoviedb.org/3/movie/" + tmdbId + "/videos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tmdbBearerToken);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, JsonNode.class
        );
        assert response.getBody() != null;
        JsonNode results = response.getBody().get("results");
        if(results ==null || !results.isArray()) throw new BadRequestException("No data returned");
        for(JsonNode video : results) {
            if("Youtube".equals(video.get("site").asText())&&
            "Trailer".equals(video.get("type").asText())
            ) {
                return video.get("key").asText();
            }
        }
        for (JsonNode video : results) {
            if ("YouTube".equals(video.get("site").asText()) &&
                    "Trailer".equals(video.get("type").asText())) {
                return video.get("key").asText();
            }
        }
return null;
    }
}
