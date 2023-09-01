package ch.timofey.recap.domain.videogame;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
public record VideoGameDTO (
        UUID id,
        String title,
        String description,
        LocalDate release_date,
        List<Float> rating_values
){}
