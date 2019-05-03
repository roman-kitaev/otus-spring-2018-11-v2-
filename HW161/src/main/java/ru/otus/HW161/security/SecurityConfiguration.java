package ru.otus.HW161.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.HW161.service.MongoReaderDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MongoReaderDetailsService readerDetailsService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/api/books").permitAll()
                .and()
                .authorizeRequests().antMatchers("/admin").authenticated()
                .and()
                .authorizeRequests().antMatchers( "/edit", "/api/book/**", "/api/editbook").hasRole("CAN_EDIT")
                .and()
                .authorizeRequests().antMatchers("/api/delete/**").hasRole("CAN_DELETE")
                .and()
                .authorizeRequests().antMatchers( "/api/addbook", "/api/authors", "/api/genres").hasRole("CAN_ADD")
                .and()
                .formLogin()
                .and()
                .sessionManagement().disable();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(readerDetailsService);
    }
}
