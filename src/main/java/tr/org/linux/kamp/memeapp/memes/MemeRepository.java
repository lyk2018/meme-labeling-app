package tr.org.linux.kamp.memeapp.memes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface MemeRepository extends CrudRepository<Meme, Long> {

    Iterable<Meme> findAllByOwnerId(@Param("ownerId") Long userId);

}
