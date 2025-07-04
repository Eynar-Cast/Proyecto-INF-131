package entidades;
import java.io.Serializable;

public class ZonaAfectada implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;

    private String nivelRiesgo;
    private double areaTotal;
    private int poblacionEstimada;

    public ZonaAfectada(String nombre, String nivelRiesgo, double areaTotal, int poblacionEstimada) {
        this.nombre = nombre;

        this.nivelRiesgo = nivelRiesgo;
        this.areaTotal = areaTotal;
        this.poblacionEstimada = poblacionEstimada;
    }

    public String getNombre() { return nombre; }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public double getAreaTotal() { return areaTotal; }
    public int getPoblacionEstimada() { return poblacionEstimada; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public void setAreaTotal(double areaTotal) { this.areaTotal = areaTotal; }
    public void setPoblacionEstimada(int poblacionEstimada) { this.poblacionEstimada = poblacionEstimada; }

    @Override
    public String toString() {
        return String.format("%s (%.4f, %.4f) - Riesgo: %s - %.2f km² - %d hab.", nombre,  nivelRiesgo, areaTotal, poblacionEstimada);
    }
}