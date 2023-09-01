package ch.timofey.recap.domain.rating;

import java.util.UUID;

public record RatingRequest(UUID userId, UUID gameId, float value) {
}
