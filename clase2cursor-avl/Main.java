public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL ÁRBOL AVL ===\n");
        
        // Crear un árbol AVL de enteros
        Avl<Integer> arbol = new Avl<>();
        
        System.out.println("1. Insertando elementos...");
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(30);
        arbol.insertar(40);
        arbol.insertar(50);
        arbol.insertar(25);
        arbol.insertar(15);
        arbol.insertar(5);
        
        System.out.println("Elementos insertados: 10, 20, 30, 40, 50, 25, 15, 5");
        
        System.out.println("\n2. Estado actual del árbol:");
        arbol.imprimirArbol();
        
        System.out.println("\n3. Recorridos del árbol:");
        arbol.inorden();
        arbol.preorden();
        arbol.postorden();
        
        System.out.println("\n4. Información del árbol:");
        System.out.println("Altura del árbol: " + arbol.alturaArbol());
        System.out.println("¿Está vacío? " + arbol.estaVacio());
        
        System.out.println("\n5. Búsquedas:");
        System.out.println("¿Existe el 25? " + arbol.buscar(25));
        System.out.println("¿Existe el 100? " + arbol.buscar(100));
        System.out.println("¿Existe el 5? " + arbol.buscar(5));
        
        System.out.println("\n6. Eliminando elementos...");
        System.out.println("Eliminando el 20...");
        arbol.eliminar(20);
        
        System.out.println("\nEstado del árbol después de eliminar el 20:");
        arbol.imprimirArbol();
        
        System.out.println("\n7. Recorrido inorden después de la eliminación:");
        arbol.inorden();
        
        System.out.println("\n8. Nueva altura del árbol: " + arbol.alturaArbol());
        
        System.out.println("\n=== DEMOSTRACIÓN COMPLETADA ===");
    }
}
