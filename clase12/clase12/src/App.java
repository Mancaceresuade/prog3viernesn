import java.util.ArrayList;

public class App {
    static int cantidadCombinaciones = 0;
    static int optimo = Integer.MAX_VALUE;
    static ArrayList<Integer> combinacionOptima = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> combinacionesOptimas = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        int[] proyectos = {6,7,5,4,3,2,1};  // optimo 9 horas cada uno
        int empleados = 3; // emp 0 = 6+2+1  emp 1 = 5+4  emp 2 = 7+3 => 10
        // cantidad de combinaciones 3**7 = 2187
        ArrayList<Integer> com_actual = new ArrayList<>();
        // {6,7,5,4,3,2,1}
        // {0,0,0,0,0,0,0}  =>  28 horas
        // {0,2,1,1,2,0,0}  =>  cada empleado 9,9,10
        // {2,2,2,2,2,2,2}  =>  28 horas
        calcularOptimo(proyectos,empleados,com_actual,0);
        System.out.println("Cantidad de combinaciones "+ cantidadCombinaciones);
        System.out.println("Optimo " + optimo);
        System.out.println("Combinacion optima "+ combinacionOptima);
        // System.out.println("Combinaciones optimas "+ combinacionesOptimas);
        combinacionesOptimas.forEach(r -> System.out.println(r));
    }

    private static void calcularOptimo(int[] proyectos, int empleados, ArrayList<Integer> com_actual, int i) {
        int optimoCombinacion = calcularOptimoCombinacion(com_actual,proyectos,empleados);
        // caso base
        if(proyectos.length == i) {
            // filtro
            if(optimoCombinacion < optimo) {
                optimo = optimoCombinacion;
                combinacionOptima = new ArrayList<>(com_actual);
                combinacionesOptimas.clear();
                combinacionesOptimas.add(new ArrayList<>(com_actual) );
            } else if (optimoCombinacion == optimo) {
                combinacionesOptimas.add(new ArrayList<>(com_actual));
            }
            //    System.out.println(com_actual);
            cantidadCombinaciones++;
            return;
        }
        // ramificacion
        for (int j = 0; j < empleados; j++) {
            com_actual.add(j);
            // poda
            if(optimoCombinacion > optimo ) {
                com_actual.remove(com_actual.size()-1);
                continue;
            }
            calcularOptimo(proyectos, empleados, com_actual, i+1);
            com_actual.remove(com_actual.size()-1); // TRUCO back
        }
    }

    private static int calcularOptimoCombinacion(ArrayList<Integer> com_actual, int[] proyectos, int empleados) {
        // {6,7,5,4,3,2,1}
        // {0,0,0,0,0,0,0}  =>  28 horas
        // {0,2,1,1,2,0,0}  =>  cada empleado 9,9,10
        // [0,1,2,0,1,2,2]  =>  emp 0 10 horas emp 1 10 emp 2 8
        // [2,0,1,2,0,1,1]  => emp 0 10  emp 1 8  emp 2 10
        // {2,2,2,2,2,2,2}  =>  28 horas
        // Agrupar por empleado
        int[] horasPorEmpleado = new int[empleados];
        for (int i = 0; i < com_actual.size(); i++) {
            horasPorEmpleado[com_actual.get(i)] += proyectos[i];
        }
        // Buscar la suma mayor de horas por empleado
        int maximoHorarioPorEmpleado = 0;
        for (int j = 0; j < horasPorEmpleado.length; j++) {
            if(horasPorEmpleado[j]>maximoHorarioPorEmpleado) {
                maximoHorarioPorEmpleado = horasPorEmpleado[j];
            }
        }
        return maximoHorarioPorEmpleado;
    }
    
}
