# **Práctica: Limpieza de Primavera (Refactorización)**

### **1\. Diagnóstico de Deuda Técnica**

Se han identificado tres antipatrones principales que comprometen la calidad del código actual:

* **Valores Mágicos:** El uso de literales numéricos sin contexto (como 0.25 y 0.15) dificulta la comprensión de los mismos.  
* **Variables sin significado:** Identificadores como dV, m y tC carecen de significado, rompiendo el principio de que el código debe revelar su intención. Esto aumenta drásticamente la carga cognitiva para los desarrolladores.  
* **Código Spaghetti:** El anidamiento excesivo de estructuras de control (if-else) genera un flujo de ejecución difícil de seguir, incrementando la probabilidad de introducir errores en futuros despliegues.

### **3\. Impacto y Beneficios de la Mejora**

1. **Aplanamiento de la Lógica :** La sustitución de bloques anidados por **cláusulas de guarda** reduce la complejidad ciclomática. El código ahora se ejecuta de manera secuencial, simplificando la depuración y la escritura de pruebas unitarias.  
2. **Nomenclatura Explícita:** Las variables m, dV y tC han sido refactorizadas a monto\_base, es\_cliente\_vip y tipo\_cliente respectivamente, documentando el comportamiento del sistema de forma intrínseca.  
3. **Desacoplamiento de Valores Hardcodeados:** Al extraer los descuentos y el IVA a constantes descriptivas al inicio del módulo, se establece un contrato claro con FacturacionLegacy. Cualquier cambio en la política fiscal o comercial ahora requiere la modificación de una única línea de código.  
4. **Tipado Estricto :** La inclusión de firmas de tipo (float, bool, str) garantiza un mejor análisis estático del código y minimiza errores en tiempo de ejecución.

### 6\. Uso de la IA

| \# 1 |  |
| ----- | :---- |
| **Agente:** | Claude Haiku 4.5 |
| **Prompt:** | Conviértete en un experto en java y refactorización para ayudarme a refactorizar estos dos archivos .java. Los números como 0.25, 0.15 y 0.05 son descuentos, necesito que para eso crees constantes para cada uno de esos descuentos justo arriba de la clase. También necesito que cambies los nombres de las variables que dispone el archivo sabiendo que: cT es el cálculo total m es el importe base tC es el tipo de cliente dV para saber si el socio es vip o no.  También necesito que elimines ese código spaghetti que aparece causado por los múltiples if-else que impide leer el código y seguir el flujo de ejecución. Necesito que tanto para el cambio de nombre de las variables como de la creación de constantes para los descuentos utilicen nombres autoexplicativos (como puede ser DESC\_VIP)  para quien no sepa qué hace este programa pueda identificar sin tener que pensar o intuir nada saber que hace el programa. Por último modifica la estructura de control para que se aplane el código, invirtiendo las condiciones lógicas y utilizando retornos tempranos (return) para así eliminar todos los bloques else, por ejemplo en lugar de usar **if (importe \> 0 {...} else {return 0;}** utiliza if **(importe \<=0) return 0;** en la primera línea para así allanar el código.  |

**Respuesta:** 
```
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

```
```
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Batería de pruebas unitarias para asegurar la refactorización.
 * EL ALUMNADO NO DEBE MODIFICAR ESTE ARCHIVO.
 */
public class FacturacionLegacyTest {

    private FacturacionLegacy facturacion;

    @BeforeEach
    public void setUp() {
        facturacion = new FacturacionLegacy();
    }

    @Test
    public void testClienteVipConDescuentoExtra() {
        // tipoCliente = 1, esClienteVip = true -> 25% descuento. 100 - 25 = 75
        assertEquals(75.0, facturacion.calcularTotalConDescuento(100.0, 1, true), 0.001,
                "Fallo: El cliente VIP con descuento extra debe pagar 75.0");
    }

    @Test
    public void testClienteVipSinDescuentoExtra() {
        // tipoCliente = 1, esClienteVip = false -> 15% descuento. 100 - 15 = 85
        assertEquals(85.0, facturacion.calcularTotalConDescuento(100.0, 1, false), 0.001,
                "Fallo: El cliente VIP normal debe pagar 85.0");
    }

    @Test
    public void testClienteEstandar() {
        // tipoCliente = 2, esClienteVip = true o false -> 5% descuento. 100 - 5 = 95
        assertEquals(95.0, facturacion.calcularTotalConDescuento(100.0, 2, false), 0.001,
                "Fallo: El cliente Estándar debe pagar 95.0");
        assertEquals(95.0, facturacion.calcularTotalConDescuento(100.0, 2, true), 0.001,
                "Fallo: El cliente Estándar debe pagar 95.0 independientemente de esClienteVip");
    }

    @Test
    public void testClienteSinCategoria() {
        // tipoCliente = 3 -> 0% descuento. 100 - 0 = 100
        assertEquals(100.0, facturacion.calcularTotalConDescuento(100.0, 3, false), 0.001,
                "Fallo: Un cliente sin categoría conocida no tiene descuento.");
    }

    @Test
    public void testImporteNegativoOCero() {
        // importeBase <= 0 -> 0
        assertEquals(0.0, facturacion.calcularTotalConDescuento(0.0, 1, true), 0.001,
                "Fallo: Importe 0 debe devolver 0");
        assertEquals(0.0, facturacion.calcularTotalConDescuento(-50.0, 2, false), 0.001,
                "Fallo: Importes negativos deben devolver 0");
    }
}

```
