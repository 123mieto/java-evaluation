package evaluation.java.nine;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewHttpClientTest {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    public void synchronousGet() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/get"))
                .headers("someKey", "someValue", "otherKey", "otherValue")
                .GET()
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertTrue(response.body().contains("someValue"));
        assertTrue(response.body().contains("otherValue"));
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void asynchronousPost() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/text")
                .POST(HttpRequest.BodyPublishers.ofString("some data to send"))
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> assertTrue(response.contains("some data to send")))
                .get();
    }
}
