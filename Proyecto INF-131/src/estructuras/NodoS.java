package estructuras;

public class NodoS<T> {
    public T dato;
    public NodoS<T> siguiente;

    public NodoS(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
