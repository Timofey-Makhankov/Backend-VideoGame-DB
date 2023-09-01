package ch.timofey.recap.domain.videogame;

import ch.timofey.recap.exception.VideoGameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;
    private final VideoGameMapper mapper;

    public VideoGameService(VideoGameRepository videoGameRepository, VideoGameMapper mapper){
        this.videoGameRepository = videoGameRepository;
        this.mapper = mapper;
    }

    @PreAuthorize("hasAuthority('READ')")
    public List<VideoGameDTO> getAllVideoGames(){
        log.info("All Video game Items has been tried to be accessed");
        return mapper.videoGamesToVideoGameDTOs(videoGameRepository.findAll());
    }

    @PreAuthorize("hasAuthority('READ')")
    public VideoGameDTO getVideoGameByUUID(UUID id){
        log.info("Video Game Item has been tried to be accessed");
        return videoGameRepository
                .findById(id)
                .map(mapper::videoGameToVideoGameDTO)
                .orElseThrow(() -> new VideoGameNotFoundException("Video Game with given Id could not be found"));
    }

    @PreAuthorize("hasAuthority('CREATE')")
    public void addVideoGame(VideoGame videoGame){
        videoGameRepository.save(videoGame);
        log.info("Video Game has been saved in the Database");
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    public void updateVideoGame(UUID id, VideoGame videoGame){
        videoGame.setId(id);
        videoGameRepository.save(videoGame);
        log.info("Video Game has been successfully updated");
    }

    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteVideoGame(UUID id){
        videoGameRepository.deleteById(id);
        log.info("Video game has been deleted in the database");
    }
}
