package recipes.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class RegistrationController {

    final UserRepositoryH2 userRepo;
    final PasswordEncoder encoder;

    @Autowired
    public RegistrationController(UserRepositoryH2 userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("api/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        if (userRepo.existsById(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.of(Optional.of(user));
    }

}
