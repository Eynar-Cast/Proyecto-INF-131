package modulos;

import entidades.ZonaAfectada;
import entidades.Incendio;
import estructuras.LSimple;
import estructuras.NodoS;
        
public class ModuloZonasAfectadas {

    private LSimple<ZonaAfectada> zonas;
    private LSimple<Incendio> incendios;

    public ModuloZonasAfectadas() {
        zonas = new LSimple<>();
        incendios = new LSimple<>();
    }

    public void registrarZona(ZonaAfectada zona) {
        zonas.agregar(zona);
    }

    public void registrarIncendio(Incendio incendio) {
        incendios.agregar(incendio);
    }

    public void mostrarZonas() {
        zonas.mostrar();
    }

    public void mostrarIncendios() {
        incendios.mostrar();
    }

    public LSimple<ZonaAfectada> getZonas() {
        return zonas;
    }

    public LSimple<Incendio> getIncendios() {
        return incendios;
    }
    
    public double calcularTotalHectareas() {
        double total = 0;
        NodoS<ZonaAfectada> aux = zonas.getCabeza();
        while (aux != null) {
            total += aux.dato.getAreaTotal();
            aux = aux.siguiente;
        }
        return total;
    }

}
