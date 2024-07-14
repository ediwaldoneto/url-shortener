package github.com.ediwaldoneto.url.shortener.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidUrlException extends ShortenerException {


    public InvalidUrlException() {
        // document why this constructor is empty
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("invalid url");
        return pb;
    }
}
