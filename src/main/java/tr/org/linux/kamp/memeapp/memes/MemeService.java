package tr.org.linux.kamp.memeapp.memes;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.org.linux.kamp.memeapp.exceptions.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;

    Iterable<Meme> findAll() {
        return memeRepository.findAll();
    }

    public Page<Meme> findAll(Long ownerId, Pageable pageable) {
        return memeRepository.findAllByOwnerId(ownerId, pageable);
    }

    public Iterable<Meme> findAllByUserId(Long id) {
        return memeRepository.findAllByOwnerId(id);
    }

    Meme findById(Long id) {
        final Optional<Meme> memeOptional = memeRepository.findById(id);
        return memeOptional.orElseThrow(ResourceNotFoundException::new);
    }

    public Meme save(Meme meme) {
        return memeRepository.save(meme);
    }

    void delete(Long id) {
        memeRepository.deleteById(id);
    }

    /**
     * @param meme the meme instance coming from HTML form
     * @return updated meme
     */
    @Transactional
    public void update(Meme meme) {
        final Meme persistentMeme = this.findById(meme.getId());

        persistentMeme.setName(meme.getName());
        persistentMeme.setArtist(meme.getArtist());
        persistentMeme.setDescription(meme.getDescription());
        persistentMeme.setUrl(meme.getUrl());

//        this.save(persistentMeme);
    }

}
