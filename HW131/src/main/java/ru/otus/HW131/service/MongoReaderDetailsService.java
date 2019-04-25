package ru.otus.HW131.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.otus.HW131.domain.Reader;
import ru.otus.HW131.domain.Role;
import ru.otus.HW131.repostory.ReadersRepository;

import java.util.*;

@Component
public class MongoReaderDetailsService implements UserDetailsService {

    @Autowired
    private ReadersRepository readersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader user = readersRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Reader not found");
        }

        System.out.println(username + " was logged");
        List<SimpleGrantedAuthority> permissions = new ArrayList<>();
        for(Role role : user.getRoles()) {
            for(String permission : role.getPermissions()) {
                permissions.add(new SimpleGrantedAuthority(permission));
                System.out.println(permission);
            }
        }
        System.out.println();

        return new User(user.getUsername(), user.getPassword(), permissions);
    }
}
