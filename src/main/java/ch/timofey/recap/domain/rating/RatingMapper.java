package ch.timofey.recap.domain.rating;

import ch.timofey.recap.domain.user.User;
import ch.timofey.recap.domain.videogame.VideoGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public interface RatingMapper {
    @Mapping(target = "game_id", source = "videoGame")
    @Mapping(target = "user_id", source = "user")
    RatingDTO toDTO(Rating rating);

    default UUID map(VideoGame value){
        return value.getId();
    }

    default UUID map(User value){
        return value.getId();
    }
}
