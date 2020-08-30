package CompiladorHTML;

public class Token {

    public String descripcion;
    public String contenido;

    public Token(String descripcion, String contenido) {
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "\n" + descripcion + ", contenido= " + contenido;
    }

}
