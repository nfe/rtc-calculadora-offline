import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// Distroless has no curl: the image checks itself with the bundled JRE.
public class Healthcheck {
    public static void main(String[] a) throws Exception {
        var req = HttpRequest.newBuilder(URI.create("http://127.0.0.1:9000/health")).timeout(Duration.ofSeconds(3)).build();
        var res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.discarding());
        System.exit(res.statusCode() == 200 ? 0 : 1);
    }
}
