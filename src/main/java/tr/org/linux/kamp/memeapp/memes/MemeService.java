package tr.org.linux.kamp.memeapp.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Meme> memeOptional = memeRepository.findById(id);
        return memeOptional.get();
    }

    Meme save(Meme meme) {
        return memeRepository.save(meme);
    }

    void delete(Long id) {
        memeRepository.deleteById(id);
    }

}
