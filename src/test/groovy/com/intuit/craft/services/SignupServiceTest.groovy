package com.intuit.craft.services

import com.intuit.craft.entities.User
import com.intuit.craft.exceptions.InvalidOperationException
import com.intuit.craft.repositories.UserRepository
import com.intuit.craft.vo.UserData
import spock.lang.Specification

import java.time.Instant

class SignupServiceTest extends Specification {
        private SignupService signupService
        private UserRepository userRepository;

        def "setup"() {
            userRepository = Mock(UserRepository)
            signupService = new SignupService(userRepository)
        }

    def "test a new user profile creation - with valid data (email)"() {
        given:
        String sample_email_id = "test@test.com"
        String sample_first_name = "test_F"
        String sample_last_name = "test_L"
        Long sample_phone_number = 1234567890
        UserData testUserData = new UserData(sample_first_name, sample_last_name, sample_email_id, sample_phone_number)
//            User test_user = new User(0, sample_first_name , sample_last_name, sample_phone_number,
//                    sample_email_id, _ as Instant, null)
        when:
            signupService.createNewUserProfile(testUserData)
        then:
            1 * userRepository.existsByEmail(sample_email_id)
            1 * userRepository.save(_);

    }

    def "test a new user profile creation - with Invalid data (email)"() {
        given:
        String sample_email_id = "test.test.com"
        String sample_first_name = "test_F"
        String sample_last_name = "test_L"
        Long sample_phone_number = 1234567890
        UserData testUserData = new UserData(sample_first_name, sample_last_name, sample_email_id, sample_phone_number)

        when:
        signupService.createNewUserProfile(testUserData)
        then:
        1 * userRepository.existsByEmail(sample_email_id)
        thrown( InvalidOperationException)
    }

    def "test a new user profile creation - if user already exists"() {
        given:
        String sample_email_id = "test.test.com"
        String sample_first_name = "test_F"
        String sample_last_name = "test_L"
        Long sample_phone_number = 1234567890
        UserData testUserData = new UserData(sample_first_name, sample_last_name, sample_email_id, sample_phone_number)

        when:
        signupService.createNewUserProfile(testUserData)
        then:
        1 * userRepository.existsByEmail(sample_email_id) >> true
        thrown( InvalidOperationException)
    }
}
