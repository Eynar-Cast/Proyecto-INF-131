package modulos;

import entidades.PersonaAfectada;
import estructuras.LDoble;

public class ModuloControlAfectados {

    private LDoble<PersonaAfectada> afectados;

    public ModuloControlAfectados() {
        afectados = new LDoble<>();
    }

    public void registrarAfectado(PersonaAfectada persona) {
        afectados.agregar(persona);
    }

    public void mostrarAfectados() {
        afectados.mostrar();
    }

    public LDoble<PersonaAfectada> getAfectados() {
        return afectados;
    }
}
