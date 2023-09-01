package ch.timofey.recap.domain.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    List<Rating> findByVideoGame_Id(UUID id);

    List<Rating> findByUser_Id(UUID id);

}
