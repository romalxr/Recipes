package recipes.registration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.Recipe;

import java.util.List;

@Repository
public interface UserRepositoryH2 extends CrudRepository<User, String>{

    List<User> findByUsernameIgnoreCase(String Username);
}
