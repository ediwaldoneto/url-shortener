package github.com.ediwaldoneto.url.shortener.application.dto;

public class UrlResponse {

    private String url;

    public UrlResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
