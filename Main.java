import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase principal para demostrar el funcionamiento del sistema de gestión de préstamos de la biblioteca.
 * Incluye la interacción con el usuario y pruebas de manejo de excepciones y aserciones.
 */
public class Main {
    public static void main(String[] args) {
        // Para habilitar las aserciones, ejecutar el programa con la opción -ea:
        // java -ea Main
        
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("--- DEMOSTRACIÓN DEL SISTEMA DE GESTIÓN DE PRÉSTAMOS ---");

        // --- 1. Registro de Libros ---
        System.out.println("\n--- REGISTRO DE LIBROS ---");
        biblioteca.registrarLibro("978-0321765723", "Effective Java", "Joshua Bloch", "3");
        biblioteca.registrarLibro("978-0134685991", "Clean Code", "Robert C. Martin", "2");
        biblioteca.registrarLibro("978-1234567890", "The Great Java", "Java Master", "1");
        
        // Prueba: ISBN duplicado (IllegalArgumentException)
        biblioteca.registrarLibro("978-0321765723", "Effective Java 2nd Ed", "Joshua Bloch", "1");
        // Prueba: Copias inválidas (NumberFormatException y IllegalArgumentException)
        biblioteca.registrarLibro("978-1111111111", "Bad Copies Book", "Author", "cero");
        biblioteca.registrarLibro("978-2222222222", "Zero Copies Book", "Author", "0");
        // Prueba: Datos nulos/vacíos (IllegalArgumentException)
        biblioteca.registrarLibro(null, "Null ISBN Book", "Author", "1");
        biblioteca.registrarLibro("978-3333333333", null, "Null Title Book", "1");

        // --- 2. Registro de Usuarios ---
        System.out.println("\n--- REGISTRO DE USUARIOS ---");
        biblioteca.registrarUsuario("U001", "Ana Garcia", "Ingeniería de Software", "5");
        biblioteca.registrarUsuario("U002", "Pedro Martinez", "Ciencias de la Computación", "7");
        biblioteca.registrarUsuario("U003", "Maria Lopez", "Diseño Gráfico", "3");

        // Prueba: Clave de usuario duplicada (IllegalArgumentException)
        biblioteca.registrarUsuario("U001", "Ana Nueva", "Matematicas", "2");
        // Prueba: Semestre inválido (NumberFormatException y IllegalArgumentException)
        biblioteca.registrarUsuario("U004", "Juan Perez", "Física", "ocho");
        biblioteca.registrarUsuario("U005", "Laura Díaz", "Química", "0");
        // Prueba: Datos nulos/vacíos (IllegalArgumentException)
        biblioteca.registrarUsuario(null, "Null Clave User", "Carrera", "1");
        biblioteca.registrarUsuario("U006", null, "Null Name User", "1");

        // --- 3. Préstamo de Libros ---
        System.out.println("\n--- REALIZACIÓN DE PRÉSTAMOS ---");
        String today = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Préstamo exitoso
        biblioteca.realizarPrestamo("U001", "978-0321765723", today); // Ana presta Effective Java
        biblioteca.realizarPrestamo("U002", "978-0134685991", today); // Pedro presta Clean Code
        biblioteca.realizarPrestamo("U001", "978-1234567890", today); // Ana presta The Great Java
        biblioteca.realizarPrestamo("U003", "978-0321765723", today); // Maria presta Effective Java

        // --- 4. Escenarios de Excepciones en Préstamos ---

        // Prueba: Libro no encontrado (LibroNoEncontradoException)
        biblioteca.realizarPrestamo("U001", "999-9999999999", today);

        // Prueba: Usuario no encontrado (IllegalArgumentException)
        biblioteca.realizarPrestamo("U999", "978-0321765723", today);

        // Prueba: Libro no disponible (LibroNoDisponibleException)
        // Pedir el libro 'The Great Java' (solo 1 copia, ya prestada por U001)
        biblioteca.realizarPrestamo("U002", "978-1234567890", today);

        // Pedir 'Effective Java' (3 copias, 2 prestadas)
        biblioteca.realizarPrestamo("U002", "978-0321765723", today);
        // Ahora debería estar agotado 'Effective Java'
        biblioteca.realizarPrestamo("U003", "978-0321765723", today);

        // Prueba: Fecha de préstamo inválida (IllegalArgumentException - DateTimeParseException)
        biblioteca.realizarPrestamo("U001", "978-0134685991", "31-12-2025");
        biblioteca.realizarPrestamo("U001", "978-0134685991", "not-a-date");

        // Prueba: Datos nulos/vacíos en préstamo (IllegalArgumentException)
        biblioteca.realizarPrestamo(null, "978-0134685991", today);
        biblioteca.realizarPrestamo("U001", null, today);
        biblioteca.realizarPrestamo("U001", "978-0134685991", null);

        // --- 5. Reporte ---
        biblioteca.mostrarReporte();

        scanner.close();
    }
}
