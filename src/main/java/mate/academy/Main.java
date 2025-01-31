package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));

        Movie terminator = new Movie("Terminator");
        terminator.setDescription("Fiction movie");
        movieService.add(terminator);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance((CinemaHallService.class));
        CinemaHall cinemaHallA = new CinemaHall(500);
        cinemaHallA.setDescription("Red hall 'A'");
        CinemaHall cinemaHallB = new CinemaHall(500);
        cinemaHallB.setDescription("Black hall 'B'");
        cinemaHallService.add(cinemaHallA);
        cinemaHallService.add(cinemaHallB);
        System.out.println(cinemaHallService.get(cinemaHallB.getId()));
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        MovieSession movieSession1A = new MovieSession(
                fastAndFurious, cinemaHallA, LocalDateTime.of(2022, 05, 7, 15, 00));
        MovieSession movieSession1B = new MovieSession(
                terminator, cinemaHallB, LocalDateTime.of(2022, 05, 07, 15, 00));
        MovieSession movieSession2B = new MovieSession(
                terminator, cinemaHallB, LocalDateTime.of(2022, 05, 07, 17, 00));
        movieSessionService.add(movieSession1A);
        movieSessionService.add(movieSession1B);
        movieSessionService.add(movieSession2B);
        System.out.println(movieSessionService.get(movieSession1A.getId()));
        System.out.println(movieSessionService.get(fastAndFurious.getId()));
        movieSessionService.findAvailableSessions(terminator.getId(),
                LocalDate.of(2022, Month.MAY, 07)).forEach(System.out::println);
    }
}
