package github.com.ediwaldoneto.url.shortener.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UrlNotFound extends ShortenerException {

    public UrlNotFound() {
    }

    @Override
    public ProblemDetail toProblemDetail() {
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

    }
}
