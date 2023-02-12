package recipes.registration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryH2 extends CrudRepository<User, String>{
}
