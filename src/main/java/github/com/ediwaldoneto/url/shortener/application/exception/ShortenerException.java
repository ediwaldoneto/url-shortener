package github.com.ediwaldoneto.url.shortener.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ShortenerException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Shortener internal server error");
        return pb;
    }
}
