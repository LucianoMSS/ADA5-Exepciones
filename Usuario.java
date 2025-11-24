/**
 * Clase que representa un usuario en el sistema de gestión de préstamos.
 */
public class Usuario {
    private String claveUsuario;
    private String nombre;
    private String licenciatura;
    private int semestre;

    /**
     * Constructor para la clase Usuario.
     * @param claveUsuario La clave única del usuario.
     * @param nombre El nombre completo del usuario.
     * @param licenciatura La licenciatura que cursa el usuario.
     * @param semestre El semestre que cursa el usuario.
     */
    public Usuario(String claveUsuario, String nombre, String licenciatura, int semestre) {
        // Assertions for initial data validity
        assert claveUsuario != null && !claveUsuario.trim().isEmpty() : "Clave de usuario no puede ser nula o vacía.";
        assert nombre != null && !nombre.trim().isEmpty() : "Nombre no puede ser nulo o vacío.";
        assert licenciatura != null && !licenciatura.trim().isEmpty() : "Licenciatura no puede ser nula o vacía.";
        assert semestre > 0 : "El semestre debe ser un número positivo.";

        this.claveUsuario = claveUsuario;
        this.nombre = nombre;
        this.licenciatura = licenciatura;
        this.semestre = semestre;
    }

    // Getters
    public String getClaveUsuario() {
        return claveUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public int getSemestre() {
        return semestre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "claveUsuario='" + claveUsuario + "'" +
               ", nombre='" + nombre + "'" +
               ", licenciatura='" + licenciatura + "'" +
               ", semestre=" + semestre +
               '}';
    }
}
