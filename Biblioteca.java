import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que gestiona la lógica de la biblioteca universitaria.
 * Incluye funcionalidades para registrar libros, usuarios, realizar préstamos
 * y generar reportes. Maneja diversas excepciones predefinidas y
 * personalizadas.
 */
public class Biblioteca {
  private Map<String, Libro> libros; // ISBN -> Libro
  private Map<String, Usuario> usuarios; // ClaveUsuario -> Usuario
  private List<Prestamo> prestamosActivos;

  /**
   * Constructor de la clase Biblioteca.
   * Inicializa las colecciones para almacenar libros, usuarios y préstamos.
   */
  public Biblioteca() {
    this.libros = new HashMap<>();
    this.usuarios = new HashMap<>();
    this.prestamosActivos = new ArrayList<>();
  }

  /**
   * Registra un nuevo libro en el sistema de la biblioteca.
   * 
   * @param isbn        El ISBN único del libro.
   * @param titulo      El título del libro.
   * @param autor       El autor del libro.
   * @param totalCopias El número total de copias de este libro.
   * @throws IllegalArgumentException Si alguno de los datos proporcionados es
   *                                  inválido (nulo, vacío, o copias no
   *                                  positivas).
   */
  public void registrarLibro(String isbn, String titulo, String autor, String totalCopiasStr) {
    try {
      // NullPointerException y IllegalArgumentException para datos básicos
      if (isbn == null || isbn.trim().isEmpty()) {
        throw new IllegalArgumentException("El ISBN no puede ser nulo o vacío.");
      }
      if (titulo == null || titulo.trim().isEmpty()) {
        throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
      }
      if (autor == null || autor.trim().isEmpty()) {
        throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
      }
      if (totalCopiasStr == null || totalCopiasStr.trim().isEmpty()) {
        throw new IllegalArgumentException("El número de copias no puede ser nulo o vacío.");
      }

      int totalCopias;
      try {
        totalCopias = Integer.parseInt(totalCopiasStr); // NumberFormatException
      } catch (NumberFormatException e) {
        throw new NumberFormatException("El número de copias debe ser un valor numérico entero.");
      }

      if (totalCopias <= 0) {
        throw new IllegalArgumentException("El número total de copias debe ser mayor que cero.");
      }

      if (libros.containsKey(isbn)) {
        throw new IllegalArgumentException("Ya existe un libro registrado con el ISBN: " + isbn);
      }

      Libro nuevoLibro = new Libro(isbn, titulo, autor, totalCopias);
      libros.put(isbn, nuevoLibro);
      System.out.println("Libro registrado exitosamente: " + titulo + " (ISBN: " + isbn + ")");
    } catch (IllegalArgumentException e) {
      System.err.println("Error al registrar libro: " + e.getMessage());
    }
  }

  /**
   * Registra un nuevo usuario en el sistema de la biblioteca.
   * 
   * @param claveUsuario La clave única del usuario.
   * @param nombre       El nombre completo del usuario.
   * @param licenciatura La licenciatura que cursa el usuario.
   * @param semestreStr  El semestre que cursa el usuario como String.
   * @throws IllegalArgumentException Verifica Si alguno de los datos
   *                                  proporcionados es inválido.
   */
  public void registrarUsuario(String claveUsuario, String nombre, String licenciatura, String semestreStr) {
    try {
      if (claveUsuario == null || claveUsuario.trim().isEmpty()) {
        throw new IllegalArgumentException("La clave de usuario no puede ser nula o vacía.");
      }
      if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacío.");
      }
      if (licenciatura == null || licenciatura.trim().isEmpty()) {
        throw new IllegalArgumentException("La licenciatura no puede ser nula o vacía.");
      }
      if (semestreStr == null || semestreStr.trim().isEmpty()) {
        throw new IllegalArgumentException("El semestre no puede ser nulo o vacío.");
      }

      int semestre;
      try {
        semestre = Integer.parseInt(semestreStr); // NumberFormatException
      } catch (NumberFormatException e) {
        throw new NumberFormatException("El semestre debe ser un valor numérico entero.");
      }

      if (semestre <= 0) {
        throw new IllegalArgumentException("El semestre debe ser un número positivo.");
      }

      if (usuarios.containsKey(claveUsuario)) {
        throw new IllegalArgumentException("Ya existe un usuario registrado con la clave: " + claveUsuario);
      }

      Usuario nuevoUsuario = new Usuario(claveUsuario, nombre, licenciatura, semestre);
      usuarios.put(claveUsuario, nuevoUsuario);
      System.out.println("Usuario registrado exitosamente: " + nombre + " (Clave: " + claveUsuario + ")");
    } catch (IllegalArgumentException e) {
      System.err.println("Error al registrar usuario: " + e.getMessage());
    }
  }

  /**
   * Permite a un usuario realizar el préstamo de un libro.
   * 
   * @param claveUsuario     La clave del usuario que solicita el préstamo.
   * @param isbnLibro        El ISBN del libro a prestar.
   * @param fechaPrestamoStr La fecha del préstamo en formato "dd/MM/yyyy".
   * @throws NullPointerException       Si el usuario, libro o fecha de préstamo
   *                                    son nulos.
   * @throws LibroNoEncontradoException Si el libro no existe en el sistema.
   * @throws LibroNoDisponibleException Si no hay copias disponibles del libro.
   * @throws IllegalArgumentException   Si la clave de usuario o ISBN son
   *                                    inválidos, o la fecha tiene formato
   *                                    incorrecto.
   */
  public void realizarPrestamo(String claveUsuario, String isbnLibro, String fechaPrestamoStr) {
    Usuario usuario = null;
    Libro libro = null;
    LocalDate fechaPrestamo = null;

    try {
      // Validaciones iniciales de Null y vacío
      if (claveUsuario == null || claveUsuario.trim().isEmpty()) {
        throw new IllegalArgumentException("La clave de usuario no puede ser nula o vacía.");
      }
      if (isbnLibro == null || isbnLibro.trim().isEmpty()) {
        throw new IllegalArgumentException("El ISBN del libro no puede ser nulo o vacío.");
      }
      if (fechaPrestamoStr == null || fechaPrestamoStr.trim().isEmpty()) {
        throw new IllegalArgumentException("La fecha de préstamo no puede ser nula o vacía.");
      }

      usuario = usuarios.get(claveUsuario);
      // NullPointerException si el usuario no existe (get retorna null)
      if (usuario == null) {
        throw new IllegalArgumentException("Usuario no encontrado con la clave: " + claveUsuario);
      }

      libro = libros.get(isbnLibro);
      // LibroNoEncontradoException si el libro no existe
      if (libro == null) {
        throw new LibroNoEncontradoException("Libro no encontrado con el ISBN: " + isbnLibro);
      }

      // Assertion 1: Verificar que el usuario y el libro no son nulos antes de
      // continuar
      assert usuario != null : "Assertion Falló: Usuario es nulo después de la búsqueda.";
      assert libro != null : "Assertion Falló: Libro es nulo después de la búsqueda.";

      try {
        fechaPrestamo = LocalDate.parse(fechaPrestamoStr, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy. " + e.getMessage());
      }

      // Assertion 2: Verificar que la fecha de préstamo no es una fecha futura
      assert !fechaPrestamo.isAfter(LocalDate.now()) : "Assertion Falló: La fecha de préstamo no puede ser futura.";

      libro.prestarCopia(); // Puede lanzar LibroNoDisponibleException

      Prestamo nuevoPrestamo = new Prestamo(usuario, libro, fechaPrestamo);
      prestamosActivos.add(nuevoPrestamo);
      System.out.println("Préstamo realizado exitosamente:");
      System.out.println(nuevoPrestamo);
    } catch (IllegalArgumentException | LibroNoEncontradoException | LibroNoDisponibleException
        | NullPointerException e) {
      System.err.println("Error al realizar préstamo: " + e.getMessage());
    } finally {
      System.out.println("Intento de préstamo procesado para usuario: " + claveUsuario + ", libro: " + isbnLibro);
    }
  }

  /**
   * Muestra un resumen en pantalla de los libros disponibles y los libros
   * prestados.
   */
  public void mostrarReporte() {
    System.out.println("\n--- Reporte de la Biblioteca ---");
    System.out.println("\nLibros Disponibles:");
    if (libros.isEmpty()) {
      System.out.println("No hay libros registrados en el sistema.");
    } else {
      libros.forEach((isbn, libro) -> {
        System.out.println("  - Título: " + libro.getTitulo() +
            ", Autor: " + libro.getAutor() +
            ", ISBN: " + libro.getIsbn() +
            ", Copias Disponibles: " + libro.getCopiasDisponibles() +
            " de " + libro.getTotalCopias());
      });
    }

    System.out.println("\nLibros Prestados (Activos):");
    if (prestamosActivos.isEmpty()) {
      System.out.println("No hay préstamos activos en este momento.");
    } else {
      prestamosActivos.forEach(prestamo -> {
        System.out.println("  - Usuario: " + prestamo.getUsuario().getNombre() +
            ", Libro: " + prestamo.getLibro().getTitulo() +
            ", ISBN: " + prestamo.getLibro().getIsbn() +
            ", Fecha Préstamo: "
            + prestamo.getFechaPrestamo().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
      });
    }
    System.out.println("--------------------------------\n");
  }
}
