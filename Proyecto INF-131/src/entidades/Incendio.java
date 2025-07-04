package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incendio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String ubicacion;
    private Date fecha;
    private String severidad;
    private double hectareasAfectadas;
    private String estado;

    public Incendio(String id, String ubicacion, String severidad, double hectareasAfectadas) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.fecha = new Date();
        this.severidad = severidad;
        this.hectareasAfectadas = hectareasAfectadas;
        this.estado = "Activo";
    }

    public String getId() { return id; }
    public String getUbicacion() { return ubicacion; }
    public Date getFecha() { return fecha; }
    public String getSeveridad() { return severidad; }
    public double getHectareasAfectadas() { return hectareasAfectadas; }
    public String getEstado() { return estado; }

    public void setId(String id) { this.id = id; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setSeveridad(String severidad) { this.severidad = severidad; }
    public void setHectareasAfectadas(double hectareasAfectadas) { this.hectareasAfectadas = hectareasAfectadas; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID: %s | %s | %s | %.2f ha | %s", id, ubicacion, sdf.format(fecha), hectareasAfectadas, severidad);
    }
}
