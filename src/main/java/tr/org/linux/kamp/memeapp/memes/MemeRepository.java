package tr.org.linux.kamp.memeapp.memes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface MemeRepository extends PagingAndSortingRepository<Meme, Long> {

    Iterable<Meme> findAllByOwnerId(@Param("ownerId") Long userId);

    Page<Meme> findAllByOwnerId(@Param("ownerId") Long userId, Pageable pageable);

}
