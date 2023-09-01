package ch.timofey.recap.domain.authority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AuthorityDTO {
    private UUID id;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    public AuthorityDTO(UUID id, String name){
        this.id = id;
        this.name = name;
    }
}
