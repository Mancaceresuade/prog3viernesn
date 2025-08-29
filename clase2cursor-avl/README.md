# Árbol AVL (Adelson-Velskii y Landis)

## Descripción
Este proyecto implementa un árbol binario de búsqueda balanceado (AVL) en Java. Un árbol AVL es un árbol binario de búsqueda que mantiene automáticamente su balance, garantizando que la diferencia de altura entre los subárboles izquierdo y derecho de cualquier nodo sea como máximo 1.

## Características Principales

### 1. **Balance Automático**
- El árbol se rebalancea automáticamente después de cada inserción y eliminación
- Mantiene la propiedad AVL: `|altura(izquierda) - altura(derecha)| ≤ 1`

### 2. **Operaciones Principales**
- **Inserción**: O(log n) - mantiene el balance automáticamente
- **Eliminación**: O(log n) - mantiene el balance automáticamente
- **Búsqueda**: O(log n) - búsqueda eficiente en árbol balanceado

### 3. **Rotaciones Implementadas**
- **Rotación Simple Derecha**: Para casos LL (Left-Left)
- **Rotación Simple Izquierda**: Para casos RR (Right-Right)
- **Rotación Doble Izquierda-Derecha**: Para casos LR (Left-Right)
- **Rotación Doble Derecha-Izquierda**: Para casos RL (Right-Left)

## Estructura de la Clase

### Clase `Avl<T>`
```java
public class Avl<T extends Comparable<T>>
```
- **Tipo genérico**: Debe implementar `Comparable<T>`
- **Raíz**: Puntero al nodo raíz del árbol

### Clase Interna `Nodo<U>`
```java
class Nodo<U> {
    U elem;           // Elemento almacenado
    Nodo<U> izq;      // Hijo izquierdo
    Nodo<U> der;      // Hijo derecho
    int altura;       // Altura del nodo
}
```

## Métodos Principales

### Operaciones Básicas
- `insertar(T elem)`: Inserta un elemento manteniendo el balance
- `eliminar(T elem)`: Elimina un elemento manteniendo el balance
- `buscar(T elem)`: Busca un elemento en el árbol
- `estaVacio()`: Verifica si el árbol está vacío

### Recorridos
- `inorden()`: Recorrido inorden (ordenado)
- `preorden()`: Recorrido preorden
- `postorden()`: Recorrido postorden

### Información del Árbol
- `alturaArbol()`: Retorna la altura total del árbol
- `imprimirArbol()`: Muestra una representación visual del árbol

## Casos de Balance

### 1. **Caso LL (Left-Left)**
```
    y (desbalanceado)
   /
  x
 /
z
```
**Solución**: Rotación simple derecha en y

### 2. **Caso RR (Right-Right)**
```
x (desbalanceado)
 \
  y
   \
    z
```
**Solución**: Rotación simple izquierda en x

### 3. **Caso LR (Left-Right)**
```
    y (desbalanceado)
   /
  x
   \
    z
```
**Solución**: Rotación izquierda en x, luego rotación derecha en y

### 4. **Caso RL (Right-Left)**
```
x (desbalanceado)
 \
  y
 /
z
```
**Solución**: Rotación derecha en y, luego rotación izquierda en x

## Complejidad Temporal

| Operación | Complejidad |
|-----------|-------------|
| Inserción | O(log n)    |
| Eliminación | O(log n)   |
| Búsqueda  | O(log n)    |
| Recorrido | O(n)        |

## Ejemplo de Uso

```java
// Crear un árbol AVL
Avl<Integer> arbol = new Avl<>();

// Insertar elementos
arbol.insertar(10);
arbol.insertar(20);
arbol.insertar(30);

// Buscar elementos
boolean existe = arbol.buscar(20); // true

// Recorrer el árbol
arbol.inorden(); // Muestra: 10 20 30

// Eliminar elementos
arbol.eliminar(20);

// Ver el árbol
arbol.imprimirArbol();
```

## Compilación y Ejecución

### Compilar
```bash
javac *.java
```

### Ejecutar
```bash
java Main
```

## Ventajas del Árbol AVL

1. **Balance Automático**: No requiere intervención manual para mantener el balance
2. **Búsqueda Eficiente**: Garantiza O(log n) para búsquedas
3. **Inserción/Eliminación Eficiente**: O(log n) con rebalanceo automático
4. **Estructura Predictible**: La altura máxima está acotada por O(log n)

## Desventajas

1. **Complejidad de Implementación**: Más complejo que un BST simple
2. **Overhead de Balance**: Cada operación puede requerir múltiples rotaciones
3. **Memoria Adicional**: Cada nodo almacena información de altura

## Aplicaciones

- Bases de datos para mantener índices balanceados
- Implementación de mapas y conjuntos ordenados
- Algoritmos que requieren búsquedas eficientes en datos ordenados
- Estructuras de datos para aplicaciones que requieren balance automático

