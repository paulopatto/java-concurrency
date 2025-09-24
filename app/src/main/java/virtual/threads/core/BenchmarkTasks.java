package virtual.threads.core;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class BenchmarkTasks {

    private static final String PASSWORD = "my-super-secret-password-for-bcrypt";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
    private static final Encoding encoding = registry.getEncoding(EncodingType.CL100K_BASE);

    public static String cpuIntensiveTask() {
        return BCrypt.withDefaults().hashToString(12, PASSWORD.toCharArray());
    }

    public static String apiCall() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return "API call failed: " + e.getMessage();
        }
    }

    public static int diskOp() {
        // Lê o arquivo do disco e tokeniza o conteúdo a cada chamada para ser pesado mesmo
        String fileContent;
        try (InputStream stream = BenchmarkTasks.class.getClassLoader().getResourceAsStream("tecnologia-e-inovacao.md")) {
            if (stream == null) {
                throw new IOException("Resource file not found");
            }
            fileContent = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            fileContent = "Error reading file: " + e.getMessage();
        }
        return encoding.countTokens(fileContent);
    }
}
