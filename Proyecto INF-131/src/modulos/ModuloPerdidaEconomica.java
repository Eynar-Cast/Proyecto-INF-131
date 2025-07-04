package modulos;

import entidades.PerdidaEconomica;
import estructuras.LSimple;
import estructuras.NodoS;
public class ModuloPerdidaEconomica {

    private LSimple<PerdidaEconomica> perdidas;

    public ModuloPerdidaEconomica() {
        perdidas = new LSimple<>();
    }

    public void registrarPerdida(PerdidaEconomica perdida) {
        perdidas.agregar(perdida);
    }

    public void mostrarPerdidas() {
        perdidas.mostrar();
    }

    public LSimple<PerdidaEconomica> getPerdidas() {
        return perdidas;
    }
    public double calcularTotalPerdido() {
    double total = 0;
    NodoS<PerdidaEconomica> aux = perdidas.getCabeza();
    while (aux != null) {
        total += aux.dato.getMontoEstimado();
        aux = aux.siguiente;
    }
    return total;
}

}
