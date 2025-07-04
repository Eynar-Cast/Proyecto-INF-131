package estructuras;

public class LDoble<T> {
    private NodoD<T> cabeza;

    public LDoble() {
        this.cabeza = null;
    }

    public NodoD<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoD<T> cabeza) {
        this.cabeza = cabeza;
    }
    

    public void agregar(T dato) {
        NodoD<T> nuevo = new NodoD<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoD<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
            nuevo.anterior = aux;
        }
    }

    public void mostrar() {
        NodoD<T> aux = cabeza;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
        }
        public int contarElementos() {
        int contador = 0;
        NodoD<T> aux = cabeza;
        while (aux != null) {
            contador++;
            aux = aux.siguiente;
        }
        return contador;
    }
    
        
    public java.util.ArrayList<T> convertirALista() {
        java.util.ArrayList<T> lista = new java.util.ArrayList<>();
        NodoD<T> aux = cabeza;
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
