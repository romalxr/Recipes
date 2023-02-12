package recipes.registration;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {
    @Id
    @NotBlank
    @Email
    @Pattern(regexp=".+@.+\\..+")
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;
}
