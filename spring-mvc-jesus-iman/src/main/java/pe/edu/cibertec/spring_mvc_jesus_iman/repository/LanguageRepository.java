package pe.edu.cibertec.spring_mvc_jesus_iman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.spring_mvc_jesus_iman.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
