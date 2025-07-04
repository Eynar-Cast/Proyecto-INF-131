package interfaz;

import entidades.*;
import modulos.*;
import estructuras.*;
import persistencia.GestorPersistencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfazSistemaIncendios extends JFrame {

    private ModuloControlAfectados moduloAfectados;
    private ModuloPerdidaEconomica moduloPerdidas;
    private ModuloZonasAfectadas moduloZonas;
    private ModuloRecursos moduloRecursos;
    private ModuloAsignaciones moduloAsignaciones;

    private JTable tablaAfectados;
    private JTable tablaRecursos;
    private JTable tablaPerdidas;
    private JTable tablaZonas;

    private final String ARCHIVO_AFECTADOS = "data/afectados.dat";
    private final String ARCHIVO_PERDIDAS = "data/perdidas.dat";
    private final String ARCHIVO_ZONAS = "data/zonas.dat";
    private final String ARCHIVO_RECURSOS = "data/recursos.dat";

    public InterfazSistemaIncendios() {
        setTitle("Sistema de Gestión de Incendios Forestales");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        moduloAfectados = new ModuloControlAfectados();
        moduloPerdidas = new ModuloPerdidaEconomica();
        moduloZonas = new ModuloZonasAfectadas();
        moduloRecursos = new ModuloRecursos();
        moduloAsignaciones = new ModuloAsignaciones();

        cargarDatos();

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.add("Afectados", crearPanelAfectados());
        pestañas.add("Recursos", crearPanelRecursos());
        pestañas.add("Asignar Recursos", crearPanelAsignarRecursos());
        pestañas.add("Pérdidas Económicas", crearPanelPerdidas());
        pestañas.add("Zonas Afectadas", crearPanelZonas());
        pestañas.add("Resumen General", crearPanelResumen());

        add(pestañas);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarDatos();
            }
        });
    }
    private void actualizarTablaRecursos() {
        tablaRecursos.setModel(getModeloRecursos());
    }


    private void actualizarComboRecursos(JComboBox<Recurso> combo) {
        combo.removeAllItems();
        NodoS<Recurso> aux = moduloRecursos.getRecursos().getCima();
        while (aux != null) {
            if (aux.dato.getCantidad() > 0) { // Solo mostrar recursos disponibles
                combo.addItem(aux.dato);
            }
            aux = aux.siguiente;
        }
    }


    private void cargarDatos() {
        moduloAfectados.getAfectados().cargarLista(GestorPersistencia.cargarDatos(ARCHIVO_AFECTADOS));
        moduloPerdidas.getPerdidas().cargarLista(GestorPersistencia.cargarDatos(ARCHIVO_PERDIDAS));
        moduloZonas.getZonas().cargarLista(GestorPersistencia.cargarDatos(ARCHIVO_ZONAS));
        moduloRecursos.getRecursos().cargarLista(GestorPersistencia.cargarDatos(ARCHIVO_RECURSOS));
    }

    private void guardarDatos() {
        GestorPersistencia.guardarDatos(ARCHIVO_AFECTADOS, moduloAfectados.getAfectados().convertirALista());
        GestorPersistencia.guardarDatos(ARCHIVO_PERDIDAS, moduloPerdidas.getPerdidas().convertirALista());
        GestorPersistencia.guardarDatos(ARCHIVO_ZONAS, moduloZonas.getZonas().convertirALista());
        GestorPersistencia.guardarDatos(ARCHIVO_RECURSOS, moduloRecursos.getRecursos().convertirALista());
    }
    private JPanel crearPanelAfectados() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField(10);
        JTextField txtEdad = new JTextField(5);
        JTextField txtAfectacion = new JTextField(10);
        JTextField txtNecesidad = new JTextField(10);
        JTextField txtComunidad = new JTextField(10);

        JButton btnAgregar = new JButton("Agregar Afectado");
        JLabel lblTotal = new JLabel("Total afectados: " + moduloAfectados.getAfectados().contarElementos());

        tablaAfectados = new JTable(getModeloAfectados());
        JScrollPane scroll = new JScrollPane(tablaAfectados);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String afectacion = txtAfectacion.getText();
            String necesidad = txtNecesidad.getText();
            String comunidad = txtComunidad.getText();

            PersonaAfectada p = new PersonaAfectada(nombre, edad, afectacion, necesidad, comunidad);
            moduloAfectados.registrarAfectado(p);

            tablaAfectados.setModel(getModeloAfectados());
            lblTotal.setText("Total afectados: " + moduloAfectados.getAfectados().contarElementos());

            txtNombre.setText("");
            txtEdad.setText("");
            txtAfectacion.setText("");
            txtNecesidad.setText("");
            txtComunidad.setText("");
        });

        JPanel formulario = new JPanel();
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Edad:"));
        formulario.add(txtEdad);
        formulario.add(new JLabel("Afectación:"));
        formulario.add(txtAfectacion);
        formulario.add(new JLabel("Necesidad:"));
        formulario.add(txtNecesidad);
        formulario.add(new JLabel("Comunidad:"));
        formulario.add(txtComunidad);
        formulario.add(btnAgregar);
        formulario.add(lblTotal);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private DefaultTableModel getModeloAfectados() {
        String[] columnas = {"Nombre", "Edad", "Afectación", "Necesidad", "Comunidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        NodoD<PersonaAfectada> aux = moduloAfectados.getAfectados().getCabeza();
        while (aux != null) {
            PersonaAfectada p = aux.dato;
            Object[] fila = {p.getNombre(), p.getEdad(), p.getTipoAfectacion(), p.getNecesidades(), p.getComunidad()};
            modelo.addRow(fila);
            aux = aux.siguiente;
        }

        return modelo;
    }
    private JPanel crearPanelRecursos() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtTipo = new JTextField(10);
        JTextField txtCantidad = new JTextField(5);
        JTextField txtUnidad = new JTextField(5);
        JTextField txtUbicacion = new JTextField(10);

        JButton btnAgregar = new JButton("Agregar Recurso");

        tablaRecursos = new JTable(getModeloRecursos());
        JScrollPane scroll = new JScrollPane(tablaRecursos);

        btnAgregar.addActionListener(e -> {
            String tipo = txtTipo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String unidad = txtUnidad.getText();
            String ubicacion = txtUbicacion.getText();

            Recurso r = new Recurso(tipo, cantidad, unidad, ubicacion);
            moduloRecursos.agregarRecurso(r);

            tablaRecursos.setModel(getModeloRecursos());

            txtTipo.setText("");
            txtCantidad.setText("");
            txtUnidad.setText("");
            txtUbicacion.setText("");
        });

        JPanel formulario = new JPanel();
        formulario.add(new JLabel("Tipo:"));
        formulario.add(txtTipo);
        formulario.add(new JLabel("Cantidad:"));
        formulario.add(txtCantidad);
        formulario.add(new JLabel("Unidad:"));
        formulario.add(txtUnidad);
        formulario.add(new JLabel("Ubicación:"));
        formulario.add(txtUbicacion);
        formulario.add(btnAgregar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private DefaultTableModel getModeloRecursos() {
        String[] columnas = {"Tipo", "Cantidad", "Unidad", "Ubicación"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        NodoS<Recurso> aux = moduloRecursos.getRecursos().getCima();
        while (aux != null) {
            Recurso r = aux.dato;
            Object[] fila = {r.getTipo(), r.getCantidad(), r.getUnidad(), r.getUbicacion()};
            modelo.addRow(fila);
            aux = aux.siguiente;
        }

        return modelo;
    }
    private JPanel crearPanelPerdidas() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] categorias = {"Sembradíos", "Infraestructura", "Recursos", "Otros"};
        JComboBox<String> comboTipo = new JComboBox<>(categorias);

        JTextField txtMonto = new JTextField(7);
        JTextField txtPorcentaje = new JTextField(5);
        JTextField txtZona = new JTextField(10);

        JButton btnAgregar = new JButton("Agregar Pérdida");

        tablaPerdidas = new JTable(getModeloPerdidas());
        JScrollPane scroll = new JScrollPane(tablaPerdidas);

        btnAgregar.addActionListener(e -> {
            String tipo = comboTipo.getSelectedItem().toString();
            double monto = Double.parseDouble(txtMonto.getText());
            double porcentaje = Double.parseDouble(txtPorcentaje.getText());
            String zona = txtZona.getText();

            PerdidaEconomica p = new PerdidaEconomica(tipo, monto, porcentaje, zona);
            moduloPerdidas.registrarPerdida(p);

            tablaPerdidas.setModel(getModeloPerdidas());

            txtMonto.setText("");
            txtPorcentaje.setText("");
            txtZona.setText("");
        });

        JPanel formulario = new JPanel();
        formulario.add(new JLabel("Categoría:"));
        formulario.add(comboTipo);
        formulario.add(new JLabel("Monto:"));
        formulario.add(txtMonto);
        formulario.add(new JLabel("% Pérdida:"));
        formulario.add(txtPorcentaje);
        formulario.add(new JLabel("Zona:"));
        formulario.add(txtZona);
        formulario.add(btnAgregar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
    private DefaultTableModel getModeloPerdidas() {
        String[] columnas = {"Categoría", "Monto", "% Pérdida", "Zona"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        NodoS<PerdidaEconomica> aux = moduloPerdidas.getPerdidas().getCabeza();
        while (aux != null) {
            PerdidaEconomica p = aux.dato;
            Object[] fila = {p.getTipoRecurso(), p.getMontoEstimado(), p.getPorcentajePerdida(), p.getZona()};
            modelo.addRow(fila);
            aux = aux.siguiente;
        }

        return modelo;
    }
    private JPanel crearPanelZonas() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField(10);
        JTextField txtRiesgo = new JTextField(10);
        JTextField txtArea = new JTextField(7);
        JTextField txtPoblacion = new JTextField(7);

        JButton btnAgregar = new JButton("Agregar Zona");

        tablaZonas = new JTable(getModeloZonas());
        JScrollPane scroll = new JScrollPane(tablaZonas);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String riesgo = txtRiesgo.getText();
            double areaZona = Double.parseDouble(txtArea.getText());
            int poblacion = Integer.parseInt(txtPoblacion.getText());

            ZonaAfectada z = new ZonaAfectada(nombre, riesgo, areaZona, poblacion);
            moduloZonas.registrarZona(z);

            tablaZonas.setModel(getModeloZonas());

            txtNombre.setText("");
            txtRiesgo.setText("");
            txtArea.setText("");
            txtPoblacion.setText("");
        });

        JPanel formulario = new JPanel();
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Riesgo:"));
        formulario.add(txtRiesgo);
        formulario.add(new JLabel("Área:"));
        formulario.add(txtArea);
        formulario.add(new JLabel("Población:"));
        formulario.add(txtPoblacion);
        formulario.add(btnAgregar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private DefaultTableModel getModeloZonas() {
        String[] columnas = {"Nombre", "Riesgo", "Área", "Población"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        NodoS<ZonaAfectada> aux = moduloZonas.getZonas().getCabeza();
        while (aux != null) {
            ZonaAfectada z = aux.dato;
            Object[] fila = {z.getNombre(), z.getNivelRiesgo(), z.getAreaTotal(), z.getPoblacionEstimada()};
            modelo.addRow(fila);
            aux = aux.siguiente;
        }

        return modelo;
    }
   private JPanel crearPanelAsignarRecursos() {
    JPanel panel = new JPanel(new BorderLayout());

    // ComboBoxes para seleccionar Afectado y Recurso
    JComboBox<PersonaAfectada> comboAfectados = new JComboBox<>();
    JComboBox<Recurso> comboRecursos = new JComboBox<>();

    // Área donde se mostrará la última asignación realizada
    JTextArea areaAsignaciones = new JTextArea(5, 30);
    areaAsignaciones.setEditable(false);

    // Área donde se mostrará la lista de todas las asignaciones realizadas
    JTextArea areaAsignados = new JTextArea(10, 30);
    areaAsignados.setEditable(false);

    // Botón para actualizar las listas (ComboBoxes)
    JButton btnActualizarListas = new JButton("Actualizar Listas");
    btnActualizarListas.addActionListener(e -> {
        comboAfectados.removeAllItems();
        NodoD<PersonaAfectada> auxAfectados = moduloAfectados.getAfectados().getCabeza();
        while (auxAfectados != null) {
            comboAfectados.addItem(auxAfectados.dato);
            auxAfectados = auxAfectados.siguiente;
        }

        actualizarComboRecursos(comboRecursos);
    });

    // Botón para asignar recurso
    JButton btnAsignar = new JButton("Asignar Recurso");
    btnAsignar.addActionListener(e -> {
        PersonaAfectada persona = (PersonaAfectada) comboAfectados.getSelectedItem();
        Recurso recurso = (Recurso) comboRecursos.getSelectedItem();

        if (persona != null && recurso != null) {

            if (moduloAsignaciones.yaAsignado(persona, recurso)) {
                JOptionPane.showMessageDialog(null, "Este recurso ya fue asignado a esta persona.");
                return;
            }

            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a asignar:");
            if (cantidadStr == null) return; // Si se cancela

            int cantidadAsignar = Integer.parseInt(cantidadStr);

            if (cantidadAsignar <= 0 || cantidadAsignar > recurso.getCantidad()) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida o insuficiente.");
                return;
            }

            recurso.setCantidad(recurso.getCantidad() - cantidadAsignar);
            if (recurso.getCantidad() == 0) {
                recurso.setDisponible(false);
            }

            moduloAsignaciones.asignarRecurso(persona, recurso, cantidadAsignar);

            areaAsignaciones.append("Recurso asignado: " + recurso.getTipo() + " (" + cantidadAsignar + " " + recurso.getUnidad() + ") a " + persona.getNombre() + "\n");

            actualizarComboRecursos(comboRecursos);
            actualizarTablaRecursos();
        }
    });

    // Botón para ver todas las asignaciones realizadas
    JButton btnVerAsignaciones = new JButton("Ver Asignaciones");
    btnVerAsignaciones.addActionListener(e -> {
        areaAsignados.setText("");
        NodoS<AsignacionRecurso> aux = moduloAsignaciones.getAsignaciones().getCabeza();
        while (aux != null) {
            areaAsignados.append(aux.dato.toString() + "\n");
            aux = aux.siguiente;
        }
    });

    // Formulario
    JPanel formulario = new JPanel();
    formulario.add(new JLabel("Afectado:"));
    formulario.add(comboAfectados);
    formulario.add(new JLabel("Recurso:"));
    formulario.add(comboRecursos);
    formulario.add(btnAsignar);
    formulario.add(btnActualizarListas);
    formulario.add(btnVerAsignaciones);

    panel.add(formulario, BorderLayout.NORTH);
    panel.add(new JScrollPane(areaAsignaciones), BorderLayout.CENTER);
    panel.add(new JScrollPane(areaAsignados), BorderLayout.SOUTH);

    return panel;
}

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblTotalAfectados = new JLabel("Total afectados: " + moduloAfectados.getAfectados().contarElementos());
        JLabel lblTotalPerdidas = new JLabel("Total pérdidas: " + moduloPerdidas.calcularTotalPerdido() + " Bs.");
        JLabel lblTotalHectareas = new JLabel("Total hectáreas: " + moduloZonas.calcularTotalHectareas() + " ha");
        JButton btnActualizar = new JButton("Actualizar Totales");
        btnActualizar.addActionListener(e -> {
            lblTotalAfectados.setText("Total afectados: " + moduloAfectados.getAfectados().contarElementos());
            lblTotalPerdidas.setText("Total pérdidas: " + moduloPerdidas.calcularTotalPerdido() + " Bs.");
            lblTotalHectareas.setText("Total hectáreas: " + moduloZonas.calcularTotalHectareas() + " ha");
        });
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(lblTotalAfectados);
        info.add(lblTotalPerdidas);
        info.add(lblTotalHectareas);
        info.add(Box.createRigidArea(new Dimension(0, 20)));
        info.add(btnActualizar);
        panel.add(info, BorderLayout.CENTER);
        return panel;
    }
}
