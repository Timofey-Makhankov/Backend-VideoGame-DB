package ch.timofey.recap.domain.role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok().body(roleService.getAllRoles().stream().map(role -> (new RoleDTO(role.getId(), role.getName()))).toList());
    }
}
