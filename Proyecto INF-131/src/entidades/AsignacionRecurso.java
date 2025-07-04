package entidades;

import java.io.Serializable;

public class AsignacionRecurso implements Serializable {
    private static final long serialVersionUID = 1L;

    private PersonaAfectada persona;
    private Recurso recurso;
    private int cantidadAsignada;
    
    public AsignacionRecurso(PersonaAfectada persona, Recurso recurso, int cantidadAsignada) {
        this.persona = persona;
        this.recurso = recurso;
        this.cantidadAsignada = cantidadAsignada;
    }

    public AsignacionRecurso(PersonaAfectada persona, Recurso recurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public PersonaAfectada getPersona() { return persona; }
    public Recurso getRecurso() { return recurso; }
    
    @Override
    public String toString() {
        return "Recurso: " + recurso.getTipo() + " (" + cantidadAsignada + " " + recurso.getUnidad() + ") asignado a " + persona.getNombre();
    }
    
    public int getCantidadAsignada() {
        return cantidadAsignada;
    }

}
