package one.harshit.urlShortener.Exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message){
        super(message);
    }
}
