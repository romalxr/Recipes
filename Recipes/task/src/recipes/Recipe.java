package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import recipes.registration.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotBlank
    String category;

    @UpdateTimestamp
    LocalDateTime date;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    List<String> ingredients;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    List<String> directions;

    @JsonIgnore
    @ManyToOne
    User user;
}