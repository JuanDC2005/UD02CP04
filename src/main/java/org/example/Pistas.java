package org.example;

import java.util.Locale;

public class Pistas {
    // Array de booleanos que indica la disponibilidad de cada pista (true = libre, false = ocupada)
    private boolean[] disponibles;

    public Pistas() {
        // Inicializamos las 2 pistas como disponibles
        this.disponibles = new boolean[]{true, true};
    }

    // Método sincronizado para que un vehículo entre a una pista libre
    public synchronized int entrarPista(String nombreVehiculo) throws InterruptedException {
        // Mientras no haya pistas disponibles, el vehículo espera
        while (!hayPistaLibre()) {
            wait();
        }

        // Buscamos la primera pista que esté libre
        for (int i = 0; i < disponibles.length; i++) {
            if (disponibles[i]) {
                disponibles[i] = false; // Marcamos la pista como ocupada
                int numeroPista = i + 1;
                System.out.println(nombreVehiculo + " ha entrado en la pista " + numeroPista + ".");
                return numeroPista;
            }
        }

        return -1;
    }

    // Método sincronizado para que un vehículo libere la pista al finalizar
    public synchronized void salirPista(int numeroPista, String nombreVehiculo, double tiempoSegundos) {
        disponibles[numeroPista - 1] = true; // Marcamos la pista como disponible
        System.out.println(nombreVehiculo + " completó la prueba en " + String.format(Locale.US, "%.3f", tiempoSegundos) + " segundos.");
        System.out.println(nombreVehiculo + " ha salido de la pista " + numeroPista + ".");
        notifyAll(); // Notificamos a los vehículos en espera
    }

    // Método auxiliar para comprobar si existe alguna pista disponible
    private boolean hayPistaLibre() {
        for (boolean disponible : disponibles) {
            if (disponible) {
                return true;
            }
        }
        return false;
    }
}
