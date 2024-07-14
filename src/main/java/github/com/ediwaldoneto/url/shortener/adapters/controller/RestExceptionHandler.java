package github.com.ediwaldoneto.url.shortener.adapters.controller;

import github.com.ediwaldoneto.url.shortener.application.exception.ShortenerException;
import github.com.ediwaldoneto.url.shortener.application.exception.UrlNotFound;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ShortenerException.class)
    public ProblemDetail handleShortenerExceptionException(ShortenerException e) {
        return e.toProblemDetail();
    }


    @ExceptionHandler(UrlNotFound.class)
    public ProblemDetail handleUrlNotFoundExceptionException(UrlNotFound e) {
        return e.toProblemDetail();
    }
}
