package pe.edu.cibertec.spring_mvc_jesus_iman.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}
