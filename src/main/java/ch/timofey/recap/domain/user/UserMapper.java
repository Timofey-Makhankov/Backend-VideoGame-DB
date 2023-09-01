package ch.timofey.recap.domain.user;

import ch.timofey.recap.domain.role.Role;
import ch.timofey.recap.domain.role.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public interface UserMapper {
    @Mapping(source = "roleSet", target = "roles")
    UserDTO toDTO(User user);

    @Mapping(source = "roles", target = "roleSet")
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUser(UserDTO userDTO);

    default List<RoleDTO> map(Set<Role> value){
        return value.stream().map(role -> (new RoleDTO(role.getId(), role.getName()))).toList();
    }

    default Set<Role> map(List<RoleDTO> value){
        return value.stream().map(roleDTO -> (new Role(roleDTO.role(), roleDTO.id()))).collect(Collectors.toSet());
    }
}
