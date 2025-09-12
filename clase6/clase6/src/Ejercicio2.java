public class Ejercicio2 {

    public static int calcular(int[] arr, int i, int f) {
        if (i == f) return arr[i];
        int n = (i + f) / 2;
        int da = calcular(arr, i, n);
        int ha = calcular(arr, n + 1, f);
        return da + ha;
    } // metodo division,  a=2 b=2 K= 0 caso tercer caso 
    // O(n ** (log 2 de 2))  =>   O(n)
    
}
