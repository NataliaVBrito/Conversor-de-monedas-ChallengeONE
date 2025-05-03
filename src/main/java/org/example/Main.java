package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Peticiones peticiones = new Peticiones();
        peticiones.obtenerTasasDisponibles();
        List<String> tasas = peticiones.listaTasasPedidas();
        tasas.forEach(System.out::println);
    }
}