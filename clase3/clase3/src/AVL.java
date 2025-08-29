import java.util.List;
import java.util.ArrayList;

public class AVL<T extends Comparable<T>> {
    Nodo<T> raiz;

    public class Nodo<U> {
        U elem;
        Nodo<U> izq;
        Nodo<U> der;
        @Override
        public String toString() {
            return elem.toString();
        }
    }

    public void insertar(T valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T valor) {
        if (nodo == null) {
            Nodo<T> nuevo = new Nodo<>();
            nuevo.elem = valor;
            return nuevo;
        }
        if (valor.compareTo(nodo.elem) < 0) {
            nodo.izq = insertarRec(nodo.izq, valor);
        } else if (valor.compareTo(nodo.elem) > 0) {
            nodo.der = insertarRec(nodo.der, valor);
        } else {
            return nodo; // No duplicados
        }
        return balancear(nodo);
    }

    private Nodo<T> balancear(Nodo<T> nodo) {
        int balance = altura(nodo.izq) - altura(nodo.der);
        if (balance > 1) {
            if (altura(nodo.izq.izq) >= altura(nodo.izq.der)) {
                nodo = rotarDerecha(nodo);
            } else {
                nodo.izq = rotarIzquierda(nodo.izq);
                nodo = rotarDerecha(nodo);
            }
        } else if (balance < -1) {
            if (altura(nodo.der.der) >= altura(nodo.der.izq)) {
                nodo = rotarIzquierda(nodo);
            } else {
                nodo.der = rotarDerecha(nodo.der);
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    private Nodo<T> rotarIzquierda(Nodo<T> x) {
        Nodo<T> y = x.der;
        x.der = y.izq;
        y.izq = x;
        return y;
    }

    private Nodo<T> rotarDerecha(Nodo<T> y) {
        Nodo<T> x = y.izq;
        y.izq = x.der;
        x.der = y;
        return x;
    }

    public void inOrder() {
        inOrder(raiz);
    }

    private void inOrder(Nodo<T> nodo) {
        if(nodo==null) return ;
        inOrder(nodo.izq);
        System.out.println(nodo.toString());
        inOrder(nodo.der);
    }

    // Función para buscar los k mejores jugadores en un rango de puntajes
    public List<T> buscarTopKEnRango(T pMin, T pMax, int k) {
        // Primero encontrar todos los elementos en el rango usando búsqueda binaria
        List<T> elementosEnRango = buscarElementosEnRango(pMin, pMax);
        
        // Luego obtener los k mejores del rango encontrado
        return obtenerTopK(elementosEnRango, k);
    }

    // Método que busca todos los elementos en un rango usando búsqueda binaria optimizada
    private List<T> buscarElementosEnRango(T pMin, T pMax) {
        List<T> resultado = new ArrayList<>();
        buscarElementosEnRangoRec(raiz, pMin, pMax, resultado);
        return resultado;
    }

    // Búsqueda recursiva optimizada que no visita nodos fuera del rango
    private void buscarElementosEnRangoRec(Nodo<T> nodo, T pMin, T pMax, List<T> resultado) {
        if (nodo == null) {
            return;
        }

        // Si el nodo actual está fuera del rango, no explorar más esa rama
        if (nodo.elem.compareTo(pMin) < 0) {
            // Solo explorar el subárbol derecho (valores mayores)
            buscarElementosEnRangoRec(nodo.der, pMin, pMax, resultado);
            return;
        }
        
        if (nodo.elem.compareTo(pMax) > 0) {
            // Solo explorar el subárbol izquierdo (valores menores)
            buscarElementosEnRangoRec(nodo.izq, pMin, pMax, resultado);
            return;
        }

        // El nodo actual está en el rango
        // Explorar primero el subárbol derecho (valores mayores)
        buscarElementosEnRangoRec(nodo.der, pMin, pMax, resultado);
        
        // Agregar el nodo actual al resultado
        resultado.add(nodo.elem);
        
        // Finalmente explorar el subárbol izquierdo (valores menores)
        buscarElementosEnRangoRec(nodo.izq, pMin, pMax, resultado);
    }

    // Método que obtiene los k mejores elementos de una lista ordenada
    private List<T> obtenerTopK(List<T> elementos, int k) {
        List<T> topK = new ArrayList<>();
        int elementosAAgregar = Math.min(k, elementos.size());
        
        // Como los elementos ya están ordenados por puntaje (mayor a menor),
        // simplemente tomamos los primeros k
        for (int i = 0; i < elementosAAgregar; i++) {
            topK.add(elementos.get(i));
        }
        
        return topK;
    }

    // Método alternativo que busca directamente los k mejores en el rango
    // sin almacenar todos los elementos del rango
    public List<T> buscarTopKEnRangoDirecto(T pMin, T pMax, int k) {
        List<T> resultado = new ArrayList<>();
        buscarTopKEnRangoDirectoRec(raiz, pMin, pMax, k, resultado);
        return resultado;
    }

    // Búsqueda directa que se detiene cuando encuentra k elementos
    private void buscarTopKEnRangoDirectoRec(Nodo<T> nodo, T pMin, T pMax, int k, List<T> resultado) {
        if (nodo == null || resultado.size() >= k) {
            return;
        }

        // Si el nodo actual está fuera del rango, no explorar más
        if (nodo.elem.compareTo(pMin) < 0) {
            buscarTopKEnRangoDirectoRec(nodo.der, pMin, pMax, k, resultado);
            return;
        }
        
        if (nodo.elem.compareTo(pMax) > 0) {
            buscarTopKEnRangoDirectoRec(nodo.izq, pMin, pMax, k, resultado);
            return;
        }

        // El nodo actual está en el rango
        // Primero explorar el subárbol derecho (valores mayores) para obtener los mejores
        buscarTopKEnRangoDirectoRec(nodo.der, pMin, pMax, k, resultado);
        
        // Si aún no tenemos k elementos, agregar el nodo actual
        if (resultado.size() < k) {
            resultado.add(nodo.elem);
        }
        
        // Finalmente explorar el subárbol izquierdo (valores menores)
        buscarTopKEnRangoDirectoRec(nodo.izq, pMin, pMax, k, resultado);
    }

    // Función auxiliar para obtener el tamaño del árbol
    public int tamanio() {
        return tamanioRec(raiz);
    }

    private int tamanioRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + tamanioRec(nodo.izq) + tamanioRec(nodo.der);
    }

    // Función para verificar si el árbol está balanceado
    public boolean estaBalanceado() {
        return estaBalanceadoRec(raiz) != -1;
    }

    private int estaBalanceadoRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        
        int alturaIzq = estaBalanceadoRec(nodo.izq);
        if (alturaIzq == -1) return -1;
        
        int alturaDer = estaBalanceadoRec(nodo.der);
        if (alturaDer == -1) return -1;
        
        if (Math.abs(alturaIzq - alturaDer) > 1) return -1;
        
        return Math.max(alturaIzq, alturaDer) + 1;
    }
}