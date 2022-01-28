package com.intuit.craft.controllers;

import com.intuit.craft.services.EditProfileService;
import com.intuit.craft.services.ProfileService;
import com.intuit.craft.services.SignupService;
import com.intuit.craft.vo.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SignupService signupService;
    private final ProfileService profileService;
    private final EditProfileService editProfileService;

    public UserController(SignupService signupService, ProfileService profileService, EditProfileService editProfileService) {
        this.signupService = signupService;
        this.profileService = profileService;
        this.editProfileService = editProfileService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> newUserSignUp(@RequestBody UserData userData) {
        signupService.createNewUserProfile(userData);
        return ResponseEntity.ok("User signed up successfully!");
    }

    @GetMapping("/user/profile")
    public ResponseEntity<Object> newUserSignUp(@RequestParam String email) {
        var user = profileService.fetchProfile(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/profile/edit")
    public ResponseEntity<String> editUserProfile(@RequestParam String email, @RequestBody UserData userData) {
         editProfileService.editProfile(email, userData);
        return ResponseEntity.ok("User updated successfully!");
    }

}
