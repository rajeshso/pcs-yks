package sky.pcs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sky.movie.DefaultMovieServiceImpl;
import sky.movie.MovieService;
import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ParentalControlServiceImpl_IntegrationTest {
    private MovieService movieService;

    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        movieService = new DefaultMovieServiceImpl();
    }

    @Test
    public void whenUserParentLevelIsULevelAndMovieIsULevelThenReturnTrue() throws Exception {
        final String movieId = "Despicable Me 3";
        final String userLevel = "U";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertTrue(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void whenUserParentLevelIsULevelAndMovieIs18LevelThenReturnFalse() throws Exception {
        final String movieId = "Berlin Syndrome";
        final String userLevel = "U";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertFalse(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void whenUserParentLevelIs18LevelAndMovieIsULevelThenReturnTrue() throws Exception {
        final String movieId = "Despicable Me 3";
        final String userLevel = "18";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertTrue(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void whenUserParentLevelIsPGLevelAndMovieIs12LevelThenReturnFalse() throws Exception {
        final String movieId = "The Levelling";
        final String userLevel = "PG";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertFalse(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test(expected = TitleNotFoundException.class)
    public void whenMovieNotInListThenThrowException() throws Exception {
        final String movieId = "I am not in the Movie List";
        final String userLevel = "PG";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel);
    }

    @Test(expected = TechnicalFailureException.class)
    public void whenUserLevelIsIncorrectThenThrowException() throws Exception {
        final String movieId = "The Levelling";
        final String userLevel = "I am not the correct user Level";
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel);
    }
}