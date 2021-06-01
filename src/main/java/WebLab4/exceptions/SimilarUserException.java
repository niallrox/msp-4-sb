package WebLab4.exceptions;

public class SimilarUserException extends RuntimeException {
    public SimilarUserException(String message) {
        super(message);
    }
}