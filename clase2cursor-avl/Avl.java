public class Avl<T extends Comparable<T>> {
    
    Nodo<T> raiz;
    
    // Constructor
    public Avl() {
        this.raiz = null;
    }
    
    // Clase nodo interna
    class Nodo<U> {
        U elem;
        Nodo<U> izq;
        Nodo<U> der;
        int altura; // Altura del nodo para calcular factor de balance
        
        Nodo(U elem) {
            this.elem = elem;
            this.izq = null;
            this.der = null;
            this.altura = 1; // Nodo hoja tiene altura 1
        }
    }
    
    // Método para obtener la altura de un nodo
    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }
    
    // Método para obtener el factor de balance de un nodo
    private int factorBalance(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izq) - altura(nodo.der);
    }
    
    // Rotación simple a la derecha
    private Nodo<T> rotacionDerecha(Nodo<T> y) {
        Nodo<T> x = y.izq;
        Nodo<T> T2 = x.der;
        
        // Realizar rotación
        x.der = y;
        y.izq = T2;
        
        // Actualizar alturas
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        
        return x;
    }
    
    // Rotación simple a la izquierda
    private Nodo<T> rotacionIzquierda(Nodo<T> x) {
        Nodo<T> y = x.der;
        Nodo<T> T2 = y.izq;
        
        // Realizar rotación
        y.izq = x;
        x.der = T2;
        
        // Actualizar alturas
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        
        return y;
    }
    
    // Método para insertar un elemento
    public void insertar(T elem) {
        raiz = insertarRecursivo(raiz, elem);
    }
    
    private Nodo<T> insertarRecursivo(Nodo<T> nodo, T elem) {
        // 1. Inserción normal de BST
        if (nodo == null) {
            return new Nodo<>(elem);
        }
        
        if (elem.compareTo(nodo.elem) < 0) {
            nodo.izq = insertarRecursivo(nodo.izq, elem);
        } else if (elem.compareTo(nodo.elem) > 0) {
            nodo.der = insertarRecursivo(nodo.der, elem);
        } else {
            // Elemento duplicado, no se inserta
            return nodo;
        }
        
        // 2. Actualizar altura del nodo actual
        nodo.altura = Math.max(altura(nodo.izq), altura(nodo.der)) + 1;
        
        // 3. Obtener factor de balance para verificar si está desbalanceado
        int balance = factorBalance(nodo);
        
        // 4. Si está desbalanceado, hay 4 casos:
        
        // Caso Izquierda-Izquierda
        if (balance > 1 && elem.compareTo(nodo.izq.elem) < 0) {
            return rotacionDerecha(nodo);
        }
        
        // Caso Derecha-Derecha
        if (balance < -1 && elem.compareTo(nodo.der.elem) > 0) {
            return rotacionIzquierda(nodo);
        }
        
        // Caso Izquierda-Derecha
        if (balance > 1 && elem.compareTo(nodo.izq.elem) > 0) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
        
        // Caso Derecha-Izquierda
        if (balance < -1 && elem.compareTo(nodo.der.elem) < 0) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        
        return nodo;
    }
    
    // Método para eliminar un elemento
    public void eliminar(T elem) {
        raiz = eliminarRecursivo(raiz, elem);
    }
    
    private Nodo<T> eliminarRecursivo(Nodo<T> raiz, T elem) {
        // 1. Eliminación normal de BST
        if (raiz == null) {
            return raiz;
        }
        
        if (elem.compareTo(raiz.elem) < 0) {
            raiz.izq = eliminarRecursivo(raiz.izq, elem);
        } else if (elem.compareTo(raiz.elem) > 0) {
            raiz.der = eliminarRecursivo(raiz.der, elem);
        } else {
            // Nodo a eliminar encontrado
            
            // Nodo con un solo hijo o sin hijos
            if (raiz.izq == null || raiz.der == null) {
                Nodo<T> temp = null;
                if (temp == raiz.izq) {
                    temp = raiz.der;
                } else {
                    temp = raiz.izq;
                }
                
                // Sin hijos
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else {
                    // Un hijo
                    raiz = temp;
                }
            } else {
                // Nodo con dos hijos: obtener el sucesor inorden (menor en subárbol derecho)
                Nodo<T> temp = nodoMinimo(raiz.der);
                
                // Copiar el contenido del sucesor
                raiz.elem = temp.elem;
                
                // Eliminar el sucesor
                raiz.der = eliminarRecursivo(raiz.der, temp.elem);
            }
        }
        
        // Si el árbol tenía solo un nodo
        if (raiz == null) {
            return raiz;
        }
        
        // 2. Actualizar altura del nodo actual
        raiz.altura = Math.max(altura(raiz.izq), altura(raiz.der)) + 1;
        
        // 3. Obtener factor de balance para verificar si está desbalanceado
        int balance = factorBalance(raiz);
        
        // 4. Si está desbalanceado, hay 4 casos:
        
        // Caso Izquierda-Izquierda
        if (balance > 1 && factorBalance(raiz.izq) >= 0) {
            return rotacionDerecha(raiz);
        }
        
        // Caso Izquierda-Derecha
        if (balance > 1 && factorBalance(raiz.izq) < 0) {
            raiz.izq = rotacionIzquierda(raiz.izq);
            return rotacionDerecha(raiz);
        }
        
        // Caso Derecha-Derecha
        if (balance < -1 && factorBalance(raiz.der) <= 0) {
            return rotacionIzquierda(raiz);
        }
        
        // Caso Derecha-Izquierda
        if (balance < -1 && factorBalance(raiz.der) > 0) {
            raiz.der = rotacionDerecha(raiz.der);
            return rotacionIzquierda(raiz);
        }
        
        return raiz;
    }
    
    // Método auxiliar para encontrar el nodo mínimo
    private Nodo<T> nodoMinimo(Nodo<T> nodo) {
        Nodo<T> actual = nodo;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }
    
    // Método para buscar un elemento
    public boolean buscar(T elem) {
        return buscarRecursivo(raiz, elem);
    }
    
    private boolean buscarRecursivo(Nodo<T> nodo, T elem) {
        if (nodo == null) {
            return false;
        }
        
        if (elem.compareTo(nodo.elem) == 0) {
            return true;
        }
        
        if (elem.compareTo(nodo.elem) < 0) {
            return buscarRecursivo(nodo.izq, elem);
        } else {
            return buscarRecursivo(nodo.der, elem);
        }
    }
    
    // Método para recorrido inorden (ordenado)
    public void inorden() {
        System.out.print("Recorrido Inorden: ");
        inordenRecursivo(raiz);
        System.out.println();
    }
    
    private void inordenRecursivo(Nodo<T> nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izq);
            System.out.print(nodo.elem + " ");
            inordenRecursivo(nodo.der);
        }
    }
    
    // Método para recorrido preorden
    public void preorden() {
        System.out.print("Recorrido Preorden: ");
        preordenRecursivo(raiz);
        System.out.println();
    }
    
    private void preordenRecursivo(Nodo<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.elem + " ");
            preordenRecursivo(nodo.izq);
            preordenRecursivo(nodo.der);
        }
    }
    
    // Método para recorrido postorden
    public void postorden() {
        System.out.print("Recorrido Postorden: ");
        postordenRecursivo(raiz);
        System.out.println();
    }
    
    private void postordenRecursivo(Nodo<T> nodo) {
        if (nodo != null) {
            postordenRecursivo(nodo.izq);
            postordenRecursivo(nodo.der);
            System.out.print(nodo.elem + " ");
        }
    }
    
    // Método para obtener la altura del árbol
    public int alturaArbol() {
        return altura(raiz);
    }
    
    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }
    
    // Método para imprimir el árbol de forma visual
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        imprimirArbolRecursivo(raiz, "", true);
    }
    
    private void imprimirArbolRecursivo(Nodo<T> nodo, String prefijo, boolean esIzquierdo) {
        if (nodo != null) {
            System.out.println(prefijo + (esIzquierdo ? "├── " : "└── ") + nodo.elem + " (h=" + nodo.altura + ")");
            imprimirArbolRecursivo(nodo.izq, prefijo + (esIzquierdo ? "│   " : "    "), true);
            imprimirArbolRecursivo(nodo.der, prefijo + (esIzquierdo ? "│   " : "    "), false);
        }
    }
}
