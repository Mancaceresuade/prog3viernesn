public class App {
    public static void main(String[] args) throws Exception {
        // requisito, matriz cuadrada 
        // primer ejercicio iterativo
        // segundo ejercicio recursivo
        int[][] matriz = {{5,1,4},{3,10,8},{4,6,15}}; //
        System.out.println(todasLasColumnasAlMenosUnDivisiblePor(matriz,5)); // true
        int[][] matriz2 = {{5,1,4},{3,10,8},{4,6,16}};
        System.out.println(todasLasColumnasAlMenosUnDivisiblePor(matriz2,5)); //  false
    }



    private static boolean todasLasColumnasAlMenosUnDivisiblePor(int[][] matriz, int elemento) {
        boolean rta = true; // 1
        for (int col = 0; col < matriz[0].length; col++) { // 1 + 3(n+1) + n = 1+3n+3+n = 4+4n
            rta = rta && algunElementoDivisibleEnColumna(matriz,col,elemento);
            // n * ( 1 + 1 + h(n))
            // n * ( 1 + 1 + 5 + 8n )  // distributiva
            // n+n+5n+8n^2 = 7n+8n^2
        }
        return rta; // 1
    } /// f(n) = 1+ 4 + 4n + 7n+8n^2 + 1 
    // f(n) = 6 + 11n + 8n^2   // conteo de instrucciones
    // 6 + 11n + 8n^2 <= c g(n) // complejidad asintotica + 1
    // 6 + 11n + 8n^2 <= 9n^2 // divido por n^2
    // 6/n^2 + 11n/n^2 + 8n^2/n^2 <= 9n^2/n^2  // simplifico
    // 6/n^2 + 11/n + 8 <= 9
    // n0 = 1 cumple ? 25<= 9 no cumple
    // n0 = 2 ...
    // n0 = 12 cumple , encontes f(n)  pertenece O(n^2) para c=9 y n0=12
    //

    private static boolean algunElementoDivisibleEnColumna(int[][] matriz,int col, int elemento) {
        boolean rta = false; // 1
        for (int fila = 0; fila < matriz.length; fila++) { //   1 + 2 (n+1) + n = 1+2n+2+n
            rta = rta || matriz[fila][col]%elemento==0; // 5n
        }
        return rta; // 1
    } // h(n) = 5 + 8n



}
