package com.tpdied.gui;

import javax.swing.*;
import java.awt.*;

public class CaminosGui implements Tab{

    public CaminosGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Caminos";
    }

    public JPanel getTab(){
        return jCaminos;
    }

    private void initComponents() {
        jCaminos = new javax.swing.JPanel();
        spCaminos = new javax.swing.JScrollPane(tlCaminos);
        tlCaminos = new javax.swing.JTable();
        btVerCaminos = new javax.swing.JButton();

        jCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jCaminos.setMaximumSize(new java.awt.Dimension(704, 430));
        jCaminos.setMinimumSize(new java.awt.Dimension(704, 430));

        spCaminos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tlCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        tlCaminos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Fecha", "S. Destino", "Tiempo Max.", "Productos"
                }
        ) {
             Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tlCaminos.setShowGrid(true);
        tlCaminos.getTableHeader().setReorderingAllowed(false);
        spCaminos.setViewportView(tlCaminos);

        btVerCaminos.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 13)); // NOI18N
        btVerCaminos.setText("Ver Caminos");

        javax.swing.GroupLayout jCaminosLayout = new javax.swing.GroupLayout(jCaminos);
        jCaminos.setLayout(jCaminosLayout);
        jCaminosLayout.setHorizontalGroup(
                jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jCaminosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btVerCaminos)
                                .addGap(18, 18, 18)
                                .addComponent(spCaminos, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(254, Short.MAX_VALUE))
        );
        jCaminosLayout.setVerticalGroup(
                jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jCaminosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jCaminosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btVerCaminos)
                                        .addComponent(spCaminos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(224, Short.MAX_VALUE))
        );
    }

    private javax.swing.JButton btVerCaminos;
    private javax.swing.JPanel jCaminos;
    private javax.swing.JScrollPane spCaminos;
    private javax.swing.JTable tlCaminos;


}
