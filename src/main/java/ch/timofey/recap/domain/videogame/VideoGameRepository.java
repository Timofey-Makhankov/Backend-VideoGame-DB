package ch.timofey.recap.domain.videogame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> { }
