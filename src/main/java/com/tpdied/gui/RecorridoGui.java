package com.tpdied.gui;

import com.tpdied.dto.RutaDTO;
import com.tpdied.managers.OrdenProvisionManager;
import com.tpdied.util.EntityManagerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.stream.Collectors;

public class RecorridoGui {
    public RecorridoGui(List<RutaDTO> camino) {
        JFrame ventana = new JFrame("Recorrido de camino");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(500, 500);

        GrafoCanvas canvas = new GrafoCanvas(camino);
        ventana.getContentPane().add(canvas);
        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);
    }
}

class Node {
    public int x, y, weight, height;
    public String name;

    public Node(int x, int y, int weight, int height, String name) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;
        this.name = name;
    }

    public Ellipse2D.Float getShape() {
        return new Ellipse2D.Float(this.x, this.y, this.weight, this.height);
    }
}

class Arista {
    public int x1, y1, x2, y2;

    public Arista(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Line2D.Float getShape() {
        return new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }
}

class GrafoCanvas extends Canvas {
    private final List<RutaDTO> camino;
    private final OrdenProvisionManager ordenProvisionManager = new OrdenProvisionManager(EntityManagerUtil.getEntityManager());
    private List<String> sucursales;

    public GrafoCanvas(List<RutaDTO> camino) {
        super();
        this.camino = camino;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        sucursales = ordenProvisionManager
                .getSucursalesDeCamino(camino)
                .stream()
                .map(s -> s.getNombre().toLowerCase())
                .collect(Collectors.toList());

        // Creo los nodos
        Node nodoPuerto = new Node(30, 200, 30, 30, "Puerto");

        // Norte
        Node nodoB = new Node(110, 110, 30, 30, "B");
        Node nodoD = new Node(190, 110, 30, 30, "D");
        Node nodoE = new Node(270, 110, 30, 30, "E");

        // Centro
        Node nodoC = new Node(110, 200, 30, 30, "C");
        Node nodoF = new Node(190, 200, 30, 30, "F");
        Node nodoG = new Node(270, 200, 30, 30, "G");
        Node nodoCentro = new Node(360, 200, 30, 30, "Casa Central");

        // Sur
        Node nodoX = new Node(110, 290, 30, 30, "X");
        Node nodoY = new Node(190, 290, 30, 30, "Y");
        Node nodoZ = new Node(270, 290, 30, 30, "Z");

        // Aristas
        Arista aristaPuertoB = new Arista(getMedioX(nodoPuerto), getMedioY(nodoPuerto), getMedioX(nodoB), getMedioY(nodoB));
        Arista aristaPuertoC = new Arista(getMedioX(nodoPuerto), getMedioY(nodoPuerto), getMedioX(nodoC), getMedioY(nodoC));
        Arista aristaPuertoX = new Arista(getMedioX(nodoPuerto), getMedioY(nodoPuerto), getMedioX(nodoX), getMedioY(nodoX));

        Arista aristaBD = new Arista(getMedioX(nodoB), getMedioY(nodoB), getMedioX(nodoD), getMedioY(nodoD));
        Arista aristaBC = new Arista(getMedioX(nodoB), getMedioY(nodoB), getMedioX(nodoC), getMedioY(nodoC));

        Arista aristaCD = new Arista(getMedioX(nodoC), getMedioY(nodoC), getMedioX(nodoD), getMedioY(nodoD));
        Arista aristaCF = new Arista(getMedioX(nodoC), getMedioY(nodoC), getMedioX(nodoF), getMedioY(nodoF));

        Arista aristaXC = new Arista(getMedioX(nodoX), getMedioY(nodoX), getMedioX(nodoC), getMedioY(nodoC));
        Arista aristaXG = new Arista(getMedioX(nodoX), getMedioY(nodoX), getMedioX(nodoG), getMedioY(nodoG));
        Arista aristaXY = new Arista(getMedioX(nodoX), getMedioY(nodoX), getMedioX(nodoY), getMedioY(nodoY));

        Arista aristaDE = new Arista(getMedioX(nodoD), getMedioY(nodoD), getMedioX(nodoE), getMedioY(nodoE));
        Arista aristaDF = new Arista(getMedioX(nodoD), getMedioY(nodoD), getMedioX(nodoF), getMedioY(nodoF));

        Arista aristaFG = new Arista(getMedioX(nodoF), getMedioY(nodoF), getMedioX(nodoG), getMedioY(nodoG));
        Arista aristaFY = new Arista(getMedioX(nodoF), getMedioY(nodoF), getMedioX(nodoY), getMedioY(nodoY));

        Arista aristaYZ = new Arista(getMedioX(nodoY), getMedioY(nodoY), getMedioX(nodoZ), getMedioY(nodoZ));

        Arista aristaECentro = new Arista(getMedioX(nodoE), getMedioY(nodoE), getMedioX(nodoCentro), getMedioY(nodoCentro));

        Arista aristaGE = new Arista(getMedioX(nodoG), getMedioY(nodoG), getMedioX(nodoE), getMedioY(nodoE));
        Arista aristaGCentro = new Arista(getMedioX(nodoG), getMedioY(nodoG), getMedioX(nodoCentro), getMedioY(nodoCentro));

        Arista aristaZCentro = new Arista(getMedioX(nodoZ), getMedioY(nodoZ), getMedioX(nodoCentro), getMedioY(nodoCentro));


        // Dibujo las aristas
        g2.setColor(Color.BLACK);
        g2.draw(aristaPuertoB.getShape());
        g2.draw(aristaPuertoC.getShape());
        g2.draw(aristaPuertoX.getShape());
        g2.draw(aristaBD.getShape());
        g2.draw(aristaBC.getShape());
        g2.draw(aristaCD.getShape());
        g2.draw(aristaCF.getShape());
        g2.draw(aristaXC.getShape());
        g2.draw(aristaXG.getShape());
        g2.draw(aristaXY.getShape());
        g2.draw(aristaDE.getShape());
        g2.draw(aristaDF.getShape());
        g2.draw(aristaFG.getShape());
        g2.draw(aristaFY.getShape());
        g2.draw(aristaYZ.getShape());
        g2.draw(aristaECentro.getShape());
        g2.draw(aristaGE.getShape());
        g2.draw(aristaGCentro.getShape());
        g2.draw(aristaZCentro.getShape());

        // Dibujo los nodos
        draw(nodoPuerto, g2);
        draw(nodoB, g2);
        draw(nodoD, g2);
        draw(nodoE, g2);
        draw(nodoC, g2);
        draw(nodoF, g2);
        draw(nodoG, g2);
        draw(nodoCentro, g2);
        draw(nodoX, g2);
        draw(nodoY, g2);
        draw(nodoZ, g2);

        g2.setColor((Color.BLACK));
        g2.drawString(nodoPuerto.name, nodoPuerto.x, nodoPuerto.y);
        g2.drawString(nodoB.name, nodoB.x, nodoB.y);
        g2.drawString(nodoC.name, nodoC.x, nodoC.y);
        g2.drawString(nodoD.name, nodoD.x, nodoD.y);
        g2.drawString(nodoE.name, nodoE.x, nodoE.y);
        g2.drawString(nodoF.name, nodoF.x, nodoF.y);
        g2.drawString(nodoG.name, nodoG.x, nodoG.y);
        g2.drawString(nodoX.name, nodoX.x, nodoX.y);
        g2.drawString(nodoY.name, nodoY.x, nodoY.y);
        g2.drawString(nodoZ.name, nodoZ.x, nodoZ.y);
        g2.drawString(nodoCentro.name, nodoCentro.x, nodoCentro.y);
    }

    private void draw(Node nodo, Graphics2D g2) {
        if (sucursales.contains(nodo.name.toLowerCase())) {
            g2.setColor((Color.GREEN));
            g2.fill(nodo.getShape());
        } else {
            g2.setColor(Color.BLUE);
            g2.fill(nodo.getShape());
        }
    }

    public Integer getMedioX(Node n) {
        return n.x + (n.weight / 2);
    }

    public Integer getMedioY(Node n) {
        return n.y + (n.height / 2);
    }
}
