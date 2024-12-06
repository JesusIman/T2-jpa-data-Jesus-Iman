package pe.edu.cibertec.spring_mvc_jesus_iman.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmActorId {

    private Integer actorId;
    private Integer filmId;

}

