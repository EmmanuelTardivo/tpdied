package com.tpdied.gui;

import com.tpdied.controllers.ProductoController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockGui implements Tab{

    public StockGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Stock";
    }

    public JPanel getTab(){
        return jStockTab;
    }

    private void initComponents() {
        jStockTab = new javax.swing.JPanel();
        lblSucursalStock = new javax.swing.JLabel();
        lblProductoStock = new javax.swing.JLabel();
        lblCantidadStock = new javax.swing.JLabel();
        btCrearStock = new javax.swing.JButton();
        btModificarStock = new javax.swing.JButton();
        btEliminarStock = new javax.swing.JButton();
        spStock = new javax.swing.JScrollPane();
        tlStockSucursal = new javax.swing.JTable();
        btLimpiarStock = new javax.swing.JButton();
        cbSucursalStock = new JComboBox<SucursalDTO>();
        cbProductoStock = new JComboBox<ProductoDTO>();
        ftfCantidadStock = new javax.swing.JFormattedTextField();

        jStockTab.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jStockTab.setMaximumSize(new java.awt.Dimension(704, 430));
        jStockTab.setMinimumSize(new java.awt.Dimension(704, 430));
        jStockTab.setName(""); // NOI18N
        jStockTab.setPreferredSize(new java.awt.Dimension(704, 430));

        lblSucursalStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblSucursalStock.setText("Sucursal:");

        lblProductoStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblProductoStock.setText("Producto:");

        lblCantidadStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblCantidadStock.setText("Cantidad:");

        btCrearStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCrearStock.setText("Crear");
        btCrearStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearStockActionPerformed(evt);
            }
        });

        btModificarStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btModificarStock.setText("Modificar");
        btModificarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarStockActionPerformed(evt);
            }
        });

        btEliminarStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btEliminarStock.setText("Eliminar");
        btEliminarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarStockActionPerformed(evt);
            }
        });

        spStock.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spStock.setPreferredSize(new java.awt.Dimension(600, 400));

        getTableStockSucursal(null);
        spStock.setViewportView(tlStockSucursal);

        btLimpiarStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarStock.setText("Limpiar");
        btLimpiarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarStockActionPerformed(evt);
            }
        });

        cbSucursalStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbSucursalStock.setToolTipText("");
        sucursales.forEach(s -> {
            cbSucursalStock.addItem(s);
        });
        cbSucursalStock.setSelectedIndex(-1);

        cbSucursalStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalStockActionPerformed(evt);
            }
        });

        cbProductoStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        cbProductoStock.setToolTipText("");
        productos.forEach(s -> {
            cbProductoStock.addItem(s);
        });
        cbProductoStock.setSelectedIndex(-1);
        cbProductoStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductoStockActionPerformed(evt);
            }
        });

        ftfCantidadStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ftfCantidadStock.setText("0");
        ftfCantidadStock.setToolTipText("");
        ftfCantidadStock.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        javax.swing.GroupLayout jStockTabLayout = new javax.swing.GroupLayout(jStockTab);
        jStockTab.setLayout(jStockTabLayout);
        jStockTabLayout.setHorizontalGroup(
                jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jStockTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jStockTabLayout.createSequentialGroup()
                                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblSucursalStock)
                                                        .addComponent(lblProductoStock)
                                                        .addComponent(lblCantidadStock))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cbSucursalStock, 0, 107, Short.MAX_VALUE)
                                                        .addComponent(cbProductoStock, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(ftfCantidadStock)))
                                        .addGroup(jStockTabLayout.createSequentialGroup()
                                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btCrearStock, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btLimpiarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btEliminarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btModificarStock))))
                                .addGap(18, 18, 18)
                                .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(149, Short.MAX_VALUE))
        );
        jStockTabLayout.setVerticalGroup(
                jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jStockTabLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSucursalStock)
                                        .addComponent(cbSucursalStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProductoStock)
                                        .addComponent(cbProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCantidadStock)
                                        .addComponent(ftfCantidadStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btCrearStock)
                                        .addComponent(btModificarStock))
                                .addGap(18, 18, 18)
                                .addGroup(jStockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btEliminarStock)
                                        .addComponent(btLimpiarStock))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jStockTabLayout.createSequentialGroup()
                                .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 60, Short.MAX_VALUE))
        );
    }

    private void cbSucursalStockActionPerformed(java.awt.event.ActionEvent evt) {
        if(cbSucursalStock.getSelectedIndex() != -1)
            updateProductoStockTable((SucursalDTO) cbSucursalStock.getSelectedItem());
    }

    private void cbProductoStockActionPerformed(java.awt.event.ActionEvent evt) {
        ProductoDTO k = ((ProductoDTO) cbProductoStock.getSelectedItem());
        if (productoStockSucursal.containsKey(k))
            ftfCantidadStock.setText(productoStockSucursal.get(k).toString());
    }

    private void btCrearStockActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SucursalDTO sucursalDTO = (SucursalDTO) cbSucursalStock.getSelectedItem();
            ProductoDTO productoDTO = (ProductoDTO) cbProductoStock.getSelectedItem();
            sucursalController.setStockProducto(sucursalDTO, productoDTO, Integer.parseInt(ftfCantidadStock.getText()));
            sucursalDTO.getListaProductoCantidadEnStock().put(productoDTO, Integer.parseInt(ftfCantidadStock.getText()));
            javax.swing.JOptionPane.showMessageDialog(null, "Stock de Producto creado con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateProductoStockTable(sucursalDTO);
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btModificarStockActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SucursalDTO sucursalDTO = (SucursalDTO) cbSucursalStock.getSelectedItem();
            ProductoDTO productoDTO = (ProductoDTO) cbProductoStock.getSelectedItem();
            sucursalController.setStockProducto(sucursalDTO, productoDTO, Integer.parseInt(ftfCantidadStock.getText()));
            sucursalDTO.getListaProductoCantidadEnStock().put(productoDTO, Integer.parseInt(ftfCantidadStock.getText()));
            javax.swing.JOptionPane.showMessageDialog(null, "Stock de Producto modificado con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateProductoStockTable(sucursalDTO);
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btLimpiarStockActionPerformed(java.awt.event.ActionEvent evt) {
        sucursales = sucursalController.getAllSucursales();
        cbSucursalStock.removeAllItems();
        sucursales.forEach(s -> {
            cbSucursalStock.addItem(s);
        });
        cbSucursalStock.setSelectedIndex(-1);
        productos = productoController.getAllProductos();
        cbProductoStock.removeAllItems();
        productos.forEach(s -> {
            cbProductoStock.addItem(s);
        });
        cbProductoStock.setSelectedIndex(-1);
        ftfCantidadStock.setText("0");
        getTableStockSucursal(null);
    }

    private void btEliminarStockActionPerformed(java.awt.event.ActionEvent evt) {
        if (tlStockSucursal.getSelectionModel().isSelectionEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para continuar.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            SucursalDTO sucursalDTO = (SucursalDTO) cbSucursalStock.getSelectedItem();
            ProductoDTO productoDTO = (ProductoDTO) cbProductoStock.getSelectedItem();
            sucursalController.setStockProducto(sucursalDTO, productoDTO, 0);
            sucursalDTO.getListaProductoCantidadEnStock().put(productoDTO, 0);
            javax.swing.JOptionPane.showMessageDialog(null, "Stock vaciado con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            updateProductoStockTable(sucursalDTO);
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getTableStockSucursal(Map<ProductoDTO, Integer> listaDTO) {
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

        if (!(listaDTO == null)) {
            try {
                for (ProductoDTO dto : listaDTO.keySet()) {
                    if(listaDTO.get(dto) > 0) {
                        data[0] = dto.getId().toString();
                        data[1] = dto.getNombre();
                        data[2] = listaDTO.get(dto).toString();
                        modelo.addRow(data);
                    }
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlStockSucursal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlStockSucursal.setModel(modelo);
        tlStockSucursal.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlStockSucursal.setShowGrid(true);
        tlStockSucursal.getTableHeader().setReorderingAllowed(false);
        tlStockSucursal.getTableHeader().setResizingAllowed(true);
        tlStockSucursal.setAutoCreateRowSorter(true);
        tlStockSucursal.setFillsViewportHeight(true);

        int i = tlStockSucursal.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlStockSucursal.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }

        tlStockSucursal.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (tlStockSucursal.getSelectionModel().isSelectionEmpty())
                    return;

                int row = tlStockSucursal.getSelectedRow();
                cbProductoStock.setSelectedItem(
                        productos
                                .stream()
                                .filter(p -> p.getId() == Integer.parseInt(tlStockSucursal.getValueAt(row, 0).toString()))
                                .findFirst()
                                .orElseThrow(null));
                ftfCantidadStock.setText(tlStockSucursal.getValueAt(row, 2).toString());
            }});
    }

    private void updateProductoStockTable(SucursalDTO dto) {
        tlStockSucursal.getSelectionModel().clearSelection();
        productoStockSucursal = sucursalController.getStockProductos(dto);
        getTableStockSucursal(productoStockSucursal);
    }

    private javax.swing.JButton btCrearStock;
    private javax.swing.JButton btEliminarStock;
    private javax.swing.JButton btLimpiarStock;
    private javax.swing.JButton btModificarStock;
    private JComboBox<ProductoDTO> cbProductoStock;
    private JComboBox<SucursalDTO> cbSucursalStock;
    private javax.swing.JFormattedTextField ftfCantidadStock;
    private javax.swing.JPanel jStockTab;
    private javax.swing.JLabel lblCantidadStock;
    private javax.swing.JLabel lblProductoStock;
    private javax.swing.JLabel lblSucursalStock;
    private javax.swing.JScrollPane spStock;
    private javax.swing.JTable tlStockSucursal;


    private final SucursalController sucursalController = new SucursalController(EntityManagerUtil.getEntityManager());
    private final ProductoController productoController = new ProductoController(EntityManagerUtil.getEntityManager());
    private List<SucursalDTO> sucursales = sucursalController.getAllSucursales();
    private List<ProductoDTO> productos = productoController.getAllProductos();
    private Map<ProductoDTO, Integer> productoStockSucursal = new HashMap<ProductoDTO, Integer>();


}
