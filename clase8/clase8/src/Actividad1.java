import java.util.ArrayList;

public class Actividad1 {
        public static void main(String[] args) throws Exception {
        int[] numeros = {1,2,3};
        int objetivo = 3;
        combinaciones(numeros,objetivo); // {1,2,3} {1,2} {1,3} .....
    }

    private static void combinaciones(int[] numeros, int objetivo) {
        // guardar resultado [1,2,3] [1,2] [1,3] [1] [2,3] [2] [3] []
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        // combinacion actual
        ArrayList<Integer> combinacion_actual = new ArrayList<>(); 
        combinacionesRecursivo(numeros,combinacion_actual,resultado,0,objetivo,0);
        resultado.forEach(r -> System.out.println(r));
    } // 

    private static void combinacionesRecursivo(int[] numeros, ArrayList<Integer> combinacion_actual,
            ArrayList<ArrayList<Integer>> resultado, int i, int objetivo, int suma) {
        if(i==numeros.length)  {
            if(suma == objetivo)
                resultado.add(new ArrayList<>(combinacion_actual));
            return;
        }      
        combinacion_actual.add(numeros[i]);
        combinacionesRecursivo(numeros, combinacion_actual, resultado, i+1, objetivo, suma+numeros[i]);
        combinacion_actual.remove(combinacion_actual.size()-1);
        combinacionesRecursivo(numeros, combinacion_actual, resultado, i+1, objetivo, suma);
    }   // a=2 b=1 k=0    => sustraccion, caso 3, O(2**n)

}
