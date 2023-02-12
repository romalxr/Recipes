package recipes.registration;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SimpleUser {

    @NotNull
    @NotBlank
    @Email
    @Pattern(regexp=".+@.+\\..+")
    String email;

    @NotNull
    @NotBlank
    @Size(min = 8)
    String password;
}

