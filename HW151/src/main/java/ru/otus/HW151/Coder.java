package ru.otus.HW151;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.HW151.domain.CryptedVeryImportantMessage;
import ru.otus.HW151.domain.VeryImportantMessage;

@Service
public class Coder {
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public CryptedVeryImportantMessage encode(
            VeryImportantMessage msg) {
        return new CryptedVeryImportantMessage(msg.getId(),
                encoder.encode(msg.getMsg()));
    }
}
