package github.com.ediwaldoneto.url.shortener.adapters.repository;

import github.com.ediwaldoneto.url.shortener.domain.model.Url;
import github.com.ediwaldoneto.url.shortener.domain.repository.UrlRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UrlJpaRepository implements UrlRepository {

    private final JpaUrlRepository repository;

    public UrlJpaRepository(JpaUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Url save(Url url) {
        return repository.save(url);
    }

    @Override
    public Optional<Url> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Url> findByExpirationDateBefore(LocalDateTime dateTime) {
        return repository.findByExpirationDateBefore(dateTime);
    }

    @Override
    public Optional<Url> findByOriginalUrl(String url) {
        return repository.findByOriginalUrl(url);
    }
}
