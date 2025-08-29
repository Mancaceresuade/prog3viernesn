import java.util.Objects;

public class Jugador implements Comparable<Jugador> {
    private String nombre;
    private int puntaje;
    private String equipo;

    public Jugador(String nombre, int puntaje, String equipo) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getEquipo() {
        return equipo;
    }

    @Override
    public int compareTo(Jugador otro) {
        // Ordenar por puntaje de mayor a menor (mejores puntajes primero)
        if (this.puntaje != otro.puntaje) {
            return Integer.compare(otro.puntaje, this.puntaje);
        }
        // Si tienen el mismo puntaje, ordenar alfabéticamente por nombre
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public String toString() {
        return nombre + " (" + equipo + ") - " + puntaje + " pts";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador jugador = (Jugador) obj;
        return puntaje == jugador.puntaje && 
               nombre.equals(jugador.nombre) && 
               equipo.equals(jugador.equipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, puntaje, equipo);
    }
}

