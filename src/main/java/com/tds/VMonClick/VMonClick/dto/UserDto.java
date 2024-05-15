package com.tds.VMonClick.VMonClick.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @NotNull
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email invalid")
    @NonNull
    private String email;

    @NotBlank(message = "Password is mandatory")
    @NotNull
    // @JsonIgnore
    // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
    // message = "Password must be at least 8 characters long, contain at least one digit, one lower
    // case, one upper case and one special character")
    private String password;
}
