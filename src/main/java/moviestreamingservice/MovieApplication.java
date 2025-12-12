package moviestreamingservice;

import moviestreamingservice.utilities.RuntimeSeeder;
import moviestreamingservice.utilities.TrendingMoviesFetcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

//    @Bean
//    CommandLineRunner runTrending(TrendingMoviesFetcher fetcher) {
//        return args -> {
//            System.out.println("Fetching trending movies...");
//            fetcher.updateTrendingMovies();
//            System.out.println("Trending movies updated!");
//        };
//    }
//@Bean
//public RestTemplate restTemplate() {
//    return new RestTemplate();
//}
//    @Bean
//    CommandLineRunner runRuntimeSeeder(RuntimeSeeder runtimeSeeder) {
//        return args -> {
//            System.out.println("Starting runtime seeding...");
//            runtimeSeeder.seedRuntimes();
//            System.out.println("Runtime seeding completed!");
//        };
//    }

}
