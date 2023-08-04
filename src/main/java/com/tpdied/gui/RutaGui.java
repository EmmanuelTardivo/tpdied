package com.tpdied.gui;

import com.tpdied.controllers.RutaController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.forms.RutaForm;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RutaGui implements Tab{

    public RutaGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Ruta";
    }

    public JPanel getTab(){
        return jRutaTab;
    }

    private void initComponents() {
        jRutaTab = new javax.swing.JPanel();
        lblIDRuta = new javax.swing.JLabel();
        lblSucursalOrigen = new javax.swing.JLabel();
        lblSucursalDestino = new javax.swing.JLabel();
        lblTiempoTransito = new javax.swing.JLabel();
        lblCapacidadMaxima = new javax.swing.JLabel();
        btCrearRuta = new javax.swing.JButton();
        btBuscarRuta = new javax.swing.JButton();
        btModificarRuta = new javax.swing.JButton();
        btEliminarRuta = new javax.swing.JButton();
        spRuta = new javax.swing.JScrollPane();
        tlRuta = new javax.swing.JTable();
        btLimpiarRuta = new javax.swing.JButton();
        cbSucursalOrigen = new JComboBox<SucursalDTO>();
        cbSucursalDestino = new JComboBox<SucursalDTO>();
        lblEstadoRuta = new javax.swing.JLabel();
        cbEstadoRuta = new javax.swing.JCheckBox();
        ftfCapMaxima = new javax.swing.JFormattedTextField();
        ftfTiempoTransito = new javax.swing.JFormattedTextField();
        ftfIDRuta = new javax.swing.JFormattedTextField();

        jRutaTab.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jRutaTab.setMaximumSize(new java.awt.Dimension(704, 430));
        jRutaTab.setMinimumSize(new java.awt.Dimension(704, 430));
        jRutaTab.setPreferredSize(new java.awt.Dimension(704, 430));

        lblIDRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblIDRuta.setText("ID:");

        lblSucursalOrigen.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblSucursalOrigen.setText("Suc. Origen:");

        lblSucursalDestino.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblSucursalDestino.setText("Suc. Destino:");

        lblTiempoTransito.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblTiempoTransito.setText("Tiempo Transito:");

        lblCapacidadMaxima.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblCapacidadMaxima.setText("Cap. Máxima:");

        btCrearRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCrearRuta.setText("Crear");
        btCrearRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearRutaActionPerformed(evt);
            }
        });

        btBuscarRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btBuscarRuta.setText("Buscar");
        btBuscarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarRutaActionPerformed(evt);
            }
        });

        btModificarRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btModificarRuta.setText("Modificar");
        btModificarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarRutaActionPerformed(evt);
            }
        });

        btEliminarRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btEliminarRuta.setText("Eliminar");
        btEliminarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarRutaActionPerformed(evt);
            }
        });

        spRuta.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spRuta.setPreferredSize(new java.awt.Dimension(600, 400));
        sucursales.forEach(s -> {
            cbSucursalOrigen.addItem(s);
            cbSucursalDestino.addItem(s);
        });
        cbSucursalOrigen.setSelectedIndex(-1);
        cbSucursalDestino.setSelectedIndex(-1);
        getTableRuta(rutas);
        spRuta.setViewportView(tlRuta);


        btLimpiarRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarRuta.setText("Limpiar");
        btLimpiarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarRutaActionPerformed(evt);
            }
        });

        cbSucursalOrigen.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbSucursalOrigen.setToolTipText("");

        cbSucursalDestino.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbSucursalDestino.setToolTipText("");


        lblEstadoRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblEstadoRuta.setText("Operativa:");

        ftfCapMaxima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        ftfCapMaxima.setText("0");
        ftfCapMaxima.setToolTipText("En Kilogramos");
        ftfCapMaxima.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        ftfTiempoTransito.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfTiempoTransito.setText("00:00");
        ftfTiempoTransito.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        ftfIDRuta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfIDRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        javax.swing.GroupLayout jRutaTabLayout = new javax.swing.GroupLayout(jRutaTab);
        jRutaTab.setLayout(jRutaTabLayout);
        jRutaTabLayout.setHorizontalGroup(
                jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblSucursalOrigen)
                                                        .addComponent(lblIDRuta)
                                                        .addComponent(lblSucursalDestino)
                                                        .addComponent(lblTiempoTransito))
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cbEstadoRuta)
                                                                        .addComponent(cbSucursalOrigen, 0, 97, Short.MAX_VALUE)
                                                                        .addComponent(ftfIDRuta)))
                                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(ftfTiempoTransito)
                                                                        .addComponent(cbSucursalDestino, 0, 96, Short.MAX_VALUE)))))
                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblEstadoRuta)
                                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btCrearRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                                                .addGap(1, 1, 1)
                                                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(btLimpiarRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(btModificarRuta))))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(btBuscarRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btEliminarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                .addComponent(lblCapacidadMaxima)
                                                .addGap(37, 37, 37)
                                                .addComponent(ftfCapMaxima)))
                                .addGap(18, 18, 18)
                                .addComponent(spRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))
        );
        jRutaTabLayout.setVerticalGroup(
                jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jRutaTabLayout.createSequentialGroup()
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblIDRuta)
                                                        .addComponent(ftfIDRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblSucursalOrigen)
                                                        .addComponent(cbSucursalOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblSucursalDestino)
                                                        .addComponent(cbSucursalDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblTiempoTransito)
                                                        .addComponent(ftfTiempoTransito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblCapacidadMaxima)
                                                        .addComponent(ftfCapMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblEstadoRuta)
                                                        .addComponent(cbEstadoRuta))
                                                .addGap(40, 40, 40)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btCrearRuta)
                                                        .addComponent(btBuscarRuta))
                                                .addGap(18, 18, 18)
                                                .addGroup(jRutaTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btModificarRuta)
                                                        .addComponent(btEliminarRuta))
                                                .addGap(18, 18, 18)
                                                .addComponent(btLimpiarRuta))
                                        .addComponent(spRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(59, Short.MAX_VALUE))
        );
    }

    /**
     * Ruta CRUD Eventos y Auxiliares
     */
    private void btCrearRutaActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbSucursalOrigen.getSelectedIndex() == -1 || cbSucursalDestino.getSelectedIndex() == -1){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una sucursal de origen y destino.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SucursalDTO dtoOrigen = (SucursalDTO) cbSucursalOrigen.getSelectedItem();
            SucursalDTO dtoDestino = (SucursalDTO) cbSucursalDestino.getSelectedItem();
            RutaDTO dto = RutaForm.validarRuta(ftfCapMaxima.getText(), dtoOrigen, dtoDestino, ftfTiempoTransito.getText(), cbEstadoRuta.isSelected());
            rutaController.addRuta(dto);
            javax.swing.JOptionPane.showMessageDialog(null, "Ruta creada con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateRutaTable();
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btBuscarRutaActionPerformed(java.awt.event.ActionEvent evt) {
        if(!ftfIDRuta.getText().isBlank()){
            List<RutaDTO> aux = new ArrayList<RutaDTO>();
            aux.add(rutaController.getRutaById(Integer.parseInt(ftfIDRuta.getText())));
            getTableRuta(aux);
            return;
        }
        if (cbSucursalOrigen.getSelectedIndex() != -1){
            getTableRuta(rutaController.getRutasBySucursalOrigen((SucursalDTO) cbSucursalOrigen.getSelectedItem()));
            return;
        }

        if(!ftfTiempoTransito.getText().isBlank() && !ftfTiempoTransito.getText().equals("00:00")){
            String[] partes = ftfTiempoTransito.getText().split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            Duration.ofHours(horas).plusMinutes(minutos);

            getTableRuta(rutaController.getRutasByDuracionViaje(Duration.ofHours(horas).plusMinutes(minutos)));
            return;
        }

        if (!ftfCapMaxima.getText().isBlank() && !ftfCapMaxima.getText().equals("0")){
            getTableRuta(rutaController.getRutasByCapacidadEnKilos(Double.parseDouble(ftfCapMaxima.getText())));
            return;
        }

        if (cbEstadoRuta.isSelected()){
            getTableRuta(rutaController.getRutasByEstado(cbEstadoRuta.isSelected()));
            return;
        }

        getTableRuta(rutas);
    }

    private void btModificarRutaActionPerformed(java.awt.event.ActionEvent evt) {
        if (tlRuta.getSelectionModel().isSelectionEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta de la tabla para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            RutaDTO dto = RutaForm.validarRuta(ftfCapMaxima.getText(), (SucursalDTO) cbSucursalOrigen.getSelectedItem(),
                    (SucursalDTO) cbSucursalDestino.getSelectedItem(), ftfTiempoTransito.getText(), cbEstadoRuta.isSelected());
            dto.setId(Integer.parseInt(ftfIDRuta.getText()));
            rutaController.updateRuta(dto);
            javax.swing.JOptionPane.showMessageDialog(null, "Ruta modificada con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateRutaTable();
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btEliminarRutaActionPerformed(java.awt.event.ActionEvent evt) {
        if (tlRuta.getSelectionModel().isSelectionEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta de la tabla para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(tlRuta.getValueAt(tlRuta.getSelectedRow(), 0).toString());
            RutaDTO dto = rutas
                    .stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElseThrow();
            rutaController.deleteRuta(dto);
            javax.swing.JOptionPane.showMessageDialog(null, "Ruta eliminada con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateRutaTable();
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btLimpiarRutaActionPerformed(java.awt.event.ActionEvent evt) {
        ftfIDRuta.setText("");
        ftfTiempoTransito.setText("00:00");
        ftfCapMaxima.setText("0");
        cbEstadoRuta.setSelected(false);
        cbSucursalOrigen.removeAllItems();
        cbSucursalDestino.removeAllItems();
        sucursales.forEach(s -> {
            cbSucursalOrigen.addItem(s);
            cbSucursalDestino.addItem(s);
        });
        cbSucursalOrigen.setSelectedIndex(-1);
        cbSucursalDestino.setSelectedIndex(-1);
        updateRutaTable();
    }

    private void getTableRuta(List<RutaDTO> listaDTO) {

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        String[] columnas = {"ID", "S. Origen", "S. Destino", "Tiempo", "Cap. Maxima", "Operativa"};
        String[] data = new String[columnas.length];
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        if (!(listaDTO == null)) {
            try {
                for (RutaDTO dto : listaDTO) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getSucursalOrigen().getNombre();
                    data[2] = dto.getSucursalDestino().getNombre();
                    data[3] = dto.formatDuration();
                    data[4] = dto.getCapacidadEnKilos().toString();
                    data[5] = dto.getEstado() ? "Si" : "No";
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlRuta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlRuta.setModel(modelo);
        tlRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlRuta.setShowGrid(true);
        tlRuta.getTableHeader().setReorderingAllowed(false);
        tlRuta.getTableHeader().setResizingAllowed(true);
        tlRuta.setAutoCreateRowSorter(true);
        tlRuta.setFillsViewportHeight(true);

        int i = tlRuta.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlRuta.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }

        tlRuta.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (tlRuta.getSelectionModel().isSelectionEmpty())
                    return;

                int row = tlRuta.getSelectedRow();
                ftfIDRuta.setText(tlRuta.getValueAt(row, 0).toString());
                cbSucursalOrigen.setSelectedItem(
                        sucursales
                                .stream()
                                .filter(s -> s.getNombre().equals(tlRuta.getValueAt(row, 1)))
                                .findFirst()
                                .orElseThrow(null));
                cbSucursalDestino.setSelectedItem(
                        sucursales
                                .stream()
                                .filter(s -> s.getNombre().equals(tlRuta.getValueAt(row, 2)))
                                .findFirst()
                                .orElseThrow(null));
                ftfTiempoTransito.setText(tlRuta.getValueAt(row, 3).toString());
                ftfCapMaxima.setText(tlRuta.getValueAt(row, 4).toString());
                cbEstadoRuta.setSelected(tlRuta.getValueAt(row, 5).toString().equalsIgnoreCase("Si"));
            }});
    }

    private void updateRutaTable() {
        tlRuta.getSelectionModel().clearSelection();
        rutas = rutaController.getAllRutas();
        getTableRuta(rutas);
    }

    private javax.swing.JButton btBuscarRuta;
    private javax.swing.JButton btCrearRuta;
    private javax.swing.JButton btEliminarRuta;
    private javax.swing.JButton btLimpiarRuta;
    private javax.swing.JButton btModificarRuta;
    private javax.swing.JCheckBox cbEstadoRuta;
    private JComboBox<SucursalDTO> cbSucursalDestino;
    private JComboBox<SucursalDTO> cbSucursalOrigen;
    private javax.swing.JFormattedTextField ftfCapMaxima;
    private javax.swing.JFormattedTextField ftfIDRuta;
    private javax.swing.JFormattedTextField ftfTiempoTransito;
    private javax.swing.JPanel jRutaTab;
    private javax.swing.JLabel lblCapacidadMaxima;
    private javax.swing.JLabel lblEstadoRuta;
    private javax.swing.JLabel lblIDRuta;
    private javax.swing.JLabel lblSucursalDestino;
    private javax.swing.JLabel lblSucursalOrigen;
    private javax.swing.JLabel lblTiempoTransito;
    private javax.swing.JScrollPane spRuta;
    private javax.swing.JTable tlRuta;

    private RutaController rutaController = new RutaController(EntityManagerUtil.getEntityManager());
    private List<RutaDTO> rutas = rutaController.getAllRutas();


    private final SucursalController sucursalController = new SucursalController(EntityManagerUtil.getEntityManager());
    private List<SucursalDTO> sucursales = sucursalController.getAllSucursales();
}
