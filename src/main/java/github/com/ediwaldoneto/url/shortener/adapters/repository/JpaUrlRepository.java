package github.com.ediwaldoneto.url.shortener.adapters.repository;

import github.com.ediwaldoneto.url.shortener.domain.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaUrlRepository extends JpaRepository<Url, String> {

    Optional<Url> findByOriginalUrl(String originalUrl);

    List<Url> findByExpirationDateBefore(LocalDateTime dateTime);


}
