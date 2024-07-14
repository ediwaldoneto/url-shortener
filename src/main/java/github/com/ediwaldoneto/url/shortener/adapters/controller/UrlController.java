package github.com.ediwaldoneto.url.shortener.adapters.controller;

import github.com.ediwaldoneto.url.shortener.application.dto.UrlRequest;
import github.com.ediwaldoneto.url.shortener.application.dto.UrlResponse;
import github.com.ediwaldoneto.url.shortener.application.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shorten-url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> getLoans(@RequestBody UrlRequest request) {
        return new ResponseEntity<>(urlService.shortenUrl(request), HttpStatus.OK);
    }
}