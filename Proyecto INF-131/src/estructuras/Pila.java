package estructuras;

public class Pila<T> {
    private NodoS<T> cima;

    public Pila() {
        this.cima = null;
    }

    public void apilar(T dato) {
        NodoS<T> nuevo = new NodoS<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T desapilar() {
        if (cima == null) {
            return null;
        }
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void mostrar() {
        NodoS<T> aux = cima;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }

    public NodoS<T> getCima() {
        return cima;
    }

    public void setCima(NodoS<T> cima) {
        this.cima = cima;
    }
    
    
    public java.util.ArrayList<T> convertirALista() {
        java.util.ArrayList<T> lista = new java.util.ArrayList<>();
        NodoS<T> aux = cima;
        while (aux != null) {
            lista.add(aux.dato);
            aux = aux.siguiente;
        }
        return lista;
    }
    
    public void cargarLista(java.util.ArrayList<T> lista) {
        for (T dato : lista) {
            apilar(dato);
        }
    }


}
