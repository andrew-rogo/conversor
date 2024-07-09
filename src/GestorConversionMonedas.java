import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestorConversionMonedas {
    private APIExchangeRate apiExchangeRate;
    private List<JsonObject> conversiones;

    public GestorConversionMonedas() {
        this.apiExchangeRate = new APIExchangeRate();
        this.conversiones = new ArrayList<>();
    }

    // Método para convertir moneda y almacenar la conversión
    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad){
        try {
            double tasa = apiExchangeRate.obtenerTasaCambio(monedaOrigen, monedaDestino);
            double cantidadConvertida = cantidad * tasa;

            // Crear un objeto JSON para almacenar los detalles de la conversión
            JsonObject conversion = new JsonObject();
            conversion.addProperty("origen", monedaOrigen);
            conversion.addProperty("destino", monedaDestino);
            conversion.addProperty("cantidad", cantidad);
            conversion.addProperty("cantidadConvertida", cantidadConvertida);
            conversion.addProperty("tasa", tasa);
            conversiones.add(conversion);

            return cantidadConvertida;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0;
        }

    }
    // Método para guardar las conversiones realizadas en un archivo JSON
    public void guardarConversionesEnJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();
        for (JsonObject conversion : conversiones) {
            jsonArray.add(conversion);
        }

        try (FileWriter file = new FileWriter("conversiones.json")) {
            file.write(gson.toJson(jsonArray));
            System.out.println("Se han guardado las conversiones en conversiones.json");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar el resumen de todas las conversiones realizadas
    public void mostrarResumenConversiones() {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get("conversiones.json")));
            System.out.println("Resumen de todas las conversiones realizadas:");
            System.out.println(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
