package one.harshit.urlShortener.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    public Url(String shortenedUrl, String redirectUrl) {
        this.shortenedUrl = shortenedUrl;
        this.redirectUrl = redirectUrl;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    // private String id;
    private String shortenedUrl;
    private String redirectUrl;
    private int visitCount;
}
