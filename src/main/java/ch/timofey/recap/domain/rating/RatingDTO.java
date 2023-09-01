package ch.timofey.recap.domain.rating;

import java.util.UUID;

public record RatingDTO(
        UUID id,
        float score,
        UUID game_id,
        UUID user_id
) {
}
