package tr.org.linux.kamp.memeapp.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.memes WHERE u.id = :userId")
    Optional<User> findByIdWithMemes(@Param("userId") Long id);

    boolean existsByUsername(@Param("username") String username);

}
