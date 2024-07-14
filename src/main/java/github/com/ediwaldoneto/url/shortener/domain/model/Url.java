package github.com.ediwaldoneto.url.shortener.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "url")
public class Url {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ORIGINAL_URL")
    private String originalUrl;
    @Column(name = "SHORT_URL")
    private String shortUrl;
    @Column(name = "EXPIRATION_DATE")
    private LocalDateTime expirationDate;


    private Url() {
    }

    private Url(String id, String originalUrl, String shortUrl, LocalDateTime expirationDate) {
        this.id = id;
        this.originalUrl = Objects.requireNonNull(originalUrl, "");
        this.shortUrl = Objects.requireNonNull(shortUrl, "");
        this.expirationDate = expirationDate;
    }

    public static Url newUrl(String originalUrl, String shortUrl, long expirationDate) {
        final var id = UUID.randomUUID().toString();
        final var expiate = LocalDateTime.now().plusDays(expirationDate != 0 ? expirationDate : 30L);
        return new Url(id, originalUrl, shortUrl, expiate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
