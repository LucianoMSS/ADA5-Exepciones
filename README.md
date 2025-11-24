# ADA5-Exepciones
# Sistema de Gestión de Préstamos de Biblioteca Universitaria

## Descripción del Proyecto

Este proyecto implementa un sistema básico de gestión de préstamos de libros para una biblioteca universitaria, desarrollado en Java. El objetivo principal es demostrar el manejo adecuado de excepciones (tanto predefinidas como personalizadas) y el uso de aserciones para la depuración del código.

El sistema permite las siguientes funcionalidades:

- **Registrar Libros**: Con datos como ISBN, título, autor y número de copias disponibles.
- **Registrar Usuarios**: Con clave de usuario, nombre, licenciatura y semestre.
- **Realizar Préstamos**: Indicando el usuario, el libro (por ISBN) y la fecha del préstamo.
- **Mostrar un Resumen/Reporte**: De libros disponibles y préstamos activos.

**Manejo de Excepciones Implementado:**

- `NullPointerException`: Al intentar acceder a objetos no inicializados.
- `NumberFormatException`: Al convertir entradas de texto a números.
- `IllegalArgumentException`: Para datos de entrada inválidos (ej. copias negativas, campos vacíos, ISBN/usuario duplicados).
- `LibroNoDisponibleException` (personalizada): Cuando no hay copias de un libro para prestar.
- `LibroNoEncontradoException` (personalizada): Cuando el ISBN del libro no existe en el sistema.

Además, se incluye **aserciones** en los constructores de las clases de modelo (`Libro`, `Usuario`) y en la lógica de préstamos (`Biblioteca`) para validar condiciones críticas durante la fase de desarrollo y depuración.

## Instrucciones de Compilación y Ejecución

Para compilar y ejecutar este programa, asegúrate de tener instalado el Java Development Kit (JDK).

1. **Navegar al Directorio Raíz del Proyecto:**
   Abre una terminal y dirígete al directorio donde se encuentran los archivos.
   
3. **Compilar los Archivos Java:**
   Desde el directorio raíz del proyecto , ejecuta el siguiente comando para compilar todos los archivos `.java` dentro de la carpeta:

   ```bash
   javac *.java
   ```

   Esto generará los archivos `.class` compilados dentro del directorio.

4. **Ejecutar el Programa:**
   Para ejecutar la aplicación y asegurarte de que las aserciones estén activadas (lo cual es crucial para la depuración), utiliza el siguiente comando:

   ```bash
   java -ea -cp "Nombre de la carpeta" Main
   ```

   *   `-ea`: Habilita las aserciones en el entorno de ejecución de Java.
   *   `-cp "Nombre de la carpeta"`: Añade la carpeta al classpath, permitiendo que el programa encuentre las clases compiladas.
   *   `Main`: Indica a la JVM que inicie la ejecución desde la clase `Main`.

   La consola mostrará una serie de operaciones de registro y préstamo, incluyendo demostraciones de cómo se manejan las diversas excepciones.

## Capturas de Pantalla del Programa en Ejecución
<img width="786" height="428" alt="image" src="https://github.com/user-attachments/assets/381b5552-2f05-47ae-aeed-0f68aa55e1db" />
<img width="1137" height="810" alt="image" src="https://github.com/user-attachments/assets/8b674d7c-dca4-4e7c-8a31-447f4cb44d08" />
<img width="980" height="268" alt="image" src="https://github.com/user-attachments/assets/c61b56b0-bb9a-45df-b04c-541e1f1d308c" />

## Enlace al Video de la Presentación

[https://drive.google.com/drive/folders/1ZzDAChCfa38izvVVRSUnmxrz4bC-nzqa?usp=sharing)

