package ru.otus.HW131.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.otus.HW131.domain.Reader;
import ru.otus.HW131.repostory.ReadersRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MongoReaderDetailsService implements UserDetailsService {

    private Map<String, List<SimpleGrantedAuthority>> roles = new HashMap<>();

    {
        roles.put("admin1", Arrays.asList(new SimpleGrantedAuthority("ROLE_CAN_DELETE")));
        roles.put("admin2", Arrays.asList(new SimpleGrantedAuthority("ROLE_CAN_EDIT")));
        roles.put("admin3", Arrays.asList(new SimpleGrantedAuthority("ROLE_CAN_ADD")));
    }

    @Autowired
    private ReadersRepository readersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader user = readersRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Reader not found");
        }

        return new User(user.getUsername(), user.getPassword(), roles.get(username));
    }
}
