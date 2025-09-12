public class Ejercicio3 {

     public static int sumaCompleja(int[] arr, int inicio, int fin) {
        if (inicio == fin) return arr[inicio];  
        int medio = (inicio + fin) / 2;
        int sumaIzquierda = sumaCompleja(arr, inicio, medio);    
        int sumaDerecha   = sumaCompleja(arr, medio + 1, fin);  
        int sumaExtra = 0;
        for (int i = inicio; i <= fin; i++) {  
            sumaExtra += arr[i] * 2;            
        }
        return sumaIzquierda + sumaDerecha + sumaExtra;
    } // metodo division, a=2, b=2 , k=1 
    // caso 2, a=b  => O(n log n)

}