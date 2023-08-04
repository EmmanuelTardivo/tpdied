package com.tpdied.gui;

import com.tpdied.controllers.OrdenProvisionController;
import com.tpdied.controllers.ProductoController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.forms.OrdenProvisionForm;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdenProvisionGui implements Tab{

    public OrdenProvisionGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Provision";
    }

    public JPanel getTab(){
        return jProvisionTab;
    }

    private void initComponents() {
        jProvisionTab = new javax.swing.JPanel();
        lblSucursalProvision = new javax.swing.JLabel();
        lblProductoProvision = new javax.swing.JLabel();
        lblCantidadProvision = new javax.swing.JLabel();
        btAgregarProductoProvision = new javax.swing.JButton();
        btEliminarProductoProvision = new javax.swing.JButton();
        spProvision = new javax.swing.JScrollPane(tlProvision);
        tlProvision = new javax.swing.JTable();
        btCrearProvision = new javax.swing.JButton();
        cbSucursalProvision = new JComboBox<SucursalDTO>();
        cbProductoProvision = new JComboBox<ProductoDTO>();
        lblFechaProvision = new javax.swing.JLabel();
        lblTiempoMax = new javax.swing.JLabel();
        btLimpiarProvision = new javax.swing.JButton();
        ftfCantidadProvision = new javax.swing.JFormattedTextField();
        ftfTiempoMax = new javax.swing.JFormattedTextField();
        lblFechaOrdenProvision = new javax.swing.JLabel();

        jProvisionTab.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jProvisionTab.setMaximumSize(new java.awt.Dimension(704, 430));
        jProvisionTab.setMinimumSize(new java.awt.Dimension(704, 430));
        jProvisionTab.setPreferredSize(new java.awt.Dimension(704, 430));

        lblSucursalProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblSucursalProvision.setText("Sucursal Destino:");

        lblProductoProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblProductoProvision.setText("Producto:");

        lblCantidadProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblCantidadProvision.setText("Cantidad:");

        btAgregarProductoProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btAgregarProductoProvision.setText("Agregar Producto");
        btAgregarProductoProvision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarProductoProvisionActionPerformed(evt);
            }
        });

        btEliminarProductoProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btEliminarProductoProvision.setText("Eliminar Producto");
        btEliminarProductoProvision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarProductoProvisionActionPerformed(evt);
            }
        });

        spProvision.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spProvision.setPreferredSize(new java.awt.Dimension(600, 400));

        getTableProvision();
        spProvision.setViewportView(tlProvision);

        btCrearProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCrearProvision.setText("Crear Orden");
        btCrearProvision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearProvisionActionPerformed(evt);
            }
        });

        cbSucursalProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbSucursalProvision.setToolTipText("");
        sucursales.forEach(s -> {
            cbSucursalProvision.addItem(s);
        });
        cbSucursalProvision.setSelectedIndex(-1);

        cbProductoProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbProductoProvision.setToolTipText("");
        productos.forEach(s -> {
            cbProductoProvision.addItem(s);
        });
        cbProductoProvision.setSelectedIndex(-1);

        lblFechaProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblFechaProvision.setText("Fecha de orden:");

        btLimpiarProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarProvision.setText("Limpiar");
        btLimpiarProvision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarProvisionActionPerformed(evt);
            }
        });

        ftfCantidadProvision.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfCantidadProvision.setText("0");
        ftfCantidadProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        lblFechaOrdenProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblFechaOrdenProvision.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        lblTiempoMax.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblTiempoMax.setText("Tiempo Max.:");

        ftfTiempoMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfTiempoMax.setText("00:00");
        ftfTiempoMax.setToolTipText("");
        ftfTiempoMax.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        javax.swing.GroupLayout jProvisionTabLayout = new javax.swing.GroupLayout(jProvisionTab);
        jProvisionTab.setLayout(jProvisionTabLayout);
        jProvisionTabLayout.setHorizontalGroup(
                jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                                .addComponent(lblTiempoMax)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ftfTiempoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblSucursalProvision)
                                                        .addComponent(lblProductoProvision)
                                                        .addComponent(lblFechaProvision)
                                                        .addComponent(lblCantidadProvision))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(cbSucursalProvision, 0, 107, Short.MAX_VALUE)
                                                                .addComponent(cbProductoProvision, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblFechaOrdenProvision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(ftfCantidadProvision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(btCrearProvision, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btEliminarProductoProvision, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btAgregarProductoProvision, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btLimpiarProvision, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spProvision, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(141, Short.MAX_VALUE))
        );
        jProvisionTabLayout.setVerticalGroup(
                jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFechaProvision)
                                        .addComponent(lblFechaOrdenProvision))
                                .addGap(15, 15, 15)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSucursalProvision)
                                        .addComponent(cbSucursalProvision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProductoProvision)
                                        .addComponent(cbProductoProvision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTiempoMax)
                                        .addComponent(ftfTiempoMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCantidadProvision)
                                        .addComponent(ftfCantidadProvision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jProvisionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btAgregarProductoProvision)
                                        .addComponent(btLimpiarProvision))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btEliminarProductoProvision)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btCrearProvision)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jProvisionTabLayout.createSequentialGroup()
                                .addComponent(spProvision, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 60, Short.MAX_VALUE))
        );
    }

    private void btAgregarProductoProvisionActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbSucursalProvision.getSelectedIndex() == -1 || cbProductoProvision.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una sucursal y un producto para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (Integer.parseInt(ftfCantidadProvision.getText()) <= 0){
            javax.swing.JOptionPane.showMessageDialog(null, "La cantidad debe ser positiva y mayor a 0.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateProvisionTable((ProductoDTO) cbProductoProvision.getSelectedItem(), Integer.parseInt(ftfCantidadProvision.getText()), false);
    }

    private void btLimpiarProvisionActionPerformed(java.awt.event.ActionEvent evt) {
        sucursales = sucursalController.getAllSucursales();
        cbSucursalProvision.removeAllItems();
        sucursales.forEach(s -> {
            cbSucursalProvision.addItem(s);
        });
        cbSucursalProvision.setSelectedIndex(-1);
        productos = productoController.getAllProductos();
        cbProductoProvision.removeAllItems();
        productos.forEach(s -> {
            cbProductoProvision.addItem(s);
        });
        cbProductoProvision.setSelectedIndex(-1);
        ftfCantidadProvision.setText("0");
        ftfTiempoMax.setText("00:00");
        ordenProvision = new HashMap<ProductoDTO, Integer>();
        getTableProvision();
    }

    private void btEliminarProductoProvisionActionPerformed(java.awt.event.ActionEvent evt) {
        if (tlProvision.getSelectionModel().isSelectionEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar producto de la tabla para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateProvisionTable((ProductoDTO) cbProductoProvision.getSelectedItem(), Integer.parseInt(ftfCantidadProvision.getText()), true);
    }

    private void btCrearProvisionActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbSucursalProvision.getSelectedIndex() == -1 || cbProductoProvision.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una sucursal y un producto para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tlProvision.getModel().getRowCount() == 0 ){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe agregar productos a la tabla para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SucursalDTO sucursalDTO = (SucursalDTO) cbSucursalProvision.getSelectedItem();
            provisionController.addOrdenProvision(OrdenProvisionForm.validarOrdenProvision(sucursalDTO, ftfTiempoMax.getText(), ordenProvision));
            javax.swing.JOptionPane.showMessageDialog(null, "Orden de provision creada con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void getTableProvision() {
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

        String[] columnas = {"ID Producto", "Producto", "Cantidad"};
        String[] data = new String[columnas.length];
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        tlProvision.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlProvision.setModel(modelo);
        tlProvision.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlProvision.setShowGrid(true);
        tlProvision.getTableHeader().setReorderingAllowed(false);
        tlProvision.getTableHeader().setResizingAllowed(true);
        tlProvision.setAutoCreateRowSorter(true);
        tlProvision.setFillsViewportHeight(true);

        int i = tlProvision.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlProvision.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }

        if (!ordenProvision.isEmpty()) {
            try {
                for (ProductoDTO dto : ordenProvision.keySet()) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getNombre();
                    data[2] = ordenProvision.get(dto).toString();
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateProvisionTable(ProductoDTO dto, Integer cantidad, Boolean eliminar) {
        tlProvision.getSelectionModel().clearSelection();
        if (eliminar) ordenProvision.remove(dto);
        else ordenProvision.put(dto, cantidad);
        getTableProvision();
    }

    private javax.swing.JButton btAgregarProductoProvision;
    private javax.swing.JButton btCrearProvision;
    private javax.swing.JButton btEliminarProductoProvision;
    private javax.swing.JButton btLimpiarProvision;
    private JComboBox<ProductoDTO> cbProductoProvision;
    private JComboBox<SucursalDTO> cbSucursalProvision;
    private javax.swing.JFormattedTextField ftfCantidadProvision;
    private javax.swing.JFormattedTextField ftfTiempoMax;
    private javax.swing.JPanel jProvisionTab;
    private javax.swing.JLabel lblCantidadProvision;
    private javax.swing.JLabel lblFechaOrdenProvision;
    private javax.swing.JLabel lblFechaProvision;
    private javax.swing.JLabel lblTiempoMax;
    private javax.swing.JLabel lblProductoProvision;
    private javax.swing.JLabel lblSucursalProvision;
    private javax.swing.JScrollPane spProvision;
    private javax.swing.JTable tlProvision;

    private final SucursalController sucursalController = new SucursalController(EntityManagerUtil.getEntityManager());
    private final ProductoController productoController = new ProductoController(EntityManagerUtil.getEntityManager());
    private final OrdenProvisionController provisionController = new OrdenProvisionController(
            EntityManagerUtil.getEntityManager());
    private List<SucursalDTO> sucursales = sucursalController.getAllSucursales();
    private List<ProductoDTO> productos = productoController.getAllProductos();
    private Map<ProductoDTO, Integer> ordenProvision = new HashMap<ProductoDTO, Integer>();
}
