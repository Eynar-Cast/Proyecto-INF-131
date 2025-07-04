package estructuras;

public class NodoD<T> {
    public T dato;
    public NodoD<T> siguiente;
    public NodoD<T> anterior;

    public NodoD(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}
