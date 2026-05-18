# **Práctica: Limpieza de Primavera (Refactorización)**
# 6\. Uso de la IA

| \# 1 |  |
| ----- | :---- |
| **Agente:** | Claude Haiku 4.5 |
| **Prompt:** | Conviértete en un experto en java y refactorización para ayudarme a refactorizar estos dos archivos .java. Los números como 0.25, 0.15 y 0.05 son descuentos, necesito que para eso crees constantes para cada uno de esos descuentos justo arriba de la clase. También necesito que cambies los nombres de las variables que dispone el archivo sabiendo que: cT es el cálculo total m es el importe base tC es el tipo de cliente dV para saber si el socio es vip o no.  También necesito que elimines ese código spaghetti que aparece causado por los múltiples if-else que impide leer el código y seguir el flujo de ejecución. Necesito que tanto para el cambio de nombre de las variables como de la creación de constantes para los descuentos utilicen nombres autoexplicativos (como puede ser DESC\_VIP)  para quien no sepa qué hace este programa pueda identificar sin tener que pensar o intuir nada saber que hace el programa. Por último modifica la estructura de control para que se aplane el código, invirtiendo las condiciones lógicas y utilizando retornos tempranos (return) para así eliminar todos los bloques else, por ejemplo en lugar de usar **if (importe \> 0 {...} else {return 0;}** utiliza if **(importe \<=0) return 0;** en la primera línea para así allanar el código.  |

**Respuesta:** 
