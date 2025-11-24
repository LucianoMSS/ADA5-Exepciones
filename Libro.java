/**
 * Clase que representa un libro en el sistema de gestión de préstamos.
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int totalCopias;
    private int copiasDisponibles;

    /**
     * Constructor para la clase Libro.
     * @param isbn El ISBN único del libro.
     * @param titulo El título del libro.
     * @param autor El autor del libro.
     * @param totalCopias El número total de copias de este libro.
     */
    public Libro(String isbn, String titulo, String autor, int totalCopias) {
        // Assertions for initial data validity
        assert isbn != null && !isbn.trim().isEmpty() : "ISBN no puede ser nulo o vacío.";
        assert titulo != null && !titulo.trim().isEmpty() : "Título no puede ser nulo o vacío.";
        assert autor != null && !autor.trim().isEmpty() : "Autor no puede ser nulo o vacío.";
        assert totalCopias > 0 : "El número total de copias debe ser mayor que cero.";

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.totalCopias = totalCopias;
        this.copiasDisponibles = totalCopias;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getTotalCopias() {
        return totalCopias;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    /**
     * Decrementa el número de copias disponibles.
     * @throws LibroNoDisponibleException Si no hay copias disponibles para prestar.
     */
    public void prestarCopia() throws LibroNoDisponibleException {
        if (copiasDisponibles <= 0) {
            throw new LibroNoDisponibleException("No hay copias disponibles del libro con ISBN: " + isbn);
        }
        copiasDisponibles--;
    }

    /**
     * Incrementa el número de copias disponibles.
     */
    public void devolverCopia() {
        if (copiasDisponibles < totalCopias) {
            copiasDisponibles++;
        }
    }

    @Override
    public String toString() {
        return "Libro{" +
               "isbn='" + isbn + "'" +
               ", titulo='" + titulo + "'" +
               ", autor='" + autor + "'" +
               ", totalCopias=" + totalCopias +
               ", copiasDisponibles=" + copiasDisponibles +
               '}';
    }
}
