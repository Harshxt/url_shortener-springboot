package one.harshit.urlShortener.Service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import one.harshit.urlShortener.Exceptions.UrlNotFoundException;
import one.harshit.urlShortener.Model.Url;
import one.harshit.urlShortener.Repository.UrlRepo;

@Service
public class UrlService {
    private final UrlRepo repo;

    UrlService(UrlRepo repo) {
        this.repo = repo;
    }

    public Url addUrl(String redirectUrl) {
        Url url = new Url();
        url.setRedirectUrl(redirectUrl);

        url.setVisitCount(0);
        repo.save(url);
        return url;
    }

    public void deleteUrl(String shortenedUrl) {
        try {
            repo.deleteById(shortenedUrl);
        } catch (EmptyResultDataAccessException e) {
            throw new UrlNotFoundException("Url not found:  " + shortenedUrl);
        }
    }

    public void modifyUrl(Url url) {
        repo.save(url);
    }

    public boolean urlExists(String shortenedUrl) {
        return repo.existsById(shortenedUrl);
    }
}
