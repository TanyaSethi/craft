package com.intuit.craft.services;

import com.intuit.craft.exceptions.UserDoesNotExistException;
import com.intuit.craft.repositories.UserRepository;
import com.intuit.craft.vo.UserData;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserData fetchProfile(String emailId) {
        var user = userRepository.findByEmail(emailId);
        if(null == user) {
            throw new UserDoesNotExistException("This user does not exist. Please sign up");
        }
        user.setLastLoginTimestamp(Instant.now());
        userRepository.save(user);
        return new UserData(user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobileNumber());
    }
}
