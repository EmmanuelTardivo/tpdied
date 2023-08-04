package com.tpdied.gui;

import javax.swing.*;
import java.awt.*;

public class FlujoMaxGui implements Tab{

    public FlujoMaxGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Flujo Máx.";
    }

    public JPanel getTab(){
        return jFlujoMax;
    }

    private void initComponents() {
        jFlujoMax = new javax.swing.JPanel();

        jFlujoMax.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jFlujoMax.setMaximumSize(new java.awt.Dimension(704, 430));
        jFlujoMax.setMinimumSize(new java.awt.Dimension(704, 430));
        jFlujoMax.setName(""); // NOI18N
        jFlujoMax.setPreferredSize(new java.awt.Dimension(704, 430));

        javax.swing.GroupLayout jFlujoMaxLayout = new javax.swing.GroupLayout(jFlujoMax);
        jFlujoMax.setLayout(jFlujoMaxLayout);
        jFlujoMaxLayout.setHorizontalGroup(
                jFlujoMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 704, Short.MAX_VALUE)
        );
        jFlujoMaxLayout.setVerticalGroup(
                jFlujoMaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 430, Short.MAX_VALUE)
        );
    }

    private javax.swing.JPanel jFlujoMax;
}
