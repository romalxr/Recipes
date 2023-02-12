package recipes.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    UserRepositoryH2 userRepo;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("api/register")
    public ResponseEntity<User> register(@RequestParam String email, @RequestParam String password) {
        return registerIn(email, password);
    }

    @PostMapping("api/register")
    public ResponseEntity<User> register(@Valid @RequestBody SimpleUser simpleUser) {
        return registerIn(simpleUser.getEmail(), simpleUser.getPassword());
    }

    private ResponseEntity<User> registerIn(String email, String password) {

        List<User> ex = userRepo.findByUsernameIgnoreCase(email);
        if (ex.size() > 0) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(password));

        userRepo.save(user);

        return ResponseEntity.of(Optional.of(user));
    }

}
