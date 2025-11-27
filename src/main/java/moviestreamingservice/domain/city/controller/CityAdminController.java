package moviestreamingservice.domain.city.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.city.City;
import moviestreamingservice.domain.city.CityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/city")
public class CityAdminController {
    private final CityService cityService;
    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }
    @PutMapping("/{id}")
    public City updateCity(@PathVariable  Long id, @RequestBody  City updatedCity){
        return cityService.updateCity(id,updatedCity);
    }
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
