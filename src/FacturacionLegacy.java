package main;

/**
 * Clase Legacy del sistema de facturación.
 * ADVERTENCIA: Código con alta deuda técnica. No modificar la firma del método.
 */
public class FacturacionLegacy {

    // Constantes de descuentos por tipo de cliente
    private static final double DESC_VIP_EXTRA = 0.25;      // Descuento VIP con beneficio extra
    private static final double DESC_VIP_STANDAR = 0.15;    // Descuento VIP estándar
    private static final double DESC_CLIENTE_ESTANDAR = 0.05; // Descuento cliente estándar

    /**
     * Calcula el total a pagar aplicando el descuento correspondiente según el tipo de cliente.
     * @param importeBase el importe inicial sin aplicar descuentos. Debe ser un valor positivo.
     *                    Si es menor o igual a 0, el método devuelve 0.
     * @param tipoCliente el identificador del tipo de cliente:
     *                    - 1: Cliente VIP (aplica 25% si esClienteVip=true, 15% si         esClienteVip=false)
     *                    - 2: Cliente Estándar (aplica 5% de descuento)
     *                    - Cualquier otro valor: sin descuento (aplica 0%)
     * @param esClienteVip indica si el cliente VIP tiene beneficios extra.
     *                     Solo aplica cuando tipoCliente es 1.
     *                     - true: aplica descuento VIP extra (25%)
     *                     - false: aplica descuento VIP estándar (15%)
     * 
     * @return el importe total a pagar con el descuento aplicado.
     *         - Retorna 0 si importeBase es menor o igual a 0
     *         - Retorna el importe con el descuento correspondiente según el tipo de cliente
     *
     */
    public double calcularTotalConDescuento(double importeBase, int tipoCliente, boolean esClienteVip) {
        // Devuelve 0 si el importe base es negativo o cero
        if (importeBase <= 0) {
            return 0;
        }

        // Depende del tipo de cliente devuelve un descuento u otro
        if (tipoCliente == 1) {
            return aplicarDescuentoClienteVip(importeBase, esClienteVip);
        }

        if (tipoCliente == 2) {
            return aplicarDescuentoClienteEstandar(importeBase);
        }

        // Si el cliente no tiene categoría conocida, no se aplica descuento
        return importeBase;
    }

    /**
     * Aplica el descuento correspondiente para los clientes VIP.
     * @param importeBase el importe sin descuento a aplicar.
     *                    Se asume que es siempre un valor positivo.
     * @param esClienteVip indica si el cliente VIP tiene derecho a beneficios extra.
     *                     - true: aplica descuento VIP extra (25%)
     *                     - false: aplica descuento VIP estándar (15%)
     * 
     * @return el importe con el descuento VIP aplicado.
     *         - Si esClienteVip es true: importeBase * 0.75 (descuento del 25%)
     *         - Si esClienteVip es false: importeBase * 0.85 (descuento del 15%)
     */
    private double aplicarDescuentoClienteVip(double importeBase, boolean esClienteVip) {
        if (esClienteVip) {
            return importeBase * (1 - DESC_VIP_EXTRA);
        }
        return importeBase * (1 - DESC_VIP_STANDAR);
    }

    /**
     * Aplica el descuento correspondiente para los clientes estándar. 
     * @param importeBase el importe sin descuento a aplicar.
     *                    Se asume que es siempre un valor positivo.
     * 
     * @return el importe con el descuento estándar aplicado.
     *         Siempre devuelve: importeBase * 0.95 (descuento del 5%)
     */
    private double aplicarDescuentoClienteEstandar(double importeBase) {
        return importeBase * (1 - DESC_CLIENTE_ESTANDAR);
    }
}
