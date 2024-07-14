package github.com.ediwaldoneto.url.shortener.application.service;

import github.com.ediwaldoneto.url.shortener.adapters.repository.UrlJpaRepository;
import github.com.ediwaldoneto.url.shortener.application.dto.UrlRequest;
import github.com.ediwaldoneto.url.shortener.application.dto.UrlResponse;
import github.com.ediwaldoneto.url.shortener.application.exception.ShortenerException;
import github.com.ediwaldoneto.url.shortener.domain.model.Url;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Value("${url.shortener.characters}")
    private String characters;

    @Value("${url.shortener.min-length}")
    private int minLength;

    @Value("${url.shortener.max-length}")
    private int maxLength;

    @Value("${url.shortener.base-url}")
    private String baseUrl;

    @Value("${url.shortener.expiration-date}")
    private Long expirationDate;

    private final UrlJpaRepository urlJpaRepository;
    private final Random random = new Random();

    public UrlService(UrlJpaRepository urlJpaRepository) {
        this.urlJpaRepository = urlJpaRepository;
    }

    public UrlResponse shortenUrl(final UrlRequest request) {
        try {
            final Optional<Url> byOriginalUrl = urlJpaRepository.findByOriginalUrl(request.getUrl());
            if (byOriginalUrl.isPresent() && !isExpired(byOriginalUrl.get())) {
                return new UrlResponse(baseUrl + byOriginalUrl.get().getShortUrl());
            }
            final Url url = Url.newUrl(request.getUrl(), generateShortenedUrl(request.getUrl()), expirationDate);
            urlJpaRepository.save(url);
            return new UrlResponse(baseUrl + url.getShortUrl());
        } catch (Exception e) {
            throw new ShortenerException();
        }
    }


    private String generateShortenedUrl(final String url) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder shortURL = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            shortURL.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortURL.toString();
    }

    public boolean isExpired(Url url) {
        return url.getExpirationDate().isBefore(LocalDateTime.now());
    }
}
