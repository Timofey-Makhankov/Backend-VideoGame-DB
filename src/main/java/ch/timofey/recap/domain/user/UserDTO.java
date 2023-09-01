package ch.timofey.recap.domain.user;

import ch.timofey.recap.domain.role.Role;
import ch.timofey.recap.domain.role.RoleDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    private UUID id;

    @Email
    private String email;

    @Valid
    private List<RoleDTO> roles;
}
