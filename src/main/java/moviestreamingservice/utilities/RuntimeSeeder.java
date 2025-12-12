package moviestreamingservice.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


import java.util.List;

//@Component
@RequiredArgsConstructor
public class RuntimeSeeder {
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${tmdb.api.bearer}")
    private String tmdbBearerToken;
    public void seedRuntimes() {
        List<Long> tmdbIds = movieRepository.findAllTmdbIds();
        System.out.println("Found" + tmdbIds.size() + "movie Ids");
        for(Long id: tmdbIds) {
            try{
                Integer runtime = fetchRuntime(id);
                if(runtime == null) continue;
                movieRepository.updateRuntimeByTmdbId(id, runtime);
                System.out.println("Updated ");
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Integer fetchRuntime(Long tmdbId) {
        String url = "https://api.themoviedb.org/3/movie/"+tmdbId;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer "+ tmdbBearerToken);
        httpHeaders.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            JsonNode json = objectMapper.readTree(response.getBody());
            JsonNode runtimeNode = json.get("runtime");
            return runtimeNode !=null && !runtimeNode.isNull() ? runtimeNode.asInt(): null;
        }catch(Exception e){
            System.out.println("Failted to fetch runtime for:" + tmdbId);
            return null;
        }

    }
}


