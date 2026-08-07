package one.harshit.urlShortener.Controller;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import one.harshit.urlShortener.Exceptions.UrlNotFoundException;
import one.harshit.urlShortener.Model.Url;
import one.harshit.urlShortener.Service.UrlService;

@RestController
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "Hello there";
    }

    @PostMapping("/add/")
    public ResponseEntity<Url> addUrl(@RequestBody Map<String, String> payload) {
        String url = payload.getOrDefault("url", null);
        Url returnedUrl = service.addUrl(url);

        String shortenedUrl = returnedUrl.getShortenedUrl();
        returnedUrl.setShortenedUrl("https://localhost:8080/" + shortenedUrl);

        return new ResponseEntity<>(returnedUrl, HttpStatus.OK);

    }

    @DeleteMapping("/{url}")
    public ResponseEntity<String> deleteUrl(@PathVariable("url") String shortenedUrl) {

        service.deleteUrl(shortenedUrl);
        return ResponseEntity.ok("Deleted");

    }

    // Payload : {"shortenedUrl": "abc", "newUrl": "https://google.com/"}
    @PutMapping("/add/")
    public ResponseEntity<String> modifyUrl(@RequestBody Map<String, String> payload) {
        String shortenedUrl = payload.getOrDefault("shortenedUrl", null);
        if (!service.urlExists(shortenedUrl))
            throw new UrlNotFoundException("Url not found: " + shortenedUrl);
        String redirectUrl = payload.getOrDefault("newUrl", null);
        Url newUrl = new Url(shortenedUrl, redirectUrl);
        if (service.urlExists(shortenedUrl)) {
            service.modifyUrl(newUrl);
            return ResponseEntity.ok(newUrl.toString());
        }
        System.err.println(service.urlExists(shortenedUrl));
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<Void> redirectUsingView(@PathVariable String shortenedUrl) {
        if (!service.urlExists(shortenedUrl)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        String originalUrl = service.findById(shortenedUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();

    }

}
