package com.intuit.craft.services

import com.intuit.craft.entities.User
import com.intuit.craft.exceptions.UserDoesNotExistException
import com.intuit.craft.repositories.UserRepository
import com.intuit.craft.vo.UserData
import spock.lang.Specification

import java.time.Instant

class ProfileServiceTest extends Specification {

    private ProfileService profileService;
    private UserRepository userRepository;

    def "setup"() {
        userRepository = Mock(UserRepository)
        profileService = new ProfileService(userRepository)
    }
    def "when a user logs in, profile for his particular email id is fetched - user record is found"() {
        given:

            String sample_email_id = "test@test.com"
            String sample_first_name = "test_F"
            String sample_last_name = "test_L"
            Long sample_phone_number = 1234567890
            User test_user = new User(1L, sample_first_name , sample_last_name, sample_phone_number,
                    sample_email_id, Instant.now(), Instant.now())
            UserData testUserData = new UserData(sample_first_name, sample_last_name, sample_email_id,
                    sample_phone_number)

        when:
            UserData actualUserData = profileService.fetchProfile(sample_email_id)
        then:
            1 * userRepository.findByEmail(sample_email_id) >> test_user
            test_user.setLastLoginTimestamp(Instant.now())
            1 * userRepository.save(test_user)
            testUserData == actualUserData
    }

    def "when a user logs in, profile for his particular email id is fetched - user record is not present"() {
        given:

        String sample_email_id = "test@test.com"
        userRepository.findByEmail(sample_email_id) >> null;
        when:
            profileService.fetchProfile(sample_email_id)
        then:
            thrown(UserDoesNotExistException)
            0 * userRepository.save(_)
    }
}
