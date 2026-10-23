package org.example;

public class Main {
    public static void main(String[] args) {
        // Creamos el recurso compartido (las 2 pistas de prueba)
        Pistas pistas = new Pistas();

        // Creamos los 4 vehículos (hilos)
        Vehiculo v1 = new Vehiculo("Vehículo 1", pistas);
        Vehiculo v2 = new Vehiculo("Vehículo 2", pistas);
        Vehiculo v3 = new Vehiculo("Vehículo 3", pistas);
        Vehiculo v4 = new Vehiculo("Vehículo 4", pistas);

        // Iniciamos la ejecución concurrente de los vehículos
        v1.start();
        v2.start();
        v3.start();
        v4.start();
    }
}
