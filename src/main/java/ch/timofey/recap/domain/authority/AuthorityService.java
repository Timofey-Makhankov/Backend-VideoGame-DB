package ch.timofey.recap.domain.authority;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    public AuthorityService(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }
}
