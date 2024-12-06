package pe.edu.cibertec.spring_mvc_jesus_iman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmCreateDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDetailDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.entity.Film;
import pe.edu.cibertec.spring_mvc_jesus_iman.entity.Language;
import pe.edu.cibertec.spring_mvc_jesus_iman.repository.FilmRepository;
import pe.edu.cibertec.spring_mvc_jesus_iman.repository.LanguageRepository;
import pe.edu.cibertec.spring_mvc_jesus_iman.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<FilmDto> findAllFilms() {
        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;
    }

    @Override
    public FilmDetailDto findDetailById(Integer id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean deleteFilmById(Integer id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> {
                    filmRepository.delete(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public FilmCreateDto createFilm(FilmCreateDto filmCreateDto) {
        Film film = new Film();

        film.setTitle(filmCreateDto.title());
        film.setDescription(filmCreateDto.description());
        film.setReleaseYear(filmCreateDto.releaseYear());
        film.setRentalDuration(filmCreateDto.rentalDuration());
        film.setRentalRate(filmCreateDto.rentalRate());
        film.setLength(filmCreateDto.length());
        film.setReplacementCost(filmCreateDto.replacementCost());
        film.setRating(filmCreateDto.rating());
        film.setSpecialFeatures(filmCreateDto.specialFeatures());
        film.setLastUpdate(new Date());

        if (filmCreateDto.originalLanguageId() != null) {
            Language language = languageRepository.findById(filmCreateDto.originalLanguageId())
                    .orElseThrow(() -> new IllegalArgumentException("" + filmCreateDto.originalLanguageId()));
            film.setLanguage(language);
        }

        Film savedFilm = filmRepository.save(film);
        return new FilmCreateDto(
                savedFilm.getFilmId(),
                savedFilm.getTitle(),
                savedFilm.getDescription(),
                savedFilm.getReleaseYear(),
                savedFilm.getLanguage() != null ? savedFilm.getLanguage().getLanguageId() : null,
                savedFilm.getRentalDuration(),
                savedFilm.getRentalRate(),
                savedFilm.getLength(),
                savedFilm.getReplacementCost(),
                savedFilm.getRating(),
                savedFilm.getSpecialFeatures(),
                savedFilm.getLastUpdate()
        );
    }
}
