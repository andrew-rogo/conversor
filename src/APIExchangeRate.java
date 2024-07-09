import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;


public class APIExchangeRate {
    private static final String API_KEY = "32039457322faeeb7bdde439";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Método para obtener la tasa de cambio entre dos monedas
    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String url = BASE_URL + API_KEY + "/latest/" + monedaOrigen;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();
        // Parsear la respuesta JSON usando Gson
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        return jsonObject.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
    }
}

