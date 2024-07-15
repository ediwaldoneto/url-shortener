package github.com.ediwaldoneto.url.shortener.application.service;

import github.com.ediwaldoneto.url.shortener.adapters.repository.UrlJpaRepository;
import github.com.ediwaldoneto.url.shortener.application.dto.UrlRequest;
import github.com.ediwaldoneto.url.shortener.application.dto.UrlResponse;
import github.com.ediwaldoneto.url.shortener.domain.model.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UrlServiceTest {

    @Mock
    private UrlJpaRepository urlJpaRepository;

    @InjectMocks
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(urlService, "characters", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        ReflectionTestUtils.setField(urlService, "minLength", 5);
        ReflectionTestUtils.setField(urlService, "maxLength", 10);
        ReflectionTestUtils.setField(urlService, "baseUrl", "http://short.url/");
        ReflectionTestUtils.setField(urlService, "expirationDate", 7L);
    }


    @Test
    void testShortenUrl() {
        UrlRequest request = new UrlRequest();
        request.setUrl("http://example.com");
        Url url = Url.newUrl("http://example.com", "http://short.url/abcde", 7L);

        when(urlJpaRepository.findByOriginalUrl(anyString())).thenReturn(Optional.empty());
        when(urlJpaRepository.save(any(Url.class))).thenReturn(url);

        UrlResponse response = urlService.shortenUrl(request);

        assertNotNull(response);
        assertTrue(response.getUrl().startsWith("http://short.url/"));
        Mockito.verify(urlJpaRepository, times(1)).save(any(Url.class));
    }

    @Test
    void testShortenUrlWhenAlreadyExists() {
        UrlRequest request = new UrlRequest();
        request.setUrl("http://example.com");
        Url url = Url.newUrl("http://example.com", "http://short.url/abcde", 7L);

        when(urlJpaRepository.findByOriginalUrl(anyString())).thenReturn(Optional.of(url));

        UrlResponse response = urlService.shortenUrl(request);

        assertNotNull(response);
        assertEquals("http://short.url/abcde", response.getUrl());
    }
}