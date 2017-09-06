package sky.pcs;

import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;

public interface ParentalControlService {
    boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException,
            TitleNotFoundException, InvalidInputException;
}
