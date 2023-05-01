package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.request.AnimePostRequsetBody;
import academy.devdojo.springboot2.request.AnimePutRequsetBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(AnimePostRequsetBody animePostRequsetBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequsetBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequsetBody animePutRequsetBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequsetBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequsetBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
