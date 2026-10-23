package org.example;

public class Vehiculo extends Thread {
    // Atributos solicitados
    private String nombre;
    private Pistas pistas;

    public Vehiculo(String nombre, Pistas pistas) {
        this.nombre = nombre;
        this.pistas = pistas;
    }

    @Override
    public void run() {
        try {
            // 1. Intenta acceder a una pista disponible (esperará si ambas están ocupadas)
            int numeroPista = pistas.entrarPista(this.nombre);

            // 2. Realiza la prueba durante un tiempo aleatorio entre 2 y 5 segundos (2000 a 5000 ms)
            long tiempoMs = (long) (2000 + Math.random() * 3000);
            Thread.sleep(tiempoMs);

            // 3. Al finalizar, libera la pista e imprime el tiempo empleado
            double tiempoSegundos = tiempoMs / 1000.0;
            pistas.salirPista(numeroPista, this.nombre, tiempoSegundos);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
