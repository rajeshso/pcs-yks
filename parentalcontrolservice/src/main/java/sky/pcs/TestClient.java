package sky.pcs;

import sky.movie.DefaultMovieServiceImpl;
import sky.movie.TechnicalFailureException;
import sky.movie.TitleNotFoundException;


public class TestClient {

    public static void main(String[] args) {
        ParentalControlService parentalControlService = new ParentalControlServiceImpl(new DefaultMovieServiceImpl());
        if (args.length != 2) {
            System.out.println("The usage is TestClient <Movie ID> <user PC level - Accepted values are U, PG, 12, 15, 18");
            System.exit(0);
        }
        String movieName = args[0];
        String userPCLevel = args[1];
        try {
            boolean canWatch = parentalControlService.isMovieAllowedByParentalControlLevel(movieName, userPCLevel);
            if (canWatch)
                System.out.println("You can watch the movie " + movieName);
            else
                System.out.println("You cannot watch the movie " + movieName);
        } catch (TechnicalFailureException e) {
            System.err.println(e.getMessage());
        } catch (TitleNotFoundException e) {
            System.out.println("Sorry, movie service could not find the given movie " + movieName);
        }
    }
}
