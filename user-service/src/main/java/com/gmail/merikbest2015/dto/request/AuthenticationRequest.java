package com.gmail.merikbest2015.dto.request;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.gmail.merikbest2015.constants.ErrorMessage.*;

@Data
public class AuthenticationRequest {

    @Email(regexp = ".+@.+\\..+", message = EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = EMPTY_PASSWORD)
    @Size(min = 8, message = SHORT_PASSWORD)
    private String password;
}
