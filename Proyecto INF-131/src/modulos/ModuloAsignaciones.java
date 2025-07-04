package modulos;

import entidades.AsignacionRecurso;
import entidades.PersonaAfectada;
import entidades.Recurso;
import estructuras.LSimple;
import estructuras.NodoS;

public class ModuloAsignaciones {
    private LSimple<AsignacionRecurso> asignaciones;

    public ModuloAsignaciones() {
        asignaciones = new LSimple<>();
    }

    public void asignarRecurso(PersonaAfectada persona, Recurso recurso, int cantidadAsignada) {
        AsignacionRecurso asignacion = new AsignacionRecurso(persona, recurso, cantidadAsignada);
        asignaciones.agregar(asignacion);
    }

    public void mostrarAsignaciones() {
        asignaciones.mostrar();
    }
    
    public LSimple<AsignacionRecurso> getAsignaciones() {
        return asignaciones;
    }
    public boolean yaAsignado(PersonaAfectada persona, Recurso recurso) {
        NodoS<AsignacionRecurso> aux = asignaciones.getCabeza();
        while (aux != null) {
            if (aux.dato.getPersona().equals(persona) && aux.dato.getRecurso().equals(recurso)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

}
