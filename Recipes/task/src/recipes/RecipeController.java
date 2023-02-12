package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import recipes.registration.User;
import recipes.registration.UserRepositoryH2;

import javax.validation.Valid;
import java.util.*;

@RestController
public class RecipeController {

    RecipeRepository repository;

    @Autowired
    UserRepositoryH2 userRepo;

    @Autowired
    public RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Object> AddRecipe(@AuthenticationPrincipal UserDetails details, @Valid @RequestBody Recipe recipe) {
        List<User> users = userRepo.findByUsernameIgnoreCase(details.getUsername());
        recipe.setUser(users.get(0));
        repository.save(recipe);
        return ResponseEntity.ok(Map.of("id", recipe.getId()));
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@AuthenticationPrincipal UserDetails details, @PathVariable long id, @Valid @RequestBody Recipe recipe) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<User> users = userRepo.findByUsernameIgnoreCase(details.getUsername());
        if(repository.findById(id).get().getUser() != users.get(0)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Recipe existRecipe = repository.findById(id).get();
        recipe.setId(existRecipe.getId());
        recipe.setUser(existRecipe.getUser());
        repository.save(recipe);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if (category.isPresent() == name.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if (category.isPresent()) {
            return ResponseEntity.of(Optional.of(repository.findByCategoryIgnoreCaseOrderByDateDesc(category.get())));
        } else {
            return ResponseEntity.of(Optional.of(repository.findByNameContainsIgnoreCaseOrderByDateDesc(name.get())));
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@AuthenticationPrincipal UserDetails details, @PathVariable long id) {
        if(repository.existsById(id)){
            List<User> users = userRepo.findByUsernameIgnoreCase(details.getUsername());
            if(repository.findById(id).get().getUser() != users.get(0)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}