import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static List<String> generarPartidos(List<String> equipos) {
        List<String> partidos = new ArrayList<>();
        int n = equipos.size();
        for (int i = 0; i < n - 1; i++) {  // 1+ 3 (n+1) + n
            for (int j = i+1; j < n; j++) { // gauus n(n-1)/2
                partidos.add(equipos.get(i) + "-" + equipos.get(j));
            }
        }
        return partidos;
    }    
    /*
    public static boolean estaOrdenada(char[] a) {
        if (a == null || a.length <= 1) return true;
        return solve(a, 0, a.length - 1).ok;
    }
 
    private static Res solve(char[] a, int l, int r) {
        if (l == r) return new Res(true, a[l], a[l]);
        int m = (l + r) >>> 1;
        Res L = solve(a, l, m);
        Res R = solve(a, m + 1, r);
        boolean ok = L.ok && R.ok && (L.max <= R.min);
        char mn = L.min < R.min ? L.min : R.min;
        char mx = L.max > R.max ? L.max : R.max;
        return new Res(ok, mn, mx);
    } // a= 2  b=2   k= 0
        // metodo division 
        // caso 3
        // O (n ^ log b de a)  => O(n ^ log 2 de 2)  => O(n)
    */





    public static boolean estaOrdenadoR(char[] array){
        return estaOrdenadoRR(array, array.length, 0);
    }
    private static boolean estaOrdenadoRR(char[] array, int longitud, int posicion){
        if(longitud-2  == posicion)
            return array[longitud-1] >= array[posicion];
        else
        {
            return array[posicion] <= array[posicion+1] && estaOrdenadoRR(array, longitud, posicion +1);
        }
    } // metodo sustraccion
    // a = 1 b = 1 k = 0
    // segundo caso   O(n^k+1) => O(n^1)




    public static boolean estaOrdenadoAlfRec(char[] secuencia){
        if(secuencia.length <= 1){
            return true;
        }
        if(secuencia[0] > secuencia[1]){
            return false;
        }

        return estaOrdenadoAlfRec(Arrays.copyOfRange(secuencia, 1, secuencia.length));
    } // metodo sustracion
    // a= 1  b= 1  k=1  (por usar copyOfRange)
    // caso 2
    // O(n ^ (k+1) )  =>  O(n^2)





    public static boolean estaOrdenado(char[] caracteres) {
        if (caracteres == null || caracteres.length < 2) {
            return true;
        }
        for (int i = 0; i < caracteres.length - 1; i++) { // 1 + 3(n+1) + n

            if (caracteres[i] > caracteres[i + 1]) {  // 4n
                return false; // 1
            }
        }
        return true; // 1
    }
} // f(n) = 1 + 3(n+1) + n + 4n + 1 + 1
// f(n) = 1 + 3n + 3 + n + 4n +1 = 5 + 8n
// f(n) <= c g(n)  // complejidad asintotica 
// 5 + 8n <= 9n    // termino dominat mas 1
// 5/n + 8n/n <= 9n/n // 
// 5/n + 8 <= 9
// f(n) pertenece a O(n) para c=9 y a partir de n0 > 9
