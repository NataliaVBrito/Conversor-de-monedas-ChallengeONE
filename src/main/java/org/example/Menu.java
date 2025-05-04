package org.example;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Peticiones peticiones = new Peticiones();

    boolean continuar;

    public void menu() {
        System.out.println("¡Bienvenido a Liontich");
        System.out.println("El conversor de monedas de Liontich le permite " +
                "comprobar los últimos valores de cambio " +
                "y convertir las principales monedas del mundo.");

        continuar = true;

        while (continuar) {
            System.out.println("¿Qué quieres hacer hoy?");
            System.out.println("A- Obtener valores de las tazas disponibles");
            System.out.println("B- Obtener valores de una taza especifica");
            System.out.println("C- Convertir una moneda en otra");
            System.out.println("D- Salir");

            var respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("A")) {
                System.out.println("Todas las tazas disponibles son en base a la moneda estaunidense USD");
                peticiones.obtenerTasasDisponibles();

                salir();
            } else if (respuesta.equalsIgnoreCase("B")) {
                System.out.println("¿Qué valor quieres saber?");
                System.out.println("USD: Moneda estaunidense\nARG: Moneda argentina\n" +
                        "BRL: Moneda Brasilera\nBOB: Moneda Boliviana\nCLP: Moneda chilena\n" +
                        "COP: Moneda colombiana");

                respuesta = sc.nextLine().toUpperCase();

                peticiones.tasaPedida(respuesta);

                salir();
            } else if (respuesta.equalsIgnoreCase("C")) {
                System.out.println("Por favor ingrese la tasa a convertir");
                System.out.println("Ejemplo:ARS");

                respuesta = sc.nextLine().toUpperCase();

                System.out.println("Ahora la tasa por la que la va a convertir");
                System.out.println("Ejemplo:USD");

                var respuesta1 = sc.nextLine().toUpperCase();

                System.out.println("Por ultimo el valor que desea convertir");
                System.out.println("Ejemplo:1");

                double respuesta2 = sc.nextDouble();

                peticiones.convertirMoneda(respuesta, respuesta1, respuesta2);

                salir();
            } else {
                salir();
            }
        }
        cerrarScanner();
    }

    public boolean salir() {
        System.out.println("¿Quieres continuar con otra operación?");
        System.out.println("S- Para continuar");
        System.out.println("N- Para salir");

        var salir = sc.nextLine();

        if (salir.toUpperCase().equals("S")) {
            return continuar = true;
        } else {
            System.out.println("Gracias por usar nuestro conversor de monedas Liontich");
            return continuar = false;
        }
    }

    public void cerrarScanner() {
        if (sc != null) {
            sc.close();
            sc = null; // Para evitar doble cierre si se llama varias veces
        }
    }
}
