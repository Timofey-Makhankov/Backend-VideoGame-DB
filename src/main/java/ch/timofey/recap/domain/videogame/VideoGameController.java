package ch.timofey.recap.domain.videogame;

import ch.timofey.recap.exception.VideoGameNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1/videogames")
public class VideoGameController {
    private final VideoGameService videoGameService;
    public VideoGameController(VideoGameService videoGameService){
        this.videoGameService = videoGameService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<VideoGameDTO>> getAllVideoGames(){
        return ResponseEntity.ok().body(videoGameService.getAllVideoGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGameDTO> getVideoGameById(@PathVariable("id") UUID id) throws VideoGameNotFoundException {
        return ResponseEntity.ok().body(videoGameService.getVideoGameByUUID(id));
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createVideoGame(@Valid @RequestBody VideoGame videoGame) {
        videoGameService.addVideoGame(videoGame);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateVideoGame(@Valid @PathVariable("id") UUID id, @RequestBody VideoGame videoGame){
        videoGameService.updateVideoGame(id, videoGame);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteVideoGame(@PathVariable("id") UUID id){
        videoGameService.deleteVideoGame(id);
    }

    @ExceptionHandler(VideoGameNotFoundException.class)
    public ResponseEntity<String> handleNoSuchElementException(VideoGameNotFoundException vgnfe) {
        log.error("The given Discount was not found in the database");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vgnfe.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
        log.error("The given values of the Model has failed the requirements");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(manve.getFieldError()).getDefaultMessage());
    }
}
