package entidades;

import java.io.Serializable;

public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;
    private int cantidad;
    private String unidad;
    private String ubicacion;
    private boolean disponible;

    public Recurso(String tipo, int cantidad, String unidad, String ubicacion) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.disponible = true;
    }

    public String getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }
    public String getUbicacion() { return ubicacion; }
    public boolean isDisponible() { return disponible; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return String.format("%s: %d %s en %s [%s]", tipo, cantidad, unidad, ubicacion, disponible ? "Disponible" : "En uso");
    }


}
