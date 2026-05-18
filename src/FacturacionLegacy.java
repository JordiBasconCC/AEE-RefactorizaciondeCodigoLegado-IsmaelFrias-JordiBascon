/**
 * Clase Legacy del sistema de facturación.
 * ADVERTENCIA: Código con alta deuda técnica. No modificar la firma del método.
 */

// Constantes de descuentos por tipo de cliente

public class FacturacionLegacy {

private static final double DESC_VIP_EXTRA = 0.25;      // Descuento VIP con beneficio extra
private static final double DESC_VIP_STANDAR = 0.15;    // Descuento VIP estándar
private static final double DESC_CLIENTE_ESTANDAR = 0.05; // Descuento cliente estándar

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
     */
    private double aplicarDescuentoClienteVip(double importeBase, boolean esClienteVip) {
        if (esClienteVip) {
            return importeBase * (1 - DESC_VIP_EXTRA);
        }
        return importeBase * (1 - DESC_VIP_STANDAR);
    }

    /**
     * Aplica el descuento correspondiente para los clientes estándar.
     */
    private double aplicarDescuentoClienteEstandar(double importeBase) {
        return importeBase * (1 - DESC_CLIENTE_ESTANDAR);
    }
}
