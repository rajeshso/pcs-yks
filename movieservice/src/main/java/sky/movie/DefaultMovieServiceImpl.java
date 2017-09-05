package sky.movie;

public class DefaultMovieServiceImpl implements MovieService {
    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        switch (movieId) {
            case "Despicable Me 3":
                return "U";
            case "Wonder Woman":
                return "PG";
            case "The Levelling":
                return "12";
            case " Guardians of the Galaxy":
                return "15";
            case "Berlin Syndrome":
                return "18";
            default:
                throw new TitleNotFoundException("Sorry, we could not find the movie you are looking for.");
        }
    }
}
