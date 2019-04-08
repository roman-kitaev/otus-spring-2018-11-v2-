package ru.otus.HW121.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.otus.HW121.domain.Reader;
import ru.otus.HW121.repostory.ReadersRepository;

import java.util.Arrays;
import java.util.List;

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

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("admin"));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
