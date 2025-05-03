package org.example;

import java.util.Map;

public class Moneda {
    public String result;
    public String provider;
    public String documentation;
    public String terms_of_use;
    public long time_last_update_unix;
    public String time_last_update_utc;
    public long time_next_update_unix;
    public String time_next_update_utc;
    public long time_eol_unix;
    public String base_code;
    public Map<String, Double> rates;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Base: ").append(base_code).append("\n");
        if (rates != null) {
            if (rates.containsKey("ARS")) {
                sb.append("ARS: ").append(String.format("%.2f", rates.get("ARS"))).append("\n");
            }
            if (rates.containsKey("USD")) {
                sb.append("USD: ").append(String.format("%.2f", rates.get("USD"))).append("\n");
            }
            if (rates.containsKey("BRL")) {
                sb.append("BRL: ").append(String.format("%.2f", rates.get("BRL"))).append("\n");
            }
            if (rates.containsKey("BOB")) {
                sb.append("BOB: ").append(String.format("%.2f", rates.get("BOB"))).append("\n");
            }
            if (rates.containsKey("CLP")) {
                sb.append("CLP: ").append(String.format("%.2f", rates.get("CLP"))).append("\n");
            }
            if (rates.containsKey("COP")) {
                sb.append("COP: ").append(String.format("%.2f", rates.get("COP"))).append("\n");
            }

        } else {
            sb.append("No hay tasas disponibles\n");
        }
        return sb.toString();
    }
}
