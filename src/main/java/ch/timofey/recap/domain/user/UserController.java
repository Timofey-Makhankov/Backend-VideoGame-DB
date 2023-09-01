package ch.timofey.recap.domain.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/api/v1/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(userMapper::toDTO).toList());
    }

    @GetMapping("/api/v1/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(userMapper.toDTO(userService.findById(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok().body(userMapper.toDTO(userService.register(request)));
    }

    @PutMapping("/api/v1/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") UUID id, @RequestBody UserDTO user) {
        return ResponseEntity.ok().body(userMapper.toDTO(userService.updateById(id, userMapper.toUser(user))));
    }

    @DeleteMapping("/api/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") UUID id) {
        userService.deleteById(id);
    }
}
