public class MainScoring {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL ÁRBOL AVL CON SCORING ===\n");
        
        Map<String, Integer> map = new HashMap<>();
        map.put("Ana", 1500);
        map.put("Carlos", 1800);
        map.put("Beatriz", 1200);
        map.put("David", 2000);
        map.put("Elena", 1600);
        map.put("Fernando", 1400);
        map.put("Gloria", 1700);
        map.put("Hector", 1900);
        map.put("Isabel", 1300);
        map.put("Juan", 1100);

        // Crear un árbol AVL de Scoring
        Avl<Scoring> arbolScoring = new Avl<>();
        
        System.out.println("1. Insertando jugadores con sus puntuaciones...");
        
        // Insertar varios jugadores con diferentes puntuaciones
        arbolScoring.insertar(new Scoring("Ana", 1500));
        arbolScoring.insertar(new Scoring("Carlos", 1800));
        arbolScoring.insertar(new Scoring("Beatriz", 1200));
        arbolScoring.insertar(new Scoring("David", 2000));
        arbolScoring.insertar(new Scoring("Elena", 1600));
        arbolScoring.insertar(new Scoring("Fernando", 1400));
        arbolScoring.insertar(new Scoring("Gloria", 1700));
        arbolScoring.insertar(new Scoring("Hector", 1900));
        arbolScoring.insertar(new Scoring("Isabel", 1300));
        arbolScoring.insertar(new Scoring("Juan", 1100));
        
        System.out.println("Jugadores insertados con sus puntuaciones.");
        
        System.out.println("\n2. Estado actual del árbol AVL:");
        arbolScoring.imprimirArbol();
        
        System.out.println("\n3. Recorrido inorden (ordenado por puntuación y nombre):");
        arbolScoring.inorden();
        
        System.out.println("\n4. Recorrido preorden:");
        arbolScoring.preorden();
        
        System.out.println("\n5. Información del árbol:");
        System.out.println("Altura del árbol: " + arbolScoring.alturaArbol());
        System.out.println("¿Está vacío? " + arbolScoring.estaVacio());
        
        System.out.println("\n6. Búsquedas de jugadores:");
        Scoring jugadorBuscar = new Scoring("Carlos", 1800);
        System.out.println("¿Existe Carlos con 1800 puntos? " + arbolScoring.buscar(jugadorBuscar));
        
        Scoring jugadorNoExiste = new Scoring("Pedro", 1500);
        System.out.println("¿Existe Pedro con 1500 puntos? " + arbolScoring.buscar(jugadorNoExiste));
        
        // Buscar por nombre (creando un objeto temporal)
        System.out.println("¿Existe Ana con 1500 puntos? " + arbolScoring.buscar(new Scoring("Ana", 1500)));
        
        System.out.println("\n7. Eliminando jugadores...");
        System.out.println("Eliminando a Beatriz (1200 puntos)...");
        arbolScoring.eliminar(new Scoring("Beatriz", 1200));
        
        System.out.println("\nEstado del árbol después de eliminar a Beatriz:");
        arbolScoring.imprimirArbol();
        
        System.out.println("\n8. Recorrido inorden después de la eliminación:");
        arbolScoring.inorden();
        
        System.out.println("\n9. Nueva altura del árbol: " + arbolScoring.alturaArbol());
        
        System.out.println("\n10. Demostrando el ordenamiento por puntuación:");
        System.out.println("El árbol mantiene automáticamente el orden por puntuación (mayor a menor)");
        System.out.println("y en caso de empate, por nombre alfabéticamente.");
        
        System.out.println("\n11. Insertando jugadores con puntuaciones iguales:");
        arbolScoring.insertar(new Scoring("Zara", 1500)); // Misma puntuación que Ana
        arbolScoring.insertar(new Scoring("Alberto", 1500)); // Misma puntuación que Ana
        
        System.out.println("\nEstado del árbol con puntuaciones iguales:");
        arbolScoring.imprimirArbol();
        
        System.out.println("\n12. Recorrido inorden mostrando el ordenamiento:");
        arbolScoring.inorden();
        
        System.out.println("\n=== DEMOSTRACIÓN COMPLETADA ===");
        System.out.println("\nNota: El árbol AVL mantiene automáticamente el balance");
        System.out.println("y el ordenamiento por puntuación (mayor a menor) y nombre.");
    }
}

