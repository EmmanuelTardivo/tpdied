package com.tpdied.gui;

import javax.swing.*;
import java.awt.*;

public class PageRankGui implements Tab{

    public PageRankGui(){
        initComponents();
    }

    @Override
    public String getTitle() {
        return "Page Rank";
    }

    public JPanel getTab(){
        return jPageRank;
    }

    private void initComponents() {
        jPageRank = new javax.swing.JPanel();

        jPageRank.setFont(new java.awt.Font("Noto Sans", Font.PLAIN, 12)); // NOI18N
        jPageRank.setMaximumSize(new java.awt.Dimension(704, 430));
        jPageRank.setMinimumSize(new java.awt.Dimension(704, 430));
        jPageRank.setName(""); // NOI18N
        jPageRank.setPreferredSize(new java.awt.Dimension(704, 430));

        javax.swing.GroupLayout jPageRankLayout = new javax.swing.GroupLayout(jPageRank);
        jPageRank.setLayout(jPageRankLayout);
        jPageRankLayout.setHorizontalGroup(
                jPageRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 704, Short.MAX_VALUE)
        );
        jPageRankLayout.setVerticalGroup(
                jPageRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 430, Short.MAX_VALUE)
        );
    }

    private javax.swing.JPanel jPageRank;
}
