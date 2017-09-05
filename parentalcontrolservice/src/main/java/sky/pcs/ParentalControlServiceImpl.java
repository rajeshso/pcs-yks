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

    public boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException,
            TitleNotFoundException {
        Optional<ParentalControlLevel> userParentalLevel = ParentalControlLevel.of(userLevel);
        if (!userParentalLevel.isPresent())
            throw new TechnicalFailureException("Invalid control Level entered as input");
        ParentalControlLevel movieParentalLevel = getMovieParentalControl(movieId);
        return userParentalLevel.get().isHigherThanOrEqualTo(movieParentalLevel);
    }

    private ParentalControlLevel getMovieParentalControl(String movieId) throws TechnicalFailureException, TitleNotFoundException {
        try {
            String controlLevel = movieService.getParentalControlLevel(movieId);
            Optional<ParentalControlLevel> movieControlLevelOptional
                    = ParentalControlLevel.of(controlLevel);
            if (!movieControlLevelOptional.isPresent())
                throw new TechnicalFailureException("Invalid control Level returned from movie service");
            return movieControlLevelOptional.get();
        } catch (TechnicalFailureException | TitleNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TechnicalFailureException("Service error with Movie Service", ex);
        }
    }
}