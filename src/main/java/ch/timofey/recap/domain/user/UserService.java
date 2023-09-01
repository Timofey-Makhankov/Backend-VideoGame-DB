package ch.timofey.recap.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User register(RegisterRequest registerRequest);
    User registerUser(User user);

    List<User> getAllUsers();
    List<User> getAllUsers(Pageable pageable);
    User save(User user);
    void deleteById(UUID id) throws NoSuchElementException;
    User updateById(UUID id, User user) throws NoSuchElementException;

    User findById(UUID id);
    boolean existsById(UUID id);

    void addRole(UUID id, String roleName);
    void removeRole(UUID id, String roleName);
}
