package one.harshit.urlShortener.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import one.harshit.urlShortener.Model.Url;
import one.harshit.urlShortener.Repository.UrlRepo;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UrlRepo repository) {
        return args -> {
            repository.save(new Url(null, "https://google.com", 0));
            repository.save(new Url(null, "https://github.com", 0));
            repository.save(new Url(null, "https://spring.io", 0));
        };

    }
}
