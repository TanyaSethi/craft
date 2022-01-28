package com.intuit.craft.services;

import com.intuit.craft.exceptions.UserDoesNotExistException;
import com.intuit.craft.repositories.UserRepository;
import com.intuit.craft.vo.UserData;
import org.springframework.stereotype.Service;

@Service
public class EditProfileService {
    private final UserRepository userRepository;

    public EditProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void editProfile(String emailId, UserData userData) {
        var user = userRepository.findByEmail(emailId);
        if(null == user) {
            throw new UserDoesNotExistException("This user does not exist. Please sign up");
        }
        user.setMobileNumber(userData.getPhoneNumber());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getSecondName());
        userRepository.save(user);
    }
}
