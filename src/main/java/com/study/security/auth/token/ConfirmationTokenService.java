package com.study.security.auth.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public void setConfirmedAt(String token){
        Optional<ConfirmationToken> confirmationToken = getConfirmationToken(token);

        if(confirmationToken.isPresent()) {
            confirmationToken.ifPresent(t -> {
                t.setConfirmedAt(LocalDateTime.now());
                confirmationTokenRepository.save(t);
            });
        }
    }

    public Optional<ConfirmationToken> getConfirmationToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }
}
