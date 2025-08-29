import java.util.List;

public class GameScoreDemo {
    public static void main(String[] args) {
        // Crear el árbol AVL para almacenar jugadores
        AVL<Jugador> ranking = new AVL<>();
        
        // Insertar algunos jugadores de ejemplo
        System.out.println("=== Insertando jugadores en el ranking ===");
        ranking.insertar(new Jugador("Carlos", 1500, "Equipo A"));
        ranking.insertar(new Jugador("Ana", 1800, "Equipo B"));
        ranking.insertar(new Jugador("Luis", 1200, "Equipo C"));
        ranking.insertar(new Jugador("María", 2000, "Equipo A"));
        ranking.insertar(new Jugador("Pedro", 1600, "Equipo B"));
        ranking.insertar(new Jugador("Sofia", 1400, "Equipo C"));
        ranking.insertar(new Jugador("Diego", 1700, "Equipo A"));
        ranking.insertar(new Jugador("Laura", 1900, "Equipo B"));
        
        // Mostrar el ranking completo
        System.out.println("\n=== Ranking completo (ordenado por puntaje) ===");
        ranking.inOrder();
        
        // Verificar que el árbol esté balanceado
        System.out.println("\n=== Verificación de balance ===");
        System.out.println("¿El árbol está balanceado? " + ranking.estaBalanceado());
        System.out.println("Tamaño del árbol: " + ranking.tamanio());
        
        // Ejemplos de búsquedas de rango usando la implementación modular
        System.out.println("\n=== Búsquedas de rango (Implementación Modular) ===");
        
        // Buscar top 3 jugadores entre 1400 y 1800 puntos
        System.out.println("\nTop 3 jugadores entre 1400 y 1800 puntos:");
        List<Jugador> resultado1 = ranking.buscarTopKEnRango(
            new Jugador("", 1800, ""), // pMax
            new Jugador("", 1400, ""), // pMin (orden invertido por compareTo)
            3
        );
        for (Jugador j : resultado1) {
            System.out.println("  " + j);
        }
        
        // Buscar top 2 jugadores entre 1500 y 2000 puntos
        System.out.println("\nTop 2 jugadores entre 1500 y 2000 puntos:");
        List<Jugador> resultado2 = ranking.buscarTopKEnRango(
            new Jugador("", 2000, ""), // pMax
            new Jugador("", 1500, ""), // pMin
            2
        );
        for (Jugador j : resultado2) {
            System.out.println("  " + j);
        }
        
        // Ejemplos de búsquedas usando la implementación directa
        System.out.println("\n=== Búsquedas de rango (Implementación Directa) ===");
        
        // Buscar top 3 jugadores entre 1400 y 1800 puntos
        System.out.println("\nTop 3 jugadores entre 1400 y 1800 puntos (directo):");
        List<Jugador> resultado1Directo = ranking.buscarTopKEnRangoDirecto(
            new Jugador("", 1800, ""), // pMax
            new Jugador("", 1400, ""), // pMin
            3
        );
        for (Jugador j : resultado1Directo) {
            System.out.println("  " + j);
        }
        
        // Buscar top 5 jugadores entre 1000 y 2500 puntos (rango amplio)
        System.out.println("\nTop 5 jugadores entre 1000 y 2500 puntos (directo):");
        List<Jugador> resultado3Directo = ranking.buscarTopKEnRangoDirecto(
            new Jugador("", 2500, ""), // pMax
            new Jugador("", 1000, ""), // pMin
            5
        );
        for (Jugador j : resultado3Directo) {
            System.out.println("  " + j);
        }
        
        // Comparación de resultados
        System.out.println("\n=== Comparación de Implementaciones ===");
        System.out.println("¿Resultados iguales para implementación modular vs directa? " + 
                          resultado1.equals(resultado1Directo));
    }
}
