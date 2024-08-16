package com.study.security.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void enableUser(String email) {
        Optional<User> user = findUserByEmail(email);

        if(user.isPresent()) {
            user.ifPresent(u -> {
                u.setEnabled(true);
                userRepository.save(u);
            });
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
