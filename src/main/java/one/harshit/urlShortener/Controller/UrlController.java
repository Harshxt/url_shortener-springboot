package one.harshit.urlShortener.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import one.harshit.urlShortener.Model.Url;
import one.harshit.urlShortener.Service.UrlService;

@RestController
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String home(){
        return "Hello there";
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Url> redirect(@PathVariable String url){
        // TODO: implement redirection logic
        
        Url returnedUrl = service.addUrl(url);

        return new ResponseEntity<>(returnedUrl, HttpStatus.OK);
        
    }


  

    
    
}

