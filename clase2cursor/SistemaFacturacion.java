import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaFacturacion {
    
    /**
     * IMPLEMENTACIÓN ORIGINAL (SIN MAPS):
     * Complejidad temporal: O(n*m) donde:
     * - n = número de clientes
     * - m = número de facturas
     * 
     * Para cada cliente, se recorre toda la lista de facturas,
     * resultando en una complejidad cuadrática.
     */
    
    /*
    public List<ResumenCliente> generarResumenImportes(List<Factura> facturas, List<Cliente> clientes) {
        List<ResumenCliente> resumenes = new ArrayList<>();
        
        // Para cada cliente, buscar sus facturas y sumar importes
        for (Cliente cliente : clientes) {
            double sumaImportes = 0.0;
            
            // Buscar todas las facturas de este cliente
            for (Factura factura : facturas) {
                if (factura.getIdCliente() == cliente.getIdCliente()) {
                    sumaImportes += factura.getImporte();
                }
            }
            
            // Crear el resumen del cliente
            ResumenCliente resumen = new ResumenCliente(
                cliente.getIdCliente(),
                cliente.getNombreCliente(),
                sumaImportes
            );
            
            resumenes.add(resumen);
        }
        
        return resumenes;
    }
    */
    
    /**
     * NUEVA IMPLEMENTACIÓN CON MAPS:
     * Complejidad temporal: O(n + m) donde:
     * - n = número de clientes
     * - m = número de facturas
     * 
     * Ventajas:
     * - Una sola pasada por las facturas: O(m)
     * - Una sola pasada por los clientes: O(n)
     * - Total: O(n + m) en lugar de O(n*m)
     * 
     * Para grandes volúmenes de datos, la mejora es significativa:
     * - Con 1000 clientes y 10000 facturas:
     *   * Sin Maps: O(1000 * 10000) = O(10,000,000)
     *   * Con Maps: O(1000 + 10000) = O(11,000)
     *   * Mejora: ~909x más eficiente
     */
    public List<ResumenCliente> generarResumenImportes(List<Factura> facturas, List<Cliente> clientes) {
        // Map para almacenar la suma de importes por ID de cliente
        // Clave: ID del cliente, Valor: suma acumulada de importes
        Map<Integer, Double> importesPorCliente = new HashMap<>();
        
        // PRIMERA PASADA: Procesar todas las facturas - O(m)
        for (Factura factura : facturas) {
            int idCliente = factura.getIdCliente();
            double importe = factura.getImporte();
            
            // Obtener el valor actual del Map (0.0 si no existe)
            double importeActual = importesPorCliente.getOrDefault(idCliente, 0.0);
            
            // Sumar el nuevo importe
            importesPorCliente.put(idCliente, importeActual + importe);
        }
        
        // SEGUNDA PASADA: Crear resúmenes para todos los clientes - O(n)
        List<ResumenCliente> resumenes = new ArrayList<>();
        for (Cliente cliente : clientes) {
            int idCliente = cliente.getIdCliente();
            
            // Obtener la suma de importes del Map (0.0 si no tiene facturas)
            double sumaImportes = importesPorCliente.getOrDefault(idCliente, 0.0);
            
            // Crear el resumen del cliente
            ResumenCliente resumen = new ResumenCliente(
                cliente.getIdCliente(),
                cliente.getNombreCliente(),
                sumaImportes
            );
            
            resumenes.add(resumen);
        }
        
        return resumenes;
    }
    
    /**
     * IMPLEMENTACIÓN ORIGINAL (BÚSQUEDA LINEAL):
     * Complejidad temporal: O(n) para cada búsqueda
     * 
     * Este método ya no es necesario con la nueva implementación,
     * pero se mantiene comentado para referencia.
     */
    
    /*
    private Cliente buscarClientePorId(int idCliente, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
    */
    
    /**
     * NUEVA IMPLEMENTACIÓN OPTIMIZADA:
     * Si necesitáramos buscar clientes frecuentemente, podríamos crear
     * un Map<Integer, Cliente> para búsquedas O(1) en lugar de O(n)
     */
    private Cliente buscarClientePorIdOptimizado(int idCliente, Map<Integer, Cliente> clientesMap) {
        return clientesMap.get(idCliente); // O(1) en el caso promedio
    }
    
    /**
     * Imprime la lista de resúmenes de clientes
     * @param resumenes Lista de resúmenes a imprimir
     */
    public void imprimirResumenes(List<ResumenCliente> resumenes) {
        System.out.println("\n=== RESUMEN DE IMPORTES POR CLIENTE ===");
        System.out.printf("%-10s %-20s %-15s%n", "ID Cliente", "Nombre Cliente", "Total Importes");
        System.out.println("----------------------------------------");
        
        for (ResumenCliente resumen : resumenes) {
            System.out.printf("%-10d %-20s $%-14.2f%n", 
                resumen.getIdCliente(), 
                resumen.getNombreCliente(), 
                resumen.getSumaImportes());
        }
        System.out.println("========================================");
    }
    
    /**
     * MÉTODO ADICIONAL: Crear un Map de clientes para búsquedas rápidas
     * Útil si se necesita acceder frecuentemente a los clientes por ID
     * Complejidad: O(n) para crear el Map, pero luego O(1) para cada búsqueda
     */
    public Map<Integer, Cliente> crearMapaClientes(List<Cliente> clientes) {
        Map<Integer, Cliente> clientesMap = new HashMap<>();
        for (Cliente cliente : clientes) {
            clientesMap.put(cliente.getIdCliente(), cliente);
        }
        return clientesMap;
    }
}
