package com.intuit.craft.services

import com.intuit.craft.entities.User
import com.intuit.craft.repositories.UserRepository
import com.intuit.craft.vo.UserData
import spock.lang.Specification

import java.time.Instant

class EditProfileServiceTest extends Specification {

    private UserRepository userRepository
    private EditProfileService editProfileService

    def setup() {
        userRepository = Mock(UserRepository)
        editProfileService = new EditProfileService(userRepository)
    }

    def "test editProfile"() {
        given:
            String sample_email_id = "test@test.com"
            String sample_first_name = "test_F"
            String sample_last_name = "test_L"
            Long sample_phone_number = 1234567890
            User existing_user = new User(1L, sample_first_name , sample_last_name, sample_phone_number,
                sample_email_id, Instant.now(), Instant.now())

            UserData input_user_data = new UserData( "updated_F", "updated_F", sample_email_id, 9810101010)
            User updated_user = existing_user

            updated_user.setFirstName("updated_F")
            updated_user.setLastName("updated_L")
            updated_user.setMobileNumber(9810101010)
        when:
            editProfileService.editProfile(sample_email_id, input_user_data)
        then:
            1 * userRepository.findByEmail(sample_email_id) >> existing_user
            1 * userRepository.save(updated_user)
    }
}
