package com.intuit.craft.services;

import com.intuit.craft.entities.User;
import com.intuit.craft.exceptions.InvalidOperationException;
import com.intuit.craft.repositories.UserRepository;
import com.intuit.craft.vo.UserData;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.regex.Pattern;

import static com.intuit.craft.constants.UserDataConstants.EMAILID_PATTERN;

@Service
public class SignupService {

    private final UserRepository userRepository;

    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUserProfile(UserData userData) {
        if (isExistingUser(userData.getEmail())) {
            throw new InvalidOperationException("This user already exists -" + userData.getEmail());
        }
        if (isInvalidEmailId(userData.getEmail())) {
            throw new InvalidOperationException("This email id is invalid -" + userData.getEmail());
        }

        var user = prepareUserDataForRetention(userData);
        userRepository.save(user);

    }

    private boolean isExistingUser(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isInvalidEmailId(String email) {
        return !Pattern.compile(EMAILID_PATTERN)
                .matcher(email)
                .matches();
    }

    private User prepareUserDataForRetention(UserData userData) {
        User user = new User();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getSecondName());
        user.setEmail(userData.getEmail());
        user.setCreatedTimestamp(Instant.now());
        user.setMobileNumber(userData.getPhoneNumber());
        return user;
    }

}
