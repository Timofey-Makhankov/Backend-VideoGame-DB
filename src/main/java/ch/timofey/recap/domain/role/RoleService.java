package ch.timofey.recap.domain.role;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getDefaultRoles() {
        return roleRepository.findByName("USER");
    }

    public Role getRoleSetByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}
