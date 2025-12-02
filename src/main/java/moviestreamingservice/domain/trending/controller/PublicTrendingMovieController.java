package moviestreamingservice.domain.trending.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.trending.TrendingMovieService;
import moviestreamingservice.domain.trending.dto.TrendingMovieResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trending")
@RequiredArgsConstructor
public class PublicTrendingMovieController {

    private final TrendingMovieService service;

    @GetMapping("/today")
    public List<TrendingMovieResponse> getTrendingToday() {
        return service.getTrendingToday();
    }

    @GetMapping("/week")
    public List<TrendingMovieResponse> getTrendingThisWeek() {
        return service.getTrendingWeek();
    }
}