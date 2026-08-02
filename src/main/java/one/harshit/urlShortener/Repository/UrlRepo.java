package one.harshit.urlShortener.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.harshit.urlShortener.Model.Url;

public interface UrlRepo extends JpaRepository<Url, String> {
    
}
