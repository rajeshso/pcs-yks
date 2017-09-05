package sky.movie;

public class TechnicalFailureException extends Exception {
    public TechnicalFailureException(String message) {
        super(message);
    }

    public TechnicalFailureException(String internal_error_with_movie_service, Exception ex) {
    }
}
