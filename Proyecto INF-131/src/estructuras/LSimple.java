package estructuras;

public class LSimple<T> {
    private NodoS<T> cabeza;

    public LSimple() {
        this.cabeza = null;
    }

    public void agregar(T dato) {
        NodoS<T> nuevo = new NodoS<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoS<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    public void mostrar() {
        NodoS<T> aux = cabeza;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public NodoS<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoS<T> cabeza) {
        this.cabeza = cabeza;
    }
    
    public java.util.ArrayList<T> convertirALista() {
        java.util.ArrayList<T> lista = new java.util.ArrayList<>();
        NodoS<T> aux = cabeza;
        while (aux != null) {
            lista.add(aux.dato);
            aux = aux.siguiente;
        }
        return lista;
    }
    
    public void cargarLista(java.util.ArrayList<T> lista) {
        for (T dato : lista) {
            agregar(dato);
        }
    }

}
