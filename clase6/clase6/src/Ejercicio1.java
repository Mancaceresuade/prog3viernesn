public class Ejercicio1 {
public static void main(String[] args) {
        int matriz1[][] = {{2,3,4},{3,5,2},{7,2,4}};
        int vector1[] = {3,5,2};
        System.out.println(verificarCoincidencia(matriz1, vector1, 3, 3)); // verdadero

        int matriz2[][] = {{2,3,4},{3,5,2},{7,2,4}};
        int vector2[] = {3,5,1};
        System.out.println(verificarCoincidencia(matriz2, vector2, 3, 3)); // falso
    }

    public static boolean verificarCoincidencia(int[][] matriz, int[] vector, int filas, int columnas) {
        for (int i = 0; i < filas; i++) { //  1+n+1+n
            if (!verificarFila(matriz, vector, i, columnas)) { // n+n(4+7n+3n**2)
                return false; // 1
            }
        }
        return true; // 1
    } // f(n) = 4+3n+4n+7n**2+3n**3
    // f(n) <= c g()
    // 4+3n+4n+7n**2+3n**3 <= 4n**3
    // 4+7n/n**3+ 7n**2/n**3+3n**3/n**3 <= 4n**3/n**3
    // 4+ 7/n**2 + 7/n + 3 <= 4
    // para n0 = 1 no cumple
    // ...
    // ... n0 = 9 cumple, por lo que f(n) pertence a O(n**3)  para c=4 y n0>=9
   
    private static boolean verificarFila(int[][] matriz, int[] vector, int fila, int columnas) {
        for (int j = 0; j < columnas; j++) { // 1+n+1+n
            if (!compararCeldaVariasVeces(matriz[fila][j], vector[j], columnas)) { // n+n(c(n))
                return false; // 1
            }
        }
        return true; // 1
    } // h(n) = 4+3n+n(3n+4) = 4+3n+3n**2+4n = 4+7n+3n**2

    private static boolean compararCeldaVariasVeces(int valorCelda, int valorVector, int repeticiones) {
        for (int k = 0; k < repeticiones; k++) { // 1+n+1+n
            if (valorCelda != valorVector) { // n
                return false; // 1
            }
        }
        return true; // 1
    } // c(n) = 3n+4
    
}