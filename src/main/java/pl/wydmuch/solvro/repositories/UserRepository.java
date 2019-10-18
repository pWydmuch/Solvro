package pl.wydmuch.solvro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wydmuch.solvro.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmail(String email);
}
