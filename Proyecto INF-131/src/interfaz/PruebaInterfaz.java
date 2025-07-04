package interfaz;

import entidades.*;
import modulos.*;
import estructuras.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PruebaInterfaz extends JFrame {

    private ModuloControlAfectados moduloAfectados;
    private ModuloPerdidaEconomica moduloPerdidas;
    private ModuloZonasAfectadas moduloZonas;
    private ModuloRecursos moduloRecursos;
    private ModuloAsignaciones moduloAsignaciones;

    
    private final String ARCHIVO_AFECTADOS = "data/afectados.dat";
    private final String ARCHIVO_PERDIDAS = "data/perdidas.dat";
    private final String ARCHIVO_ZONAS = "data/zonas.dat";
    private final String ARCHIVO_RECURSOS = "data/recursos.dat";


    public PruebaInterfaz() {
        setTitle("Sistema de Gestión de Incendios Forestales");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialización de módulos
        moduloAfectados = new ModuloControlAfectados();
        moduloPerdidas = new ModuloPerdidaEconomica();
        moduloZonas = new ModuloZonasAfectadas();
        moduloRecursos = new ModuloRecursos();
        
        moduloAsignaciones = new ModuloAsignaciones();

        // Cargar datos al iniciar
        cargarDatos();

        JTabbedPane pestañas = new JTabbedPane();

        pestañas.add("Afectados", crearPanelAfectados());

        pestañas.add("Recursos", crearPanelRecursos());

        pestañas.add("Asignar Recursos", crearPanelAsignarRecursos());

        pestañas.add("Pérdidas Económicas", crearPanelPerdidas());

        pestañas.add("Zonas Afectadas", crearPanelZonas());

        pestañas.add("Resumen General", crearPanelResumen());
        


        add(pestañas);

        // Guardar datos al cerrar
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                guardarDatos();
            }
        });
    }
    
    private void cargarDatos() {
        moduloAfectados.getAfectados().cargarLista(persistencia.GestorPersistencia.cargarDatos("data/afectados.dat"));
        moduloPerdidas.getPerdidas().cargarLista(persistencia.GestorPersistencia.cargarDatos("data/perdidas.dat"));
        moduloZonas.getZonas().cargarLista(persistencia.GestorPersistencia.cargarDatos("data/zonas.dat"));
        moduloRecursos.getRecursos().cargarLista(persistencia.GestorPersistencia.cargarDatos("data/recursos.dat"));
    }

    private void guardarDatos() {
        persistencia.GestorPersistencia.guardarDatos("data/afectados.dat", moduloAfectados.getAfectados().convertirALista());
        persistencia.GestorPersistencia.guardarDatos("data/perdidas.dat", moduloPerdidas.getPerdidas().convertirALista());
        persistencia.GestorPersistencia.guardarDatos("data/zonas.dat", moduloZonas.getZonas().convertirALista());
        persistencia.GestorPersistencia.guardarDatos("data/recursos.dat", moduloRecursos.getRecursos().convertirALista());
    }
    
    
    private javax.swing.table.DefaultTableModel getModeloAfectados() {
    String[] columnas = {"Nombre", "Edad", "Afectación", "Necesidad", "Comunidad"};
    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0);

    NodoD<PersonaAfectada> aux = moduloAfectados.getAfectados().getCabeza();
    while (aux != null) {
        PersonaAfectada p = aux.dato;
        Object[] fila = {p.getNombre(), p.getEdad(), p.getTipoAfectacion(), p.getNecesidades(), p.getComunidad()};
        modelo.addRow(fila);
        aux = aux.siguiente;
    }

    return modelo;
}

    
    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new BorderLayout());

        // Labels para mostrar los totales
        JLabel lblTotalAfectados = new JLabel("Total afectados: " + moduloAfectados.getAfectados().contarElementos());
        JLabel lblTotalPerdidas = new JLabel("Total pérdidas: " + moduloPerdidas.calcularTotalPerdido() + " Bs.");
        JLabel lblTotalHectareas = new JLabel("Total hectáreas: " + moduloZonas.calcularTotalHectareas() + " ha");

        // Botón para actualizar los valores
        JButton btnActualizar = new JButton("Actualizar Totales");
        btnActualizar.addActionListener(e -> {
            lblTotalAfectados.setText("Total afectados: " + moduloAfectados.getAfectados().contarElementos());
            lblTotalPerdidas.setText("Total pérdidas: " + moduloPerdidas.calcularTotalPerdido() + " Bs.");
            lblTotalHectareas.setText("Total hectáreas: " + moduloZonas.calcularTotalHectareas() + " ha");
        });

        // Panel de información
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


    private JPanel crearPanelAfectados() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField(15);
        JTextField txtEdad = new JTextField(5);
        JTextField txtAfectacion = new JTextField(15);
        JTextField txtNecesidad = new JTextField(15);
        JTextField txtComunidad = new JTextField(10);


        JButton btnAgregar = new JButton("Agregar Afectado");
        
        JTable tablaAfectados = new JTable(getModeloAfectados());
        JScrollPane scroll = new JScrollPane(tablaAfectados);

        
        //JTextArea area = new JTextArea();
        //area.setEditable(false);
        
        JLabel lblTotal = new JLabel("Total afectados: 0");


        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String afectacion = txtAfectacion.getText();
            String necesidad = txtNecesidad.getText();
            String comunidad = txtComunidad.getText();

            PersonaAfectada p = new PersonaAfectada(nombre, edad, afectacion, necesidad, comunidad);
            moduloAfectados.registrarAfectado(p);



//            area.append(p.toString() + "\n");
            
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
        //panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelPerdidas() {
        JPanel panel = new JPanel(new BorderLayout());

        //JTextField txtTipo = new JTextField(10);
        String[] categorias = {"Sembradíos", "Infraestructura", "Recursos", "Otros"};
        JComboBox<String> comboTipo = new JComboBox<>(categorias);

        JTextField txtMonto = new JTextField(7);
        JTextField txtPorcentaje = new JTextField(5);
        JTextField txtZona = new JTextField(10);

        JButton btnAgregar = new JButton("Agregar Pérdida");
        JTextArea area = new JTextArea();
        area.setEditable(false);
        
        JLabel lblTotalPerdidas = new JLabel("Total pérdidas: 0 Bs.");


        btnAgregar.addActionListener(e -> {
            //String tipo = txtTipo.getText();
            String tipo = comboTipo.getSelectedItem().toString();

            double monto = Double.parseDouble(txtMonto.getText());
            double porcentaje = Double.parseDouble(txtPorcentaje.getText());
            String zona = txtZona.getText();

            PerdidaEconomica p = new PerdidaEconomica(tipo, monto, porcentaje, zona);
            moduloPerdidas.registrarPerdida(p);

            area.append(p.toString() + "\n");
            
            lblTotalPerdidas.setText("Total pérdidas: " + moduloPerdidas.calcularTotalPerdido() + " Bs.");


            //txtTipo.setText("");
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
        formulario.add(lblTotalPerdidas);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelZonas() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField(10);

        JTextField txtRiesgo = new JTextField(10);
        JTextField txtArea = new JTextField(7);
        JTextField txtPoblacion = new JTextField(7);

        JButton btnAgregar = new JButton("Agregar Zona");
        JTextArea area = new JTextArea();
        area.setEditable(false);
        
        JLabel lblTotalHectareas = new JLabel("Total hectáreas: 0 ha");


        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();

            String riesgo = txtRiesgo.getText();
            double areaZona = Double.parseDouble(txtArea.getText());
            int poblacion = Integer.parseInt(txtPoblacion.getText());

            ZonaAfectada z = new ZonaAfectada(nombre,riesgo, areaZona, poblacion);
            moduloZonas.registrarZona(z);

            area.append(z.toString() + "\n");
            
            lblTotalHectareas.setText("Total hectáreas: " + moduloZonas.calcularTotalHectareas() + " ha");


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

        formulario.add(lblTotalHectareas);


        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelRecursos() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtTipo = new JTextField(10);
        JTextField txtCantidad = new JTextField(5);
        JTextField txtUnidad = new JTextField(5);
        JTextField txtUbicacion = new JTextField(10);

        JButton btnAgregar = new JButton("Agregar Recurso");
        JButton btnUsar = new JButton("Usar Recurso");
        JTextArea area = new JTextArea();
        area.setEditable(false);

        btnAgregar.addActionListener(e -> {
            String tipo = txtTipo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String unidad = txtUnidad.getText();
            String ubicacion = txtUbicacion.getText();

            Recurso r = new Recurso(tipo, cantidad, unidad, ubicacion);
            moduloRecursos.agregarRecurso(r);

            area.append("Agregado: " + r.toString() + "\n");

            txtTipo.setText("");
            txtCantidad.setText("");
            txtUnidad.setText("");
            txtUbicacion.setText("");
        });

        btnUsar.addActionListener(e -> {
            Recurso r = moduloRecursos.usarRecurso();
            if (r != null) {
                area.append("Usado: " + r.toString() + "\n");
            } else {
                area.append("No hay recursos disponibles.\n");
            }
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
        formulario.add(btnUsar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        return panel;
    }
    
    private JPanel crearPanelAsignarRecursos() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista desplegable de personas afectadas
        JComboBox<PersonaAfectada> comboAfectados = new JComboBox<>();
        NodoD<PersonaAfectada> auxAfectados = moduloAfectados.getAfectados().getCabeza();
        while (auxAfectados != null) {
            comboAfectados.addItem(auxAfectados.dato);
            auxAfectados = auxAfectados.siguiente;
        }

        // Lista desplegable de recursos
        JComboBox<Recurso> comboRecursos = new JComboBox<>();
        NodoS<Recurso> auxRecursos = moduloRecursos.getRecursos().getCima();
        while (auxRecursos != null) {
            comboRecursos.addItem(auxRecursos.dato);
            auxRecursos = auxRecursos.siguiente;
        }

        JButton btnAsignar = new JButton("Asignar Recurso");
        JTextArea areaAsignaciones = new JTextArea();
        areaAsignaciones.setEditable(false);

        btnAsignar.addActionListener(e -> {
            PersonaAfectada persona = (PersonaAfectada) comboAfectados.getSelectedItem();
            Recurso recurso = (Recurso) comboRecursos.getSelectedItem();

            if (persona != null && recurso != null) {
                moduloAsignaciones.asignarRecurso(persona, recurso, ABORT);
                areaAsignaciones.append("Recurso asignado: " + recurso.getTipo() + " a " + persona.getNombre() + "\n");
            }
        });

        JPanel formulario = new JPanel();
        formulario.add(new JLabel("Afectado:"));
        formulario.add(comboAfectados);
        formulario.add(new JLabel("Recurso:"));
        formulario.add(comboRecursos);
        formulario.add(btnAsignar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaAsignaciones), BorderLayout.CENTER);

        return panel;
    }

    
}
