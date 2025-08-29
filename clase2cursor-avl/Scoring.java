public class Scoring implements Comparable<Scoring> {
    private String nombre;
    private int puntuacion;
    
    // Constructor
    public Scoring(String nombre, int puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    // Implementación de Comparable
    // Ordenamos por puntuación (mayor a menor) y en caso de empate por nombre alfabéticamente
    @Override
    public int compareTo(Scoring otro) {
        // Primero comparamos por puntuación (mayor puntuación va primero)
        if (this.puntuacion != otro.puntuacion) {
            return Integer.compare(otro.puntuacion, this.puntuacion); // Orden descendente
        }
        // Si las puntuaciones son iguales, ordenamos alfabéticamente por nombre
        return this.nombre.compareTo(otro.nombre);
    }
    
    // Método toString para mostrar la información
    @Override
    public String toString() {
        return nombre + " (" + puntuacion + " puntos)";
    }
    
    // Método equals para comparar objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Scoring scoring = (Scoring) obj;
        return puntuacion == scoring.puntuacion && 
               (nombre == null ? scoring.nombre == null : nombre.equals(scoring.nombre));
    }
    
    // Método hashCode
    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + puntuacion;
        return result;
    }
}
