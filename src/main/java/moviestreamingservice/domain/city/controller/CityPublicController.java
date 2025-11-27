package moviestreamingservice.domain.city.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.city.City;
import moviestreamingservice.domain.city.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityPublicController {
    private final CityService cityService;
    @GetMapping
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
    @GetMapping("/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getCityById(id);
    }
}
