package com.tpdied.gui;

import com.tpdied.controllers.ProductoController;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.forms.ProductoForm;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoGui implements Tab {

    public ProductoGui() {
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Producto";
    }

    public JPanel getTab() {
        return jProductoTab;
    }

    private void initComponents() {
        jProductoTab = new javax.swing.JPanel();
        JLabel lblIDProducto = new JLabel();
        tfNombreProducto = new javax.swing.JTextField();
        JLabel lblNombreProducto = new JLabel();
        JLabel lblDescripcionProducto = new JLabel();
        JLabel lblPrecioUnitario = new JLabel();
        JLabel lblPesoKG = new JLabel();
        JButton btCrearProducto = new JButton();
        JButton btBuscarProducto = new JButton();
        JButton btModificarProducto = new JButton();
        JButton btEliminarProducto = new JButton();
        JScrollPane spProducto = new JScrollPane();
        tlProducto = new javax.swing.JTable();
        JButton btLimpiarProducto = new JButton();
        tfDescripcionProducto = new javax.swing.JTextField();
        ftfPrecioUnitario = new javax.swing.JFormattedTextField();
        ftfPesoKG = new javax.swing.JFormattedTextField();
        ftfIDProducto = new javax.swing.JFormattedTextField();

        jProductoTab.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jProductoTab.setMaximumSize(new java.awt.Dimension(704, 430));
        jProductoTab.setMinimumSize(new java.awt.Dimension(704, 430));
        jProductoTab.setPreferredSize(new java.awt.Dimension(704, 430));

        lblIDProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblIDProducto.setText("ID:");

        tfNombreProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        tfNombreProducto.setMaximumSize(new java.awt.Dimension(64, 23));

        lblNombreProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblNombreProducto.setText("Nombre:");

        lblDescripcionProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblDescripcionProducto.setText("Descripción:");

        lblPrecioUnitario.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblPrecioUnitario.setText("Precio Unitario:");

        lblPesoKG.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblPesoKG.setText("Peso en KG:");

        btCrearProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCrearProducto.setText("Crear");
        btCrearProducto.addActionListener(evt -> {
            try {
                ProductoDTO dto = ProductoForm.validarProducto(tfNombreProducto.getText(), tfDescripcionProducto.getText(),
                        ftfPrecioUnitario.getText(), ftfPesoKG.getText());
                productoController.addProducto(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Producto creado con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateProductoTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btBuscarProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btBuscarProducto.setText("Buscar");
        btBuscarProducto.addActionListener(evt -> {
            if (!ftfIDProducto.getText().isBlank()) {
                List<ProductoDTO> aux = new ArrayList<>();
                aux.add(productoController.getProductoById(Integer.parseInt(ftfIDProducto.getText())));
                getTableProducto(aux);
                return;
            }
            if (!tfNombreProducto.getText().isBlank()) {
                getTableProducto(productoController.getProductosByName(tfNombreProducto.getText()));
                return;
            }

            if (!tfDescripcionProducto.getText().isBlank()) {
                getTableProducto(productoController.getProductosByDescripcion(tfDescripcionProducto.getText()));
                return;
            }

            if (!ftfPrecioUnitario.getText().isBlank() && !ftfPrecioUnitario.getText().equals("0")) {
                getTableProducto(productoController.getProductosByPrecio(Double.parseDouble(ftfPrecioUnitario.getText())));
                return;
            }

            if (!ftfPesoKG.getText().isBlank() && !ftfPesoKG.getText().equals("0")) {
                getTableProducto(productoController.getProductosByPrecio(Double.parseDouble(ftfPesoKG.getText())));
                return;
            }
            getTableProducto(productos);
        });

        btModificarProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btModificarProducto.setText("Modificar");
        btModificarProducto.addActionListener(evt -> {
            if (tlProducto.getSelectionModel().isSelectionEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ProductoDTO dto = ProductoForm.validarProducto(tfNombreProducto.getText(), tfDescripcionProducto.getText(),
                        ftfPrecioUnitario.getText(), ftfPesoKG.getText());
                dto.setId(Integer.parseInt(ftfIDProducto.getText()));
                productoController.updateProducto(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Producto modificado con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateProductoTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btEliminarProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btEliminarProducto.setText("Eliminar");
        btEliminarProducto.addActionListener(evt -> {
            if (tlProducto.getSelectionModel().isSelectionEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(tlProducto.getValueAt(tlProducto.getSelectedRow(), 0).toString());
                ProductoDTO dto = productos
                        .stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst().get();
                productoController.deleteProducto(dto);
                javax.swing.JOptionPane.showMessageDialog(null, "Producto eliminado con éxito", "ÉXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                updateProductoTable();
            } catch (IllegalArgumentException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        spProducto.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spProducto.setPreferredSize(new java.awt.Dimension(600, 400));
        getTableProducto(productos);
        spProducto.setViewportView(tlProducto);

        btLimpiarProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarProducto.setText("Limpiar");
        btLimpiarProducto.addActionListener(evt -> {
            ftfIDProducto.setText("");
            tfNombreProducto.setText("");
            tfDescripcionProducto.setText("");
            ftfPrecioUnitario.setText("0");
            ftfPesoKG.setText("0");
            updateProductoTable();
        });

        tfDescripcionProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        tfDescripcionProducto.setMaximumSize(new java.awt.Dimension(64, 23));

        ftfPrecioUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        ftfPrecioUnitario.setText("0");
        ftfPrecioUnitario.setToolTipText("En Dolares");
        ftfPrecioUnitario.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        ftfPesoKG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        ftfPesoKG.setText("0");
        ftfPesoKG.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        ftfIDProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        javax.swing.GroupLayout jProductoTabLayout = new javax.swing.GroupLayout(jProductoTab);
        jProductoTab.setLayout(jProductoTabLayout);
        jProductoTabLayout.setHorizontalGroup(
                jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNombreProducto)
                                                        .addComponent(lblIDProducto)
                                                        .addComponent(lblDescripcionProducto)
                                                        .addComponent(lblPrecioUnitario)
                                                        .addComponent(lblPesoKG))
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tfDescripcionProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(ftfPrecioUnitario)
                                                                        .addComponent(ftfPesoKG, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(tfNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                                                        .addComponent(ftfIDProducto)))))
                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btCrearProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(btLimpiarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btModificarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                .addGap(18, 18, 18)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 15, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(spProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149))
        );
        jProductoTabLayout.setVerticalGroup(
                jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jProductoTabLayout.createSequentialGroup()
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblIDProducto)
                                                        .addComponent(ftfIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNombreProducto)
                                                        .addComponent(tfNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblDescripcionProducto)
                                                        .addComponent(tfDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblPrecioUnitario)
                                                        .addComponent(ftfPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(16, 16, 16)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblPesoKG)
                                                        .addComponent(ftfPesoKG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btCrearProducto)
                                                        .addComponent(btBuscarProducto))
                                                .addGap(18, 18, 18)
                                                .addGroup(jProductoTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btModificarProducto)
                                                        .addComponent(btEliminarProducto))
                                                .addGap(18, 18, 18)
                                                .addComponent(btLimpiarProducto))
                                        .addComponent(spProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(54, Short.MAX_VALUE))
        );
    }

    private void getTableProducto(List<ProductoDTO> listaDTO) {
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

        String[] columnas = {"ID", "Nombre", "Descripcion", "Precio_Unitario", "Peso_KG"};
        String[] data = new String[columnas.length];
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        if (!(listaDTO == null)) {
            try {
                for (ProductoDTO dto : listaDTO) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getNombre();
                    data[2] = dto.getDescripcion();
                    data[3] = dto.getPrecio().toString();
                    data[4] = dto.getPeso().toString();
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlProducto.setModel(modelo);
        tlProducto.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlProducto.setShowGrid(true);
        tlProducto.getTableHeader().setReorderingAllowed(false);
        tlProducto.getTableHeader().setResizingAllowed(true);
        tlProducto.setAutoCreateRowSorter(true);
        tlProducto.setFillsViewportHeight(true);

        int i = tlProducto.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlProducto.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }

        tlProducto.getSelectionModel().addListSelectionListener(event -> {
            if (tlProducto.getSelectionModel().isSelectionEmpty())
                return;

            int row = tlProducto.getSelectedRow();
            ftfIDProducto.setText(tlProducto.getValueAt(row, 0).toString());
            tfNombreProducto.setText(tlProducto.getValueAt(row, 1).toString());
            tfDescripcionProducto.setText(tlProducto.getValueAt(row, 2).toString());
            ftfPrecioUnitario.setText(tlProducto.getValueAt(row, 3).toString());
            ftfPesoKG.setText(tlProducto.getValueAt(row, 4).toString());
        });
    }

    private void updateProductoTable() {
        tlProducto.getSelectionModel().clearSelection();
        productos = productoController.getAllProductos();
        getTableProducto(productos);
    }

    private javax.swing.JFormattedTextField ftfIDProducto;
    private javax.swing.JFormattedTextField ftfPesoKG;
    private javax.swing.JFormattedTextField ftfPrecioUnitario;
    private javax.swing.JPanel jProductoTab;
    private javax.swing.JTextField tfDescripcionProducto;
    private javax.swing.JTextField tfNombreProducto;
    private javax.swing.JTable tlProducto;

    private final ProductoController productoController = new ProductoController(EntityManagerUtil.getEntityManager());
    private List<ProductoDTO> productos = productoController.getAllProductos();

}
