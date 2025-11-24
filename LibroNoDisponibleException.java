/**
 * Excepción personalizada para indicar que un libro no está disponible para préstamo.
 * Se lanza cuando no hay copias disponibles de un libro.
 */
public class LibroNoDisponibleException extends Exception {
    /**
     * Constructor para LibroNoDisponibleException.
     * @param message Mensaje descriptivo de la excepción.
     */
    public LibroNoDisponibleException(String message) {
        super(message);
    }
}
