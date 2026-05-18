/**
 * Clase Legacy del sistema de facturación.
 * ADVERTENCIA: Código con alta deuda técnica. No modificar la firma del método.
 */

// Constantes de descuentos por tipo de cliente

public class FacturacionLegacy {

private static final double DESC_VIP_EXTRA = 0.25;      // Descuento VIP con beneficio extra
private static final double DESC_VIP_STANDAR = 0.15;    // Descuento VIP estándar
private static final double DESC_CLIENTE_ESTANDAR = 0.05; // Descuento cliente estándar

    // Método a refactorizar
    public double calcularTotalConDescuento(double importeBase, int tipoCliente, boolean esClienteVip) {



        // Retorno temprano: importes no válidos
        if (importeBase <= 0) {
            return 0;
        }

        // Aplicar descuento según tipo de cliente
        if (tipoCliente == 1) {
            return aplicarDescuentoClienteVip(importeBase, esClienteVip);
        }

        if (tipoCliente == 2) {
            return aplicarDescuentoClienteEstandar(importeBase);
        }

        // Cliente sin categoría conocida: sin descuento
        return importeBase;
    }

    /**
     * Aplica el descuento correspondiente para clientes VIP.
     */
    private double aplicarDescuentoClienteVip(double importeBase, boolean esClienteVip) {
        if (esClienteVip) {
            return importeBase * (1 - DESC_VIP_EXTRA);
        }
        return importeBase * (1 - DESC_VIP_STANDAR);
    }

    /**
     * Aplica el descuento correspondiente para clientes estándar.
     */
    private double aplicarDescuentoClienteEstandar(double importeBase) {
        return importeBase * (1 - DESC_CLIENTE_ESTANDAR);
    }
}
