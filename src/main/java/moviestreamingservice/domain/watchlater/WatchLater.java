package moviestreamingservice.domain.watchlater;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="watch_later", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"}))
public class WatchLater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
    private LocalDateTime createdAt;
}
