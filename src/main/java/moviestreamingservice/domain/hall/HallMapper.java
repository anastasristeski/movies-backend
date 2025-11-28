package moviestreamingservice.domain.hall;

import moviestreamingservice.domain.hall.dto.HallResponse;

public class HallMapper {
    public static HallResponse mapHallToResponse(Hall hall){
        return new HallResponse(hall.getId(),hall.getHallNumber(),hall.getTotalSeats(),hall.getCinema().getId());
    }
}
