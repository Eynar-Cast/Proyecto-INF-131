package modulos;

import entidades.Recurso;
import estructuras.Pila;

public class ModuloRecursos {

    private Pila<Recurso> recursos;

    public ModuloRecursos() {
        recursos = new Pila<>();
    }

    public void agregarRecurso(Recurso recurso) {
        recursos.apilar(recurso);
    }

    public void mostrarRecursos() {
        recursos.mostrar();
    }

    public Recurso usarRecurso() {
        return recursos.desapilar();
    }

    public Pila<Recurso> getRecursos() {
        return recursos;
    }
    
}
