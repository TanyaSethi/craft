package com.intuit.craft.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserData {

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String secondName;

    @JsonProperty("email")
    String email;

    @Size(min = 8, max = 10)
    @JsonProperty("phone_number")
    Long phoneNumber;
}
