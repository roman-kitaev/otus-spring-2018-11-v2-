package ru.otus.HW171.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.otus.HW171.domain.Reader;
import ru.otus.HW171.domain.Role;
import ru.otus.HW171.repostory.ReadersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MongoReaderDetailsService implements UserDetailsService {

    private final static Logger LOGGER = Logger.getLogger(MongoReaderDetailsService.class.getName());

    @Autowired
    private ReadersRepository readersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader user = readersRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Reader not found");
        }

        LOGGER.log(Level.INFO, username + " is trying to log in");
        List<SimpleGrantedAuthority> permissions = new ArrayList<>();
        for(Role role : user.getRoles()) {
            for(String permission : role.getPermissions()) {
                permissions.add(new SimpleGrantedAuthority(permission));
            }
        }

        return new User(user.getUsername(), user.getPassword(), permissions);
    }
}
