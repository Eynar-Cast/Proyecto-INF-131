package entidades;

import java.io.Serializable;

public class PersonaAfectada implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;
    private String tipoAfectacion;
    private String necesidades;
    private String comunidad;
    private boolean atendido;

    public PersonaAfectada(String nombre, int edad, String tipoAfectacion, String necesidades, String comunidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoAfectacion = tipoAfectacion;
        this.necesidades = necesidades;
        this.comunidad = comunidad;
        this.atendido = false;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getTipoAfectacion() { return tipoAfectacion; }
    public String getNecesidades() { return necesidades; }
    public String getComunidad() { return comunidad; }
    public boolean isAtendido() { return atendido; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setTipoAfectacion(String tipoAfectacion) { this.tipoAfectacion = tipoAfectacion; }
    public void setNecesidades(String necesidades) { this.necesidades = necesidades; }
    public void setComunidad(String comunidad) { this.comunidad = comunidad; }
    public void setAtendido(boolean atendido) { this.atendido = atendido; }

    @Override
    public String toString() {
        return String.format("%s (%d años) - %s - %s [%s]", nombre, edad, tipoAfectacion, comunidad, atendido ? "Atendido" : "Pendiente");
    }
}