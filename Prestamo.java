import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un préstamo de un libro a un usuario.
 */
public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaPrestamo;

    /**
     * Constructor para la clase Prestamo.
     * @param usuario El usuario que realiza el préstamo.
     * @param libro El libro que se presta.
     * @param fechaPrestamo La fecha en que se realiza el préstamo.
     */
    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaPrestamo) {
        // Assertions for initial data validity
        assert usuario != null : "El usuario no puede ser nulo.";
        assert libro != null : "El libro no puede ser nulo.";
        assert fechaPrestamo != null : "La fecha de préstamo no puede ser nula.";

        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Prestamo{" +
               "usuario=" + usuario.getNombre() +
               ", libro=" + libro.getTitulo() +
               ", isbn=" + libro.getIsbn() +
               ", fechaPrestamo=" + fechaPrestamo.format(formatter) +
               '}';
    }
}
