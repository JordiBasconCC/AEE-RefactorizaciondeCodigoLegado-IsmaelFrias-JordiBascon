Aquí tienes todo el documento estructurado y listo en formato Markdown (.md). He organizado el contenido dentro de un bloque de código para que puedas copiarlo íntegramente con un solo clic y pegarlo en tu archivo README.md.

Markdown
# **Práctica: Limpieza de Primavera (Refactorización y Deuda Técnica)**

---

## **1. Diagnóstico de Deuda Técnica**

Tras una revisión exhaustiva del módulo de facturación heredado (`FacturacionLegacy`), se identificaron tres antipatrones de diseño de software que comprometían la mantenibilidad, escalabilidad y legibilidad del sistema:

* **Valores Mágicos (Magic Numbers):** El uso de literales numéricos directos (`0.25`, `0.15` y `0.05`) introducía una preocupante falta de contexto operativo. Al no estar declarados de forma explícita, cualquier modificación futura en las políticas fiscales o comerciales incrementaba el riesgo de inconsistencias lógicas.
* **Variables sin Significado (Nomenclatura Ofuscada):** Identificadores crípticos como `dV`, `m` y `tC` violaban el principio de código auto-documentado. Esta opacidad semántica obligaba a los desarrolladores a deducir el propósito de las variables, elevando drásticamente la carga cognitiva y ralentizando los procesos de mantenimiento.
* **Código Spaghetti (Estructuras Anidadas Complejas):** El flujo original dependía de un anidamiento excesivo de estructuras de control (`if-else`). Esta arquitectura piramidal elevaba de forma innecesaria la complejidad ciclomática, transformando el seguimiento de los caminos lógicos en una tarea propensa a errores de regresión.

---

## **2. Objetivos de la Refactorización**

La intervención sobre el código fuente se planteó bajo los siguientes estándares de la ingeniería de software moderna:
1.  **Reducción de Complejidad:** Transformar un flujo ramificado en una secuencia lineal predecible.
2.  **Desacoplamiento Estricto:** Separar las reglas de negocio de la lógica de control operacional.
3.  **Preservación de Comportamiento:** Asegurar el principio de caja negra, alterando la estructura interna sin afectar el resultado esperado por los clientes del módulo.

---

## **3. Impacto y Beneficios de la Mejora**

La reingeniería aplicada mitiga la deuda técnica acumulada y dota al sistema de las siguientes ventajas competitivas:

1.  **Aplanamiento de la Lógica mediante Cláusulas de Guarda:** La sustitución de bloques `if-else` condicionales por retornos tempranos (`early returns`) permite aislar las excepciones y flujos alternativos en las primeras líneas del método. El código ahora se ejecuta de manera estrictamente secuencial, simplificando la depuración y facilitando la cobertura de pruebas.
2.  **Nomenclatura Explícita y Ubicua:** Las variables ambiguas fueron refactorizadas a `importeBase`, `tipoCliente` y `esClienteVip`. Esta actualización introduce documentación intrínseca, alineando el código fuente con el lenguaje del dominio de negocio.
3.  **Desacoplamiento de Valores Hardcodeados:** Al extraer los porcentajes de descuento a constantes descriptivas (`private static final double`) en la cabecera del módulo, se centraliza la configuración de las reglas de negocio. Cualquier cambio comercial posterior requerirá la modificación de una única línea de código.
4.  **Tipado Estricto de Java:** Se ha garantizado la coherencia del tipado fuerte nativo (`double`, `int`, `boolean`), permitiendo un análisis estático robusto a través de herramientas de integración continua (CI) y previniendo anomalías de datos en tiempo de ejecución.

---

## **4. Estrategia de Aseguramiento de la Calidad (QA)**

Para garantizar que el proceso de refactorización no alterase el comportamiento funcional histórico del sistema, se implementó un arnés de pruebas automatizadas utilizando **JUnit 5**. 

La batería de pruebas diseñada valida de forma exhaustiva el 100% de los escenarios lógicos:
* **Validación de Límites:** Verificación de importes negativos o iguales a cero.
* **Segmentación VIP:** Cobertura de flujos preferenciales con y sin beneficios extra (25% y 15%).
* **Segmentación Estándar y Excepciones:** Evaluación de perfiles de cliente convencionales y usuarios sin categorización (0% descuento).

---

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
