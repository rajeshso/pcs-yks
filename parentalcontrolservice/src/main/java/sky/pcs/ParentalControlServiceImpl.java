package sky.pcs;

import sky.movie.MovieService;
import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;

public class ParentalControlServiceImpl implements ParentalControlService {
    private final MovieService movieService;

    ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException, TitleNotFoundException {
        return userLevel.equalsIgnoreCase(getMovieParentalControl(movieId));
    }

    private String getMovieParentalControl(String movieId) throws TechnicalFailureException, TitleNotFoundException {
        try {
            String controlLevel = movieService.getParentalControlLevel(movieId);
            if (controlLevel != null) {
                return controlLevel;
            } else {
                throw new TechnicalFailureException("invalid control Level returned from movie service");
            }
        } catch (TechnicalFailureException | TitleNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TechnicalFailureException("Service error with Movie Service", ex);
        }
    }
}
