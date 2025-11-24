/**
 * Excepción personalizada para indicar que un libro no ha sido encontrado en el sistema.
 * Se lanza cuando se busca un libro por ISBN y este no existe.
 */
public class LibroNoEncontradoException extends Exception {
    /**
     * Constructor para LibroNoEncontradoException.
     * @param message Mensaje descriptivo de la excepción.
     */
    public LibroNoEncontradoException(String message) {
        super(message);
    }
}
