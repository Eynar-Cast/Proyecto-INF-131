package entidades;

import java.io.Serializable;
import java.util.Date;

public class PerdidaEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoRecurso;
    private double montoEstimado;
    private double porcentajePerdida;
    private String zona;
    private Date fechaEvaluacion;

    public PerdidaEconomica(String tipoRecurso, double montoEstimado, double porcentajePerdida, String zona) {
        this.tipoRecurso = tipoRecurso;
        this.montoEstimado = montoEstimado;
        this.porcentajePerdida = porcentajePerdida;
        this.zona = zona;
        this.fechaEvaluacion = new Date();
    }

    public String getTipoRecurso() { return tipoRecurso; }
    public double getMontoEstimado() { return montoEstimado; }
    public double getPorcentajePerdida() { return porcentajePerdida; }
    public String getZona() { return zona; }
    public Date getFechaEvaluacion() { return fechaEvaluacion; }

    public void setTipoRecurso(String tipoRecurso) { this.tipoRecurso = tipoRecurso; }
    public void setMontoEstimado(double montoEstimado) { this.montoEstimado = montoEstimado; }
    public void setPorcentajePerdida(double porcentajePerdida) { this.porcentajePerdida = porcentajePerdida; }
    public void setZona(String zona) { this.zona = zona; }

    @Override
    public String toString() {
        return String.format("%s en %s: $%.2f (%.1f%% pérdida)", tipoRecurso, zona, montoEstimado, porcentajePerdida);
    }
}