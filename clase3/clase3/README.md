# Sistema de Ranking GameScore con Árbol AVL

## Descripción del Problema

GameScore necesita un sistema de clasificación que permita búsquedas complejas de manera óptima. Específicamente, se requiere una función que, dado un rango de puntajes [p_min, p_max] y un número k, devuelva los k mejores jugadores que se encuentren en ese rango de puntajes.

## Implementación

### Estructura de Datos

- **Clase AVL<T>**: Árbol AVL genérico que mantiene el balance automáticamente
- **Clase Jugador**: Implementa Comparable para ordenar por puntaje (mayor a menor)
- **Función buscarTopKEnRango()**: Función principal que implementa la búsqueda modular
- **Función buscarTopKEnRangoDirecto()**: Implementación alternativa más eficiente en memoria

### Características de la Implementación

1. **Arquitectura Modular**: Separación clara entre búsqueda del rango y obtención de los k mejores
2. **Búsqueda Binaria Optimizada**: No visita nodos fuera del rango [p_min, p_max]
3. **Recursiva**: Implementación completamente recursiva como se solicitó
4. **Ordenamiento por Puntaje**: Los jugadores se ordenan de mayor a menor puntaje
5. **Control Total**: Implementación personalizada sin dependencias externas

### Implementación Modular

La función `buscarTopKEnRango()` se divide en dos pasos:

1. **`buscarElementosEnRango()`**: Busca todos los elementos en el rango usando búsqueda binaria optimizada
2. **`obtenerTopK()`**: Extrae los k mejores elementos de la lista ordenada

### Implementación Directa

La función `buscarTopKEnRangoDirecto()` combina ambos pasos en una sola función recursiva, deteniéndose cuando encuentra k elementos.

## Análisis de Complejidad

### ¿Por qué un AVL es la estructura adecuada?

1. **Balance Automático**: El árbol se mantiene balanceado automáticamente, garantizando una altura máxima de O(log n)
2. **Ordenamiento**: Los elementos están ordenados, permitiendo búsquedas eficientes por rango
3. **Flexibilidad**: Permite modificaciones y control total sobre la lógica de recorrido
4. **Eficiencia**: Operaciones de inserción, búsqueda y recorrido en O(log n)

### Complejidad de la Búsqueda

#### Implementación Modular:
- **Búsqueda del rango**: O(log n + r) donde r es el número de elementos en el rango
- **Obtención de top k**: O(k) donde k es el número de elementos solicitados
- **Complejidad total**: O(log n + r + k)

#### Implementación Directa:
- **Mejor caso**: O(log n + k) - cuando el rango es pequeño
- **Caso promedio**: O(log n + k) - eficiente para rangos pequeños
- **Peor caso**: O(n) - cuando todos los elementos están en el rango

### Ventajas de la Implementación Modular:

1. **Separación de responsabilidades**: Cada función tiene un propósito específico
2. **Reutilización**: La función de búsqueda de rango puede usarse independientemente
3. **Mantenibilidad**: Código más fácil de entender y modificar
4. **Flexibilidad**: Permite diferentes estrategias para obtener los k mejores

### Ventajas de la Implementación Directa:

1. **Eficiencia en memoria**: No almacena todos los elementos del rango
2. **Mejor rendimiento**: Se detiene cuando encuentra k elementos
3. **Menos overhead**: Una sola función recursiva

## Uso del Sistema

```java
// Crear el ranking
AVL<Jugador> ranking = new AVL<>();

// Insertar jugadores
ranking.insertar(new Jugador("Ana", 1800, "Equipo A"));
ranking.insertar(new Jugador("Carlos", 1500, "Equipo B"));

// Implementación modular
List<Jugador> resultado1 = ranking.buscarTopKEnRango(
    new Jugador("", 1800, ""), // pMax
    new Jugador("", 1400, ""), // pMin
    3
);

// Implementación directa
List<Jugador> resultado2 = ranking.buscarTopKEnRangoDirecto(
    new Jugador("", 1800, ""), // pMax
    new Jugador("", 1400, ""), // pMin
    3
);
```

## Compilación y Ejecución

```bash
# Compilar
javac -d bin src/*.java

# Ejecutar la demostración
java -cp bin GameScoreDemo
```

## Estructura del Proyecto

```
clase3/
├── src/
│   ├── AVL.java          # Implementación del árbol AVL con ambas funciones
│   ├── Jugador.java      # Clase Jugador con Comparable
│   └── GameScoreDemo.java # Demostración de ambas implementaciones
├── bin/                  # Archivos compilados
└── README.md            # Este archivo
```

## Funcionalidades Adicionales

- **Verificación de balance**: `ranking.estaBalanceado()`
- **Tamaño del árbol**: `ranking.tamanio()`
- **Recorrido in-order**: `ranking.inOrder()`
- **Inserción automática**: `ranking.insertar(jugador)`
- **Búsqueda de rango**: `buscarElementosEnRango(pMin, pMax)`
- **Obtención de top k**: `obtenerTopK(elementos, k)`

## Consideraciones de Diseño

1. **Ordenamiento inverso**: Los jugadores se ordenan de mayor a menor puntaje para facilitar la obtención de los "mejores"
2. **Poda inteligente**: La función evita explorar subárboles que no pueden contener elementos del rango
3. **Recursión optimizada**: Se detiene la recursión cuando se alcanzan k elementos (en la implementación directa)
4. **Manejo de rangos**: La función funciona correctamente con rangos vacíos o que contienen todos los elementos
5. **Modularidad**: Separación clara entre búsqueda y selección para mejor mantenibilidad
