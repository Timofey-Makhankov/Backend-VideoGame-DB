package ch.timofey.recap.domain.videogame;

import ch.timofey.recap.domain.rating.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoGameMapper {
    @Mapping(target = "title", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "release_date", source = "releaseDate")
    @Mapping(target = "rating_values", source = "ratings")
    VideoGameDTO videoGameToVideoGameDTO(VideoGame videoGame);
    @Mapping(target = "title", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "release_date", source = "releaseDate")
    @Mapping(target = "rating_values", source = "ratings")
    List<VideoGameDTO> videoGamesToVideoGameDTOs(List<VideoGame> videoGames);

    default List<Float> map(List<Rating> value) {
        return value.stream().map(Rating::getScore).toList();
    }
}
