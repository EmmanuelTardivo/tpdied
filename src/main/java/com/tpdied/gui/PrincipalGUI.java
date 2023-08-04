package com.tpdied.gui;

import javax.swing.*;
import java.awt.*;

public class PrincipalGUI extends javax.swing.JFrame {

    public PrincipalGUI() {
        initComponents();
    }

    private void initComponents() {
        JTabbedPane jTabbedPane = new JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(704, 430));
        setMinimumSize(new java.awt.Dimension(704, 430));
        setResizable(false);
        setSize(new java.awt.Dimension(704, 430));

        jTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 18));
        jTabbedPane.setMaximumSize(new java.awt.Dimension(704, 430));
        jTabbedPane.setMinimumSize(new java.awt.Dimension(704, 430));
        jTabbedPane.setPreferredSize(new java.awt.Dimension(704, 430));

        SucursalGui sucursalTab = new SucursalGui();
        ProductoGui productoTab = new ProductoGui();
        StockGui stockTab = new StockGui();
        RutaGui rutaTab = new RutaGui();
        OrdenProvisionGui provisionTab = new OrdenProvisionGui();
        CaminosGui caminosTab = new CaminosGui();
        OtrosGui otrosTab = new OtrosGui();

        jTabbedPane.addTab(sucursalTab.getTitle(), sucursalTab.getTab());
        jTabbedPane.addTab(rutaTab.getTitle(), rutaTab.getTab());
        jTabbedPane.addTab(productoTab.getTitle(), productoTab.getTab());
        jTabbedPane.addTab(stockTab.getTitle(), stockTab.getTab());
        jTabbedPane.addTab(provisionTab.getTitle(), provisionTab.getTab());
        jTabbedPane.addTab(caminosTab.getTitle(), caminosTab.getTab());
        jTabbedPane.addTab(otrosTab.getTitle(), otrosTab.getTab());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }
}
