package tr.org.linux.kamp.memeapp.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.org.linux.kamp.memeapp.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    Iterable<Meme> findAll() {
        return memeRepository.findAll();
    }

    public Iterable<Meme> findAllByUserId(Long id) {
        return memeRepository.findAllByOwnerId(id);
    }

    Meme findById(Long id) {
        final Optional<Meme> memeOptional = memeRepository.findById(id);
        return memeOptional.orElseThrow(ResourceNotFoundException::new);
    }

    Meme save(Meme meme) {
        return memeRepository.save(meme);
    }

    void delete(Long id) {
        memeRepository.deleteById(id);
    }

    /**
     * @param meme the meme instance coming from HTML form
     * @return updated meme
     */
    void update(Meme meme) {
        final Meme persistentMeme = this.findById(meme.getId());

        persistentMeme.setName(meme.getName());
        persistentMeme.setArtist(meme.getArtist());
        persistentMeme.setDescription(meme.getDescription());
        persistentMeme.setUrl(meme.getUrl());

        this.save(persistentMeme);
    }

}
