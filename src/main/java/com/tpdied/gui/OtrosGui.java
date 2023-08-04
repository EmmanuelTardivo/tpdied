package com.tpdied.gui;

import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.managers.OrdenProvisionManager;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class OtrosGui implements Tab {

    public OtrosGui() {
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Otros";
    }

    @Override
    public JPanel getTab() {
        return jOtros;
    }

    private void initComponents() {
        jOtros = new javax.swing.JPanel();
        JLabel lblPageRank = new JLabel();
        JLabel lblFlujoMaxResultado = new JLabel();
        JLabel lblFlujoMax = new JLabel();
        JLabel lblFactorAmortiguacion = new JLabel();
        JLabel lblIteraciones = new JLabel();
        JLabel lblTolerancia = new JLabel();
        tfFactorAmortiguacion = new javax.swing.JTextField();
        tfIteraciones = new javax.swing.JTextField();
        tfTolerancia = new javax.swing.JTextField();
        JScrollPane spPageRank = new JScrollPane();
        tlPageRank = new javax.swing.JTable();
        JButton btCalcularPageRank = new JButton();

        jOtros.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jOtros.setMaximumSize(new java.awt.Dimension(704, 430));
        jOtros.setMinimumSize(new java.awt.Dimension(704, 430));
        jOtros.setName(""); // NOI18N
        jOtros.setPreferredSize(new java.awt.Dimension(704, 430));

        lblPageRank.setFont(new java.awt.Font("Noto Sans", Font.BOLD, 24)); // NOI18N
        lblPageRank.setText("Page Rank");

        lblFlujoMaxResultado.setFont(new java.awt.Font("Noto Sans", Font.BOLD, 24)); // NOI18N

        double flujoMax = ordenProvisionManager.calcularFlujoMaximo(
                sucursalController.getSucursalByName("Puerto"),
                sucursalController.getSucursalByName("Casa Central"));
        lblFlujoMaxResultado.setText(String.valueOf(flujoMax));

        lblFlujoMax.setFont(new java.awt.Font("Noto Sans", Font.BOLD, 24)); // NOI18N
        lblFlujoMax.setText("Flujo Máximo Puerto-Casa Central:");

        lblFactorAmortiguacion.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblFactorAmortiguacion.setText("Factor Amortiguación:");

        lblIteraciones.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblIteraciones.setText("Iteraciones:");

        lblTolerancia.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        lblTolerancia.setText("Tolerancia:");

        tfFactorAmortiguacion.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        tfIteraciones.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        tfTolerancia.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N

        tlPageRank.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        getTablePageRank(null);
        spPageRank.setViewportView(tlPageRank);

        btCalcularPageRank.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btCalcularPageRank.setText("Calcular");
        btCalcularPageRank.addActionListener(e -> {
            String factorAmortiguacion = tfFactorAmortiguacion.getText();
            String iteraciones = tfIteraciones.getText();
            String tolerancia = tfTolerancia.getText();

            try {
                validarDouble(factorAmortiguacion);
                validarInteger(iteraciones);
                validarDouble(tolerancia);
                getTablePageRank(ordenProvisionManager.calcularPageRank(Double.parseDouble(factorAmortiguacion),
                        Integer.parseInt(iteraciones), Double.parseDouble(tolerancia)));
            } catch (IllegalArgumentException msg) {
                javax.swing.JOptionPane.showMessageDialog(null, msg.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        javax.swing.GroupLayout jOtrosLayout = new javax.swing.GroupLayout(jOtros);
        jOtros.setLayout(jOtrosLayout);
        jOtrosLayout.setHorizontalGroup(
                jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jOtrosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jOtrosLayout.createSequentialGroup()
                                                .addComponent(lblPageRank)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jOtrosLayout.createSequentialGroup()
                                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jOtrosLayout.createSequentialGroup()
                                                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jOtrosLayout.createSequentialGroup()
                                                                                .addComponent(lblTolerancia)
                                                                                .addGap(81, 81, 81)
                                                                                .addComponent(tfTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(jOtrosLayout.createSequentialGroup()
                                                                                        .addComponent(lblIteraciones)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(tfIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jOtrosLayout.createSequentialGroup()
                                                                                        .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(btCalcularPageRank)
                                                                                                .addComponent(lblFactorAmortiguacion))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addComponent(tfFactorAmortiguacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(spPageRank, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(lblFlujoMaxResultado)
                                                                .addComponent(lblFlujoMax)))
                                                .addGap(0, 149, Short.MAX_VALUE))))
        );
        jOtrosLayout.setVerticalGroup(
                jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jOtrosLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblFlujoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFlujoMaxResultado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPageRank)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jOtrosLayout.createSequentialGroup()
                                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblFactorAmortiguacion)
                                                        .addComponent(tfFactorAmortiguacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblIteraciones)
                                                        .addComponent(tfIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblTolerancia)
                                                        .addComponent(tfTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(btCalcularPageRank))
                                        .addComponent(spPageRank, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private void validarDouble(String unDouble) {
        if (unDouble == null || unDouble.isBlank()) {
            throw new IllegalArgumentException("El factor amortiguacion/tolerancia no puede ser nulo.");
        }

        try {
            double aux = Double.parseDouble(unDouble);
            if (aux <= 0.0) {
                throw new IllegalArgumentException("El factor amortiguacion/tolerancia debe ser un número positivo mayor a 0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El factor amortiguacion/tolerancia debe ser un número válido.");
        }
    }

    private void validarInteger(String unInteger) {
        if (unInteger == null || unInteger.isBlank()) {
            throw new IllegalArgumentException("Las iteraciones no pueden ser nulas.");
        }

        try {
            int aux = Integer.parseInt(unInteger);
            if (aux <= 0) {
                throw new IllegalArgumentException("Las iteraciones deben ser un número positivo mayor a 0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Las iteraciones deben ser ser un número válido.");
        }
    }

    private void getTablePageRank(Map<SucursalDTO, Double> listaDTO) {
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

        String[] columnas = {"ID", "Sucursal", "Coeficiente"};
        String[] data = new String[columnas.length];
        for (String columna : columnas) {
            modelo.addColumn(columna);
        }

        if (!(listaDTO == null)) {
            try {
                for (SucursalDTO dto : listaDTO.keySet()) {
                    data[0] = dto.getId().toString();
                    data[1] = dto.getNombre();
                    data[2] = listaDTO.get(dto).toString();
                    modelo.addRow(data);
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        tlPageRank.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tlPageRank.setModel(modelo);
        tlPageRank.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13));
        tlPageRank.setShowGrid(true);
        tlPageRank.getTableHeader().setReorderingAllowed(false);
        tlPageRank.getTableHeader().setResizingAllowed(true);
        tlPageRank.setAutoCreateRowSorter(true);
        tlPageRank.setFillsViewportHeight(true);

        int i = tlPageRank.getColumnModel().getColumnCount() - 1;

        while (i >= 0) {
            tlPageRank.getColumnModel().getColumn(i).setResizable(true);
            i--;
        }
    }

    private javax.swing.JPanel jOtros;
    private javax.swing.JTextField tfFactorAmortiguacion;
    private javax.swing.JTextField tfIteraciones;
    private javax.swing.JTextField tfTolerancia;
    private javax.swing.JTable tlPageRank;

    private final OrdenProvisionManager ordenProvisionManager = new OrdenProvisionManager(EntityManagerUtil.getEntityManager());
    private final SucursalController sucursalController = new SucursalController(EntityManagerUtil.getEntityManager());
}
