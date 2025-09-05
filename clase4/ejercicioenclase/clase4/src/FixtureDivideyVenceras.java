import java.util.*;
 
public class FixtureDivideYVenceras {
 
    public static List<List<String>> fixture(List<String> teams) {
        int n = teams.size();
        if (Integer.bitCount(n) != 1) {
            throw new IllegalArgumentException("n debe ser potencia de 2");
        }
        return build(new ArrayList<>(teams));
    }
 
    // Construye recursivamente el fixture para la lista de equipos dada
    private static List<List<String>> build(List<String> T) {
        int n = T.size();
        if (n == 1) {
            // Con 1 equipo no hay partidos ni fechas
            return new ArrayList<>();
        }
        if (n == 2) {
            // Caso base: 1 fecha con el único partido
            List<List<String>> res = new ArrayList<>();
            res.add(List.of(T.get(0) + "-" + T.get(1)));
            return res;
        }
 
        int m = n / 2;
        List<String> A = T.subList(0, m);
        List<String> B = T.subList(m, n);
 
        // Recursión en cada mitad (cada una devuelve m-1 fechas)
        List<List<String>> FA = build(new ArrayList<>(A));
        List<List<String>> FB = build(new ArrayList<>(B));
 
        List<List<String>> F = new ArrayList<>();
 
        // 1) Fechas internas combinadas: en la misma fecha se juegan A y B
        for (int r = 0; r < m - 1; r++) {
            List<String> fecha = new ArrayList<>(FA.get(r).size() + FB.get(r).size());
            fecha.addAll(FA.get(r));
            fecha.addAll(FB.get(r));
            F.add(fecha);
        }
 
        // 2) Fechas cruzadas A vs B: m fechas con emparejamientos por desplazamiento
        for (int shift = 0; shift < m; shift++) {
            List<String> fecha = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                String local = A.get(i);
                String visita = B.get((i + shift) % m);
                fecha.add(local + "-" + visita);
            }
            F.add(fecha);
        } // O(n)  
 
        // F tiene (m-1) + m = 2m-1 = n-1 fechas
        return F;
    }
 
    // Demo
    public static void main(String[] args) {
        int n = 4;
        List<String> teams = new ArrayList<>();
        for (int i = 0; i < n; i++) teams.add(String.valueOf((char)('a' + i)));
        List<List<String>> fixture = fixture(teams);
 
        for (int k = 0; k < fixture.size(); k++) {
            System.out.println("Fecha " + (k + 1));
            System.out.println(String.join(" ", fixture.get(k)));
        }
    }
}