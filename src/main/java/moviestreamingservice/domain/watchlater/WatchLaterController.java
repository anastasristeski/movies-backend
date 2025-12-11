package moviestreamingservice.domain.watchlater;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watch-later")
public class WatchLaterController {
    private final WatchLaterService watchLaterService;
    @GetMapping
    public ResponseEntity<List<WatchLaterResponse>> getAll(@AuthenticationPrincipal User user) {
        List<WatchLaterResponse> results = watchLaterService.getWatchLater(user);
        return ResponseEntity.ok(results);
    }
    @PostMapping("/{tmdbId}")
    public ResponseEntity<WatchLaterResponse> add(@AuthenticationPrincipal User user, @PathVariable Long tmdbId) {
     return ResponseEntity.status(HttpStatus.CREATED).body(watchLaterService.addToWatchLater(user, tmdbId));
    }
    @DeleteMapping("/{tmdbId}")
    public ResponseEntity<?> remove(@AuthenticationPrincipal User user, @PathVariable Long tmdbId) {
        watchLaterService.removeFromWatchLater(user, tmdbId);
        return ResponseEntity.noContent().build();
    }
}
