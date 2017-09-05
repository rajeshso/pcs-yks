package sky.pcs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sky.movie.MovieService;
import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ParentalControlServiceImplTest {

    private MovieService movieService;

    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        movieService = mock(MovieService.class);
    }

    @Test
    public void whenUserParentLevelIsULevelAndMovieIsUThenReturnTrue() throws Exception {
        final String movieId = "1";
        final String userLevel = "U";
        final String movieLevel = "U";
        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertTrue(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }
}
