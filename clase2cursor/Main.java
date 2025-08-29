import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear lista de clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Juan Pérez"));
        clientes.add(new Cliente(2, "María García"));
        clientes.add(new Cliente(3, "Carlos López"));
        clientes.add(new Cliente(4, "Ana Martínez"));
        
        // Crear lista de facturas
        List<Factura> facturas = new ArrayList<>();
        facturas.add(new Factura(1001, 1, 1500.50));
        facturas.add(new Factura(1002, 1, 2300.75));
        facturas.add(new Factura(1003, 2, 800.25));
        facturas.add(new Factura(1004, 2, 1200.00));
        facturas.add(new Factura(1005, 2, 950.50));
        facturas.add(new Factura(1006, 3, 3000.00));
        facturas.add(new Factura(1007, 4, 750.25));
        facturas.add(new Factura(1008, 4, 1800.00));
        
        // Crear instancia del sistema de facturación
        SistemaFacturacion sistema = new SistemaFacturacion();
        
        // Mostrar datos de entrada
        System.out.println("=== SISTEMA DE FACTURACIÓN ===");
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        } // n
        
        System.out.println("\n--- LISTA DE FACTURAS ---");
        for (Factura factura : facturas) {
            System.out.println(factura);
        } // n
        
        // Generar resumen de importes por cliente
        List<ResumenCliente> resumenes = sistema.generarResumenImportes(facturas, clientes);
        
        // Mostrar el resumen
        sistema.imprimirResumenes(resumenes);
        
        // Mostrar estadísticas adicionales
        mostrarEstadisticas(resumenes);
    }
    
    /**
     * Muestra estadísticas adicionales del sistema
     * @param resumenes Lista de resúmenes de clientes
     */
    private static void mostrarEstadisticas(List<ResumenCliente> resumenes) {
        double totalGeneral = 0.0;
        double maximoImporte = 0.0;
        String clienteMaximo = "";
        double minimoImporte = Double.MAX_VALUE;
        String clienteMinimo = "";
        
        for (ResumenCliente resumen : resumenes) {
            totalGeneral += resumen.getSumaImportes();
            
            if (resumen.getSumaImportes() > maximoImporte) {
                maximoImporte = resumen.getSumaImportes();
                clienteMaximo = resumen.getNombreCliente();
            }
            
            if (resumen.getSumaImportes() < minimoImporte) {
                minimoImporte = resumen.getSumaImportes();
                clienteMinimo = resumen.getNombreCliente();
            }
        }
        
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ===");
        System.out.printf("Total general de facturación: $%.2f%n", totalGeneral);
        System.out.printf("Cliente con mayor facturación: %s ($%.2f)%n", clienteMaximo, maximoImporte);
        System.out.printf("Cliente con menor facturación: %s ($%.2f)%n", clienteMinimo, minimoImporte);
        System.out.printf("Promedio por cliente: $%.2f%n", totalGeneral / resumenes.size());
    }
}
