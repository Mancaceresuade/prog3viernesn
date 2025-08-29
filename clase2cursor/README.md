# Sistema de Facturación en Java

Este proyecto implementa un sistema de facturación que procesa listas de facturas electrónicas y clientes para generar un resumen de importes por cliente.

## Estructura del Proyecto

- **Cliente.java**: Clase que representa un cliente con ID y nombre
- **Factura.java**: Clase que representa una factura con ID de factura, ID de cliente e importe
- **ResumenCliente.java**: Clase que contiene el resumen de importes de un cliente
- **SistemaFacturacion.java**: Clase principal que implementa la lógica de procesamiento (con implementación optimizada usando Maps)
- **Main.java**: Clase principal con método main para probar el sistema

## Funcionalidades

El sistema recibe:
- Una lista de comprobantes (facturas electrónicas) con:
  - ID de factura
  - ID de cliente
  - Importe
- Una lista de clientes con:
  - ID de cliente
  - Nombre de cliente

Y genera:
- Una lista con ID de cliente, nombre de cliente y suma de importes de las facturas

## Implementación

### Versión Original (Sin Maps)
- **Complejidad temporal**: O(n*m) donde n es el número de clientes y m es el número de facturas
- **Uso de memoria**: Eficiente, solo almacena los datos necesarios
- **Desventaja**: Complejidad cuadrática que se vuelve ineficiente con grandes volúmenes de datos

### Versión Optimizada (Con Maps) ⭐
- **Complejidad temporal**: O(n + m) - **MEJORA SIGNIFICATIVA**
- **Uso de memoria**: Ligeramente mayor debido al HashMap, pero compensado por la eficiencia temporal
- **Ventajas**: 
  - Una sola pasada por las facturas: O(m)
  - Una sola pasada por los clientes: O(n)
  - Total: O(n + m) en lugar de O(n*m)

## Análisis de Complejidad

### Comparación de Eficiencia

| Escenario | Sin Maps | Con Maps | Mejora |
|-----------|----------|----------|---------|
| 100 clientes, 1000 facturas | O(100,000) | O(1,100) | ~91x más eficiente |
| 1000 clientes, 10000 facturas | O(10,000,000) | O(11,000) | ~909x más eficiente |
| 10000 clientes, 100000 facturas | O(1,000,000,000) | O(110,000) | ~9,091x más eficiente |

### ¿Por qué la mejora es tan significativa?

1. **Sin Maps**: Para cada cliente, se recorre toda la lista de facturas
   - Operaciones = n × m
   - Ejemplo: 1000 clientes × 10000 facturas = 10,000,000 operaciones

2. **Con Maps**: Solo se recorre cada lista una vez
   - Operaciones = n + m
   - Ejemplo: 1000 + 10000 = 11,000 operaciones

## Compilación y Ejecución

### Compilar todas las clases:
```bash
javac *.java
```

### Ejecutar el programa:
```bash
java Main
```

## Ejemplo de Salida

El programa mostrará:
1. Lista de clientes
2. Lista de facturas
3. Resumen de importes por cliente
4. Estadísticas del sistema (total general, cliente con mayor/menor facturación, promedio)

## Características Técnicas

- **Complejidad temporal optimizada**: O(n + m) en lugar de O(n*m)
- **Uso de memoria**: Eficiente con HashMap para acceso rápido
- **Extensibilidad**: Fácil de modificar para agregar nuevas funcionalidades
- **Documentación**: Código completamente documentado con JavaDoc y análisis de complejidad

## Notas de Diseño

- Se utilizan getters y setters para encapsulación
- Se implementa el método `toString()` para facilitar la visualización
- El código está estructurado de manera modular y reutilizable
- Se incluyen validaciones básicas y manejo de casos edge
- **NUEVO**: Implementación optimizada con Maps para mejor rendimiento
- **NUEVO**: Código original comentado para referencia y comparación

## Casos de Uso

### Cuándo usar la versión con Maps:
- **Grandes volúmenes de datos** (más de 1000 registros)
- **Aplicaciones en tiempo real** donde la velocidad es crítica
- **Sistemas empresariales** con múltiples usuarios concurrentes

### Cuándo usar la versión sin Maps:
- **Pequeños volúmenes de datos** (menos de 100 registros)
- **Aplicaciones educativas** para entender algoritmos básicos
- **Sistemas con restricciones de memoria** muy estrictas
