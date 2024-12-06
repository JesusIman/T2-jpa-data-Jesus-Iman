package pe.edu.cibertec.spring_mvc_jesus_iman.service;

import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmCreateDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDetailDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDto;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> findAllFilms();
    Boolean deleteFilmById(Integer id);
    FilmCreateDto createFilm(FilmCreateDto filmCreateDto);
    FilmDetailDto findDetailById(Integer id);
    Boolean updateFilm(FilmDetailDto filmDetailDto);

}
