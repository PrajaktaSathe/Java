import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
 
public class HttpsRequest {
    public static void main(String[] args) {
        var request = HttpRequest.newBuilder(URI.create("https://github.com/"))
                .GET()
                .build();
        //Prints the status Code of the HTTP-Request
        HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()))
                .thenApply(HttpResponse::statusCode)
                .thenAccept(System.out::println)
                .join();
    }
}
