package tr.org.linux.kamp.memeapp.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.memes WHERE u.id = :userId")
    Optional<User> findByIdWithMemes(@Param("userId") Long id);

    Optional<User> findByUsername(@Param("username") String username);

    boolean existsByUsername(@Param("username") String username);

}
