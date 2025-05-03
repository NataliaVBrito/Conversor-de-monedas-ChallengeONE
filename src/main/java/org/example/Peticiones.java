package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Peticiones {

    private final String direccion = "https://open.er-api.com/v6/latest/USD";
    private final HttpClient cliente = HttpClient.newHttpClient();

    private HttpResponse<String> obtenerRespuesta() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .GET()
                .build();
        return cliente.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void obtenerTasasDisponibles() {
        try {
            //HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response = obtenerRespuesta();

            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(response.body(), Moneda.class);

            System.out.println(moneda.toString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las tasas " + e.getMessage());
        }
    }

    public List<String> listaTasasPedidas() {
        List<String> lista = new ArrayList<>();

        try {
            HttpResponse<String> response = obtenerRespuesta();

            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(response.body(), Moneda.class);

            if (moneda.rates == null) {
                String error = "No se pudo obtener las tasas de cambio.";
                lista.add(error);
                return lista;
            }

            Map<String, Double> rates = moneda.rates;

            if (rates != null) {
                for (String tipoMoneda : List.of("ARS", "USD", "BRL", "BOB", "CLP", "COP")) {
                    if (rates.containsKey(tipoMoneda)) {
                        lista.add(tipoMoneda + ": " + String.format("%.2f", rates.get(tipoMoneda)));
                    } else {
                        lista.add(tipoMoneda + ": No disponible");
                    }
                }
            } else {
                lista.add("No hay tasas disponibles.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al listar tasas: " + e.getMessage());
        }
        return lista;
    }

    public void convertirMoneda(String monedaACambiar, String monedaCambio, double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }

        try {
            HttpResponse<String> response = obtenerRespuesta();

            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(response.body(), Moneda.class);

            Map<String, Double> rates = moneda.rates;

            if (rates == null || !rates.containsKey(monedaACambiar) || !rates.containsKey(monedaCambio)) {
                throw new IllegalArgumentException("Moneda no soportada: " + monedaACambiar + " o " + monedaCambio);
            }

            double tasaOrigen = rates.get(monedaACambiar);
            double tasaDestino = rates.get(monedaCambio);
            double resultado = monto * (tasaDestino / tasaOrigen);

            DecimalFormat df = new DecimalFormat("#,##0.0000");
            System.out.println(monto + " " + monedaACambiar + " = " + df.format(resultado) + " " + monedaCambio);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
