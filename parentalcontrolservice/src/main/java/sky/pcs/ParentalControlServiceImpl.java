package sky.pcs;

import sky.movie.MovieService;
import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;

import java.util.Optional;

public class ParentalControlServiceImpl implements ParentalControlService {
    private final MovieService movieService;

    ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException, TitleNotFoundException {
        Optional<ParentalControlLevel> userParentalLevel = ParentalControlLevel.of(userLevel);
        if (!userParentalLevel.isPresent())
            throw new TechnicalFailureException("invalid control Level entered as input");
        ParentalControlLevel movieParentalLevel = getMovieParentalControl(movieId);
        return userParentalLevel.get().isHigherThanOrEqualTo(movieParentalLevel);
    }

    private ParentalControlLevel getMovieParentalControl(String movieId) throws TechnicalFailureException, TitleNotFoundException {
        try {
            String controlLevel = movieService.getParentalControlLevel(movieId);
            if (controlLevel == null) {
                throw new TechnicalFailureException("invalid control Level returned from movie service");
            }
            Optional<ParentalControlLevel> parentalControlLevelOptional
                    = ParentalControlLevel.of(controlLevel);
            if (!parentalControlLevelOptional.isPresent())
                throw new TechnicalFailureException("invalid control Level returned from movie service");
            return parentalControlLevelOptional.get();
        } catch (TechnicalFailureException | TitleNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TechnicalFailureException("Service error with Movie Service", ex);
        }
    }
}