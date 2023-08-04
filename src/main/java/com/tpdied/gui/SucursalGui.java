package com.tpdied.gui;

import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.forms.SucursalForm;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SucursalGui implements Tab {

    public SucursalGui() {
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Sucursal";
    }

    public JPanel getTab() {
        return jSucursalTab;
    }

    private void initComponents() {
        jSucursalTab = new javax.swing.JPanel();
        JLabel lblIDSucursal = new JLabel();
        tfNombreSucursal = new javax.swing.JTextField();
        JLabel lblNombreSucursal = new JLabel();
        JLabel lblHoraAperturaSucursal = new JLabel();
        JLabel lblHoraCierreSucursal = new JLabel();
        JLabel lblEstadoSucursal = new JLabel();
        cbEstadoSucursal = new javax.swing.JCheckBox();
        JButton btCrearSucursal = new JButton();
        JButton btBuscarSucursal = new JButton();
        JButton btModificarSucursal = new JButton();
        JButton btEliminarSucursal = new JButton();
        JButton btLimpiarSucursal = new JButton();
        ftfHoraApertura = new javax.swing.JFormattedTextField();
        ftfHoraCierre = new javax.swing.JFormattedTextField();
        JScrollPane spSucursal = new JScrollPane();
        tlSucursal = new javax.swing.JTable();
        ftfIDSucursal = new javax.swing.JFormattedTextField();

        jSucursalTab.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jSucursalTab.setMaximumSize(new java.awt.Dimension(704, 430));
        jSucursalTab.setMinimumSize(new java.awt.Dimension(704, 430));
        jSucursalTab.setName(""); // NOI18N
        jSucursalTab.setPreferredSize(new java.awt.Dimension(704, 430));

        lblIDSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblIDSucursal.setText("ID:");

        tfNombreSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        tfNombreSucursal.setMaximumSize(new java.awt.Dimension(64, 23));

        lblNombreSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblNombreSucursal.setText("Nombre:");

        lblHoraAperturaSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblHoraAperturaSucursal.setText("Hora Apertura:");

        lblHoraCierreSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblHoraCierreSucursal.setText("Hora Cierre:");

        lblEstadoSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblEstadoSucursal.setText("Operativo:");

        btCrearSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCrearSucursal.setText("Crear");
        btCrearSucursal.addActionListener(evt -> {
            try {
                SucursalDTO dto = SucursalForm.validarSucursal(tfNombreSucursal.getText(),
                        ftfHoraApertura.getText(),
                        ftfHoraCierre.getText(), cbEstadoSucursal.isSelected());
                sucursalController.addSucursal(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Sucursal creada con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateSucursalTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btBuscarSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btBuscarSucursal.setText("Buscar");
        btBuscarSucursal.addActionListener(evt -> {
            if (!ftfIDSucursal.getText().isBlank()) {
                List<SucursalDTO> aux = new ArrayList<>();
                aux.add(sucursalController.getSucursalById(Integer.parseInt(ftfIDSucursal.getText())));
                getTableSucursal(aux);
                return;
            }
            if (!tfNombreSucursal.getText().isBlank()) {
                getTableSucursal(sucursalController.getSucursalesByName(tfNombreSucursal.getText()));
                return;
            }
            if (!ftfHoraApertura.getText().isBlank() && !ftfHoraApertura.getText().equals("00:00")) {
                getTableSucursal(sucursalController.getSucursalesByHorarioApertura(LocalTime.parse(ftfHoraApertura.getText())));
                return;
            }
            if (!ftfHoraCierre.getText().isBlank() && !ftfHoraCierre.getText().equals("00:00")) {
                getTableSucursal(sucursalController.getSucursalesByHorarioCierre(LocalTime.parse(ftfHoraCierre.getText())));
                return;
            }
            if (cbEstadoSucursal.isSelected()) {
                getTableSucursal(sucursalController.getSucursalesByEstado(cbEstadoSucursal.isSelected()));
                return;
            }

            getTableSucursal(sucursales);
        });

        btModificarSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btModificarSucursal.setText("Modificar");
        btModificarSucursal.addActionListener(evt -> {
            if (tlSucursal.getSelectionModel().isSelectionEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una sucursal de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                SucursalDTO dto = SucursalForm.validarSucursal(tfNombreSucursal.getText(), ftfHoraApertura.getText(), ftfHoraCierre.getText(), cbEstadoSucursal.isSelected());
                dto.setId(Integer.parseInt(ftfIDSucursal.getText()));
                sucursalController.updateSucursal(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Sucursal modificada con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateSucursalTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btEliminarSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btEliminarSucursal.setText("Eliminar");
        btEliminarSucursal.addActionListener(evt -> {
            if (tlSucursal.getSelectionModel().isSelectionEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una sucursal de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(tlSucursal.getValueAt(tlSucursal.getSelectedRow(), 0).toString());
                SucursalDTO dto = sucursales
                        .stream()
                        .filter(s -> s.getId().equals(id))
                        .findFirst().get();
                sucursalController.deleteSucursal(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Sucursal eliminada con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateSucursalTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btLimpiarSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarSucursal.setText("Limpiar");
        btLimpiarSucursal.addActionListener(evt -> {
            ftfIDSucursal.setText("");
            tfNombreSucursal.setText("");
            ftfHoraApertura.setText("00:00");
            ftfHoraCierre.setText("00:00");
            cbEstadoSucursal.setSelected(false);
            updateSucursalTable();
        });

        ftfHoraApertura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfHoraApertura.setText("00:00");
        ftfHoraApertura.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        ftfHoraCierre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfHoraCierre.setText("00:00");
        ftfHoraCierre.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        spSucursal.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N

        //btLimpiarSucursalActionPerformed();
        updateSucursalTable();
        spSucursal.setViewportView(tlSucursal);

        ftfIDSucursal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfIDSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        javax.swing.GroupLayout jSucursalTabLayout = new javax.swing.GroupLayout(jSucursalTab);
        jSucursalTab.setLayout(jSucursalTabLayout);
        jSucursalTabLayout.setHorizontalGroup(
                jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btCrearSucursal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(btLimpiarSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btModificarSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                .addGap(18, 18, 18)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btBuscarSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btEliminarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNombreSucursal)
                                                        .addComponent(lblIDSucursal)
                                                        .addComponent(lblHoraAperturaSucursal)
                                                        .addComponent(lblHoraCierreSucursal)
                                                        .addComponent(lblEstadoSucursal))
                                                .addGap(18, 18, 18)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cbEstadoSucursal, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tfNombreSucursal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                                        .addComponent(ftfHoraApertura, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ftfHoraCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ftfIDSucursal, javax.swing.GroupLayout.Alignment.LEADING))))
                                .addGap(18, 18, 18)
                                .addComponent(spSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(151, Short.MAX_VALUE))
        );
        jSucursalTabLayout.setVerticalGroup(
                jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jSucursalTabLayout.createSequentialGroup()
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblIDSucursal)
                                                        .addComponent(ftfIDSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNombreSucursal)
                                                        .addComponent(tfNombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblHoraAperturaSucursal)
                                                        .addComponent(ftfHoraApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblHoraCierreSucursal)
                                                        .addComponent(ftfHoraCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cbEstadoSucursal)
                                                        .addComponent(lblEstadoSucursal))
                                                .addGap(42, 42, 42)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btCrearSucursal)
                                                        .addComponent(btBuscarSucursal))
                                                .addGap(18, 18, 18)
                                                .addGroup(jSucursalTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btModificarSucursal)
                                                        .addComponent(btEliminarSucursal))
                                                .addGap(18, 18, 18)
                                                .addComponent(btLimpiarSucursal))
                                        .addComponent(spSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(59, Short.MAX_VALUE))
        );
    }

    private void getTableSucursal(List<SucursalDTO> listaDTO) {

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

        String[] columnas = {"ID", "Nombre", "Apertura", "Cierre", "Operativa"};
        String[] data = new String[columnas.length];
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        if (!(listaDTO == null)) {
            try {
                for (SucursalDTO dto : listaDTO) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getNombre();
                    data[2] = dto.getHoraApertura().toString();
                    data[3] = dto.getHoraCierre().toString();
                    data[4] = dto.getEstado() ? "Si" : "No";
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlSucursal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlSucursal.setModel(modelo);
        tlSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlSucursal.setShowGrid(true);
        tlSucursal.getTableHeader().setReorderingAllowed(false);
        tlSucursal.getTableHeader().setResizingAllowed(true);
        tlSucursal.setAutoCreateRowSorter(true);
        tlSucursal.setFillsViewportHeight(true);

        int i = tlSucursal.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlSucursal.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }

        tlSucursal.getSelectionModel().addListSelectionListener(event -> {
            if (tlSucursal.getSelectionModel().isSelectionEmpty())
                return;

            int row = tlSucursal.getSelectedRow();
            ftfIDSucursal.setText(tlSucursal.getValueAt(row, 0).toString());
            tfNombreSucursal.setText(tlSucursal.getValueAt(row, 1).toString());
            ftfHoraApertura.setText(tlSucursal.getValueAt(row, 2).toString());
            ftfHoraCierre.setText(tlSucursal.getValueAt(row, 3).toString());
            cbEstadoSucursal.setSelected(tlSucursal.getValueAt(row, 4).toString().equalsIgnoreCase("Si"));
        });
    }

    public void updateSucursalTable() {
        tlSucursal.getSelectionModel().clearSelection();
        sucursales = sucursalController.getAllSucursales();
        getTableSucursal(sucursales);
    }

    private javax.swing.JCheckBox cbEstadoSucursal;
    private javax.swing.JFormattedTextField ftfHoraApertura;
    private javax.swing.JFormattedTextField ftfHoraCierre;
    private javax.swing.JFormattedTextField ftfIDSucursal;
    private javax.swing.JPanel jSucursalTab;
    private javax.swing.JTextField tfNombreSucursal;
    private javax.swing.JTable tlSucursal;

    private final SucursalController sucursalController = new SucursalController(EntityManagerUtil.getEntityManager());
    private List<SucursalDTO> sucursales = sucursalController.getAllSucursales();
}
