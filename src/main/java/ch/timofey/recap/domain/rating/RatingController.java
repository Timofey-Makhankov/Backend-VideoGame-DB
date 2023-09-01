package ch.timofey.recap.domain.rating;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;
    private final RatingMapper mapper;

    public RatingController(RatingService ratingService, RatingMapper mapper) {
        this.ratingService = ratingService;
        this.mapper = mapper;
    }

    @GetMapping({"", "/"})
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        return ResponseEntity.ok().body(ratingService.getAllRatings().stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('READ')")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable("id") UUID id) throws EntityNotFoundException {
        return ResponseEntity.ok().body(mapper.toDTO(ratingService.getRatingById(id)));
    }

    @PostMapping({"", "/"})
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<RatingDTO> createRating(@Valid @RequestBody RatingRequest rating) {
        return ResponseEntity.ok().body(mapper.toDTO(ratingService.createRating(rating)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('UPDATE')")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<RatingDTO> updateRating(@Valid @RequestBody RatingRequest rating, @PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(mapper.toDTO(ratingService.updateRating(id, rating)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('DELETE')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable("id") UUID id) {
        ratingService.deleteRatingById(id);
    }
}
