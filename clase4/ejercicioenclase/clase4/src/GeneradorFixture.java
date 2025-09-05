import java.util.ArrayList;
import java.util.List;

public class GeneradorFixture {

    public static void generar(int n) {
        // Validación: El algoritmo funciona para n par. La consigna pide potencia de 2.
        if (n % 2 != 0) {
            System.out.println("El número de equipos debe ser par.");
            return;
        }

        // Creamos la lista de equipos, que serán representados por números.
        List<Integer> equipos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            equipos.add(i);
        }

        // Separamos el equipo fijo (el último)
        int equipoFijo = equipos.remove(n - 1);

        // Generamos las n-1 jornadas
        for (int jornada = 0; jornada < n - 1; jornada++) {
            System.out.println("--- Jornada " + (jornada + 1) + " ---");

            // El equipo fijo juega contra el primer equipo de la lista rotativa
            System.out.println("Partido: " + equipoFijo + " vs. " + equipos.get(0));

            // Armamos el resto de los partidos
            for (int i = 1; i < n / 2; i++) {
                int equipoLocal = equipos.get(i);
                int equipoVisitante = equipos.get(n - 1 - i);
                System.out.println("Partido: " + equipoLocal + " vs. " + equipoVisitante);
            }

            // Rotación: movemos el último elemento al principio de la lista de rotación.
            // Esto prepara la lista para la siguiente jornada.
            int ultimo = equipos.remove(equipos.size() - 1);
            equipos.add(0, ultimo);
        }
    }

    public static void main(String[] args) {
        int numeroDeEquipos = 8; // n debe ser una potencia de 2, ej: 4, 8, 16
        System.out.println("Generando fixture para " + numeroDeEquipos + " equipos...");
        generar(numeroDeEquipos);
    }
}