package com.tpdied.gui;

import com.tpdied.controllers.OrdenProvisionController;
import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.RutaDTO;
import com.tpdied.managers.OrdenProvisionManager;
import com.tpdied.models.EstadoOrden;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CaminosGui implements Tab {

    public CaminosGui() {
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Caminos";
    }

    public JPanel getTab() {
        return jCaminos;
    }

    private void initComponents() {
        jCaminos = new javax.swing.JPanel();
        JScrollPane spCaminos = new JScrollPane(tlCaminos);
        tlCaminos = new javax.swing.JTable();
        JButton btVerCaminos = new JButton();
        JScrollPane spCaminosCanvas = new JScrollPane(tlCaminos);
        tlCaminosCanvas = new javax.swing.JTable();
        JButton btAsignarRecorrido = new JButton();
        JButton btLimpiarCaminos = new JButton();
        JButton btVerRuta = new JButton();

        jCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jCaminos.setMaximumSize(new java.awt.Dimension(704, 430));
        jCaminos.setMinimumSize(new java.awt.Dimension(704, 430));

        spCaminos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getTableOrdenProvision(ordenes);
        spCaminos.setViewportView(tlCaminos);

        btVerCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btVerCaminos.setText("Ver Caminos");
        btVerCaminos.addActionListener(e -> {
            if (tlCaminos.getSelectedRow() == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            OrdenProvisionDTO dto = ordenes
                    .stream()
                    .filter(o -> o.getId() == (Integer.parseInt(tlCaminos.getValueAt(tlCaminos.getSelectedRow(), 0).toString())))
                    .findFirst()
                    .get();
            caminosPosibles = ordenProvisionManager.getCaminosPosibles(dto);
            getTableCaminosCanvas(caminosPosibles);
        });

        spCaminosCanvas.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getTableCaminosCanvas(null);
        spCaminosCanvas.setViewportView(tlCaminosCanvas);

        btAsignarRecorrido.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btAsignarRecorrido.setText("Asignar Recorrido");
        btAsignarRecorrido.addActionListener(e -> {
            if (tlCaminos.getSelectedRow() == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tlCaminosCanvas.getSelectedRow() == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar un camino de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            OrdenProvisionDTO dto = ordenes
                    .stream()
                    .filter(o -> o.getId() == (Integer.parseInt(tlCaminos.getValueAt(tlCaminos.getSelectedRow(), 0).toString())))
                    .findFirst()
                    .get();
            dto.setEstado(EstadoOrden.EN_PROCESO);
            ordenProvisionController.updateOrdenProvision(dto);
            javax.swing.JOptionPane.showMessageDialog(null, "Orden asignada con éxito", "ÉXITO",
                    JOptionPane.INFORMATION_MESSAGE);
            ordenes = ordenProvisionController.getOrdenesProvisionPendientes();
            getTableOrdenProvision(ordenes);
            getTableCaminosCanvas(null);
        });

        btLimpiarCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btLimpiarCaminos.setText("Limpiar");
        btLimpiarCaminos.addActionListener(e -> {
            tlCaminos.getSelectionModel().clearSelection();
            tlCaminosCanvas.getSelectionModel().clearSelection();
            ordenes = ordenProvisionController.getOrdenesProvisionPendientes();
            getTableOrdenProvision(ordenes);
            getTableCaminosCanvas(null);
        });

        btVerRuta.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btVerRuta.setText("Ver Ruta");
        btVerRuta.addActionListener(e -> {
            if (tlCaminosCanvas.getSelectedRow() == -1) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar un camino de la tabla para continuar.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            int row = tlCaminosCanvas.getSelectedRow();
            List<RutaDTO> camino = caminosPosibles
                    .stream()
                    .filter(c -> c.get(0).getSucursalOrigen().toString().equals(tlCaminosCanvas.getValueAt(row, 0))
                            && ordenProvisionManager.getTiempoTotal(c).equals(tlCaminosCanvas.getValueAt(row, 1)))
                    .findFirst()
                    .get();
            new RecorridoGui(camino);
        });

        javax.swing.GroupLayout jCaminosLayout = new javax.swing.GroupLayout(jCaminos);
        jCaminos.setLayout(jCaminosLayout);
        jCaminosLayout.setHorizontalGroup(
                jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jCaminosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spCaminos, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jCaminosLayout.createSequentialGroup()
                                                .addComponent(spCaminosCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btVerCaminos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btAsignarRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btVerRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btLimpiarCaminos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(133, Short.MAX_VALUE))
        );
        jCaminosLayout.setVerticalGroup(
                jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jCaminosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(spCaminos, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spCaminosCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jCaminosLayout.createSequentialGroup()
                                                .addComponent(btVerCaminos)
                                                .addGap(18, 18, 18)
                                                .addComponent(btVerRuta)
                                                .addGap(18, 18, 18)
                                                .addComponent(btAsignarRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btLimpiarCaminos)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );
    }

    private void getTableOrdenProvision(List<OrdenProvisionDTO> listaDTO) {
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

        String[] columnas = {"ID", "Fecha", "S. Destino", "Tiempo Max."};
        String[] data = new String[columnas.length];
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        if (!(listaDTO == null)) {
            try {
                for (OrdenProvisionDTO dto : listaDTO) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getFechaOrden().toLocalDate().toString();
                    data[2] = dto.getSucursalDestino().getNombre();
                    data[3] = dto.getLimiteTiempo().toString();
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlCaminos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlCaminos.setModel(modelo);
        tlCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlCaminos.setShowGrid(true);
        tlCaminos.getTableHeader().setReorderingAllowed(false);
        tlCaminos.getTableHeader().setResizingAllowed(true);
        tlCaminos.setAutoCreateRowSorter(true);
        tlCaminos.setFillsViewportHeight(true);

        int i = tlCaminos.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlCaminos.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }
    }

    private void getTableCaminosCanvas(List<List<RutaDTO>> caminos) {
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

        String[] columnas = {"S. Origen", "Tiempo"};
        String[] data = new String[columnas.length];
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        if (!(caminos == null)) {
            try {
                for (List<RutaDTO> dto : caminos) {
                    data[0] = dto.get(0).getSucursalOrigen().toString();
                    data[1] = ordenProvisionManager.getTiempoTotal(dto);
                    modelo.addRow(data);
                }

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlCaminosCanvas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlCaminosCanvas.setModel(modelo);
        tlCaminosCanvas.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlCaminosCanvas.setShowGrid(true);
        tlCaminosCanvas.getTableHeader().setReorderingAllowed(false);
        tlCaminosCanvas.getTableHeader().setResizingAllowed(true);
        tlCaminosCanvas.setAutoCreateRowSorter(true);
        tlCaminosCanvas.setFillsViewportHeight(true);

        int i = tlCaminosCanvas.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlCaminosCanvas.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }
    }


    private javax.swing.JPanel jCaminos;
    private javax.swing.JTable tlCaminos;
    private javax.swing.JTable tlCaminosCanvas;

    private final OrdenProvisionController ordenProvisionController = new OrdenProvisionController(EntityManagerUtil.getEntityManager());
    private final OrdenProvisionManager ordenProvisionManager = new OrdenProvisionManager(EntityManagerUtil.getEntityManager());
    private List<OrdenProvisionDTO> ordenes = ordenProvisionController.getOrdenesProvisionPendientes();
    private List<List<RutaDTO>> caminosPosibles;
}

