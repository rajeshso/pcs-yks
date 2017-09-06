package sky.pcs;

import sky.movie.DefaultMovieServiceImpl;


public class TestClient {

    public static void main(String[] args) {
        ParentalControlService parentalControlService = new ParentalControlServiceImpl(new DefaultMovieServiceImpl());
        if (args.length != 2) {
            System.out.println("The usage is java -jar pcs.jar <Movie ID> <user PC level>");
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
