import java.io.FileReader;
import java.util.HashMap;

public class App {
    boolean todosPares(int[] arr) {
        boolean rta = true; // 1
        int aux = arr.length; // 1
        for(int i = 0; i < aux; i++) { // 1 +  n + 1 + n = 2 + 2n
            rta = rta && (arr[i]%2)==0; // 5 n
        }
        return rta; // 1
    } // f(n) = 1 + 1 + 2 + 2n + 5n + 1 = 5 + 7n
    // f(n) <= c g(n)
    // 5 + 7n <= 8n   // termino dominante mas 1
    // 5/n + 7n/n <= 8n/n  // 
    // 5/n + 7 <= 8
    // para n = 1, no cumple
    // para n = 2, no cumple
    // ..
    // para n = 5 cumple  =>  f(n) pertenece a O(n) a partir de n >= 5  y c = 8  



    public static void main(String[] args)  {
        /* 
        HashMap<String, Jugador> jugadores = new HashMap<>();
        jugadores.put("Jugador1", new Jugador("Alice", 25));
        jugadores.put("Jugador2", new Jugador("Bob", 30));

        System.out.println(jugadores.get("Jugador"));

        jugadores.values().forEach(jugador -> {
            System.out.println("Nombre: " + jugador.getNombre() + ", Edad: " + jugador.getEdad());
        });
        */
    }
}
