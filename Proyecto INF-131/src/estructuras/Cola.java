package estructuras;

public class Cola<T> {
    private NodoS<T> frente;
    private NodoS<T> fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(T dato) {
        NodoS<T> nuevo = new NodoS<>(dato);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public T desencolar() {
        if (frente == null) {
            return null;
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void mostrar() {
        NodoS<T> aux = frente;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }
}

