package ch.timofey.recap.domain.rating;

import ch.timofey.recap.domain.user.User;
import ch.timofey.recap.domain.user.UserRepository;
import ch.timofey.recap.domain.videogame.VideoGame;
import ch.timofey.recap.domain.videogame.VideoGameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final VideoGameRepository videoGameRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, VideoGameRepository videoGameRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.videoGameRepository = videoGameRepository;
        this.userRepository = userRepository;
    }

    public List<Rating> getAllRatings(){
        log.info("All Ratings has been tried to be accessed");
        return Optional.of(ratingRepository.findAll()).orElseThrow(EntityNotFoundException::new);
    }

    public List<Rating> getAllRatingsByGameId(UUID gameId) {
        log.info("All Ratings by game id has been tried to be accessed");
        return ratingRepository.findByVideoGame_Id(gameId);
    }

    public List<Rating> getAllRatingsByUserId(UUID userId) {
        log.info("All Ratings by user id has been tried to be accessed");
        return ratingRepository.findByUser_Id(userId);
    }

    public Rating getRatingById(UUID id) {
        log.info("A Rating by Id has been tried to be accessed");
        return ratingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Rating createRating(RatingRequest ratingRequest){
        var user = userRepository.findById(ratingRequest.userId()).orElseThrow(EntityNotFoundException::new);
        var videoGame = videoGameRepository.getById(ratingRequest.gameId());
        Rating rating = new Rating(user, videoGame, ratingRequest.value());
        log.info("A Rating has been tried to be created");
        return ratingRepository.save(rating);
    }

    public Rating updateRating(UUID id, RatingRequest ratingRequest){
        VideoGame videoGame = videoGameRepository.getReferenceById(ratingRequest.gameId());
        User user = userRepository.getReferenceById(ratingRequest.userId());
        Rating rating = new Rating(id, ratingRequest.value(), user, videoGame);
        log.info("A Rating has been tried to be updated");
        return ratingRepository.save(rating);
    }

    public void deleteRatingById(UUID id) {
        ratingRepository.deleteById(id);
        log.info("A Rating by Id has been deleted");
    }
}
