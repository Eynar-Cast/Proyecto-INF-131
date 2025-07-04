package persistencia;

import java.io.*;
import java.util.ArrayList;

public class GestorPersistencia {

    public static <T> void guardarDatos(String nombreArchivo, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cargarDatos(String nombreArchivo) {
        ArrayList<T> lista = new ArrayList<>();
        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                lista = (ArrayList<T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }

        return lista;
    }
}
