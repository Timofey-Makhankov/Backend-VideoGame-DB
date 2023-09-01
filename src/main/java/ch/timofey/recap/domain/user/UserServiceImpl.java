package ch.timofey.recap.domain.user;

import ch.timofey.recap.domain.role.Role;
import ch.timofey.recap.domain.role.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("A User has tried to log in to the service");
        return userRepository.findByEmail(email).map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public User loadAsUserEntity(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found"));
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        User user = User.builder().email(registerRequest.email()).password(passwordEncoder.encode(registerRequest.password())).build();
        user.setRoleSet(new HashSet<>(List.of(roleService.getDefaultRoles())));
        user.setRatings(new HashSet<>());
        log.info(user.getRoleSet());
        return userRepository.save(user);
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(getRandomSpecialChars(20).toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll((Sort) pageable);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) throws NoSuchElementException {
        userRepository.deleteById(id);
    }

    @Override
    public User updateById(UUID id, User user) throws NoSuchElementException {
        User userRep = userRepository.getReferenceById(id);
        userRep.setRoleSet(user.getRoleSet());
        return userRepository.save(userRep);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    @Override
    public void addRole(UUID id, String roleName) {
        Role role = roleService.getRoleSetByName(roleName);
        User user = userRepository.getReferenceById(id);
        user.getRoleSet().add(role);
        userRepository.save(user);
    }

    @Override
    public void removeRole(UUID id, String roleName) {
        Role role = roleService.getRoleSetByName(roleName);
        User user = userRepository.getReferenceById(id);
        user.getRoleSet().remove(role);
        userRepository.save(user);
    }

    public Stream<Character> getRandomSpecialChars(int count){
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }
}
