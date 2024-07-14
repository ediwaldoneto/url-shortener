package github.com.ediwaldoneto.url.shortener.domain.repository;

import github.com.ediwaldoneto.url.shortener.domain.model.Url;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository {

    Url save(final Url url);

    Optional<Url> findById(final String id);

    List<Url> findByExpirationDateBefore(final LocalDateTime dateTime);

    Optional<Url> findByOriginalUrl(String url);

    Optional<Url> findByShortUrl(String shortUrl);
}
