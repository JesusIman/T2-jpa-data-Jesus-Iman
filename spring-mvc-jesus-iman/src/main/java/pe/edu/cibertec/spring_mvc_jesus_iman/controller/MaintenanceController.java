package pe.edu.cibertec.spring_mvc_jesus_iman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmCreateDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDetailDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.dto.FilmDto;
import pe.edu.cibertec.spring_mvc_jesus_iman.entity.Language;
import pe.edu.cibertec.spring_mvc_jesus_iman.repository.LanguageRepository;
import pe.edu.cibertec.spring_mvc_jesus_iman.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    LanguageRepository languageRepository;

    @GetMapping("/start")
    public String start(Model model) {
        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findDetailById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findDetailById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute FilmDetailDto film, Model model) {
        maintenanceService.updateFilm(film);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        boolean isDeleted = maintenanceService.deleteFilmById(id);
        if (isDeleted) {
            model.addAttribute("message", "Película eliminada.");
        } else {
            model.addAttribute("error", "No se pudo eliminar.");
        }
        return "redirect:/maintenance/start";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Language> languages = languageRepository.findAll();
        FilmCreateDto newFilm = new FilmCreateDto(null, "", "", null, null, null, null, null, null, null, null, null);
        model.addAttribute("film", newFilm);
        model.addAttribute("languages", languages);
        return "maintenance-create";
    }

    @PostMapping("/create-confirm")
    public String create(@ModelAttribute FilmCreateDto film, Model model) {
        maintenanceService.createFilm(film);
        return "redirect:/maintenance/start";
    }
}

