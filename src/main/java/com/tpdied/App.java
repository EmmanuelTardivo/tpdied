package com.tpdied;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.tpdied.controllers.OrdenProvisionController;
import com.tpdied.controllers.ProductoController;
import com.tpdied.controllers.RutaController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.forms.OrdenProvisionForm;
import com.tpdied.forms.ProductoForm;
import com.tpdied.forms.RutaForm;
import com.tpdied.forms.SucursalForm;
import com.tpdied.gui.PrincipalGUI;
import com.tpdied.managers.OrdenProvisionManager;
import com.tpdied.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        inicializarDB();
        inicializarGUI();
    }

    private static void inicializarGUI() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                new PrincipalGUI().setVisible(true);
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PrincipalGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void inicializarDB() {
        EntityManagerUtil.limpiarDB();

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        SucursalController sc = new SucursalController(entityManager);
        RutaController rc = new RutaController(entityManager);
        ProductoController pc = new ProductoController(entityManager);
        OrdenProvisionController oc = new OrdenProvisionController(entityManager);
        SucursalDTO sPuerto = null;
        SucursalDTO sB = null;
        SucursalDTO sC = null;
        SucursalDTO sD = null;
        SucursalDTO sE = null;
        SucursalDTO sF = null;
        SucursalDTO sG = null;
        SucursalDTO sX = null;
        SucursalDTO sY = null;
        SucursalDTO sZ = null;
        SucursalDTO sCasaCentral = null;

        List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
        try {
            sPuerto = SucursalForm.validarSucursal("Puerto", "08:00", "20:00", true);
            sB = SucursalForm.validarSucursal("B", "09:00", "20:00", true);
            sC = SucursalForm.validarSucursal("C", "10:00", "19:00", true);
            sD = SucursalForm.validarSucursal("D", "09:00", "18:00", true);
            sE = SucursalForm.validarSucursal("E", "10:00", "19:00", true);
            sF = SucursalForm.validarSucursal("F", "10:00", "19:00", true);
            sG = SucursalForm.validarSucursal("G", "10:00", "19:00", true);
            sX = SucursalForm.validarSucursal("X", "10:00", "19:00", true);
            sY = SucursalForm.validarSucursal("Y", "10:00", "19:00", true);
            sZ = SucursalForm.validarSucursal("Z", "10:00", "19:00", true);
            sCasaCentral = SucursalForm.validarSucursal("Casa Central", "08:00", "18:00", true);
            sucursales = List.of(sPuerto, sB, sC, sD, sE, sF, sG, sX, sY, sZ, sCasaCentral);
            sucursales.forEach(s -> sc.addSucursal(s));
        } catch (Exception e) {
            System.out.println("Error al cargar sucursales: " + e.getMessage());
        }

        ProductoDTO p1 = null;
        ProductoDTO p2 = null;
        ProductoDTO p3 = null;
        ProductoDTO p4 = null;
        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
        try {
            p1 = ProductoForm.validarProducto("Producto 1", "Descripcion 1", "100.0", "5.0");
            p2 = ProductoForm.validarProducto("Producto 2", "Descripcion 2", "50.0", "10.0");
            p3 = ProductoForm.validarProducto("Producto 3", "Descripcion 3", "200.0", "2.5");
            p4 = ProductoForm.validarProducto("Producto 4", "Descripcion 4", "100.5", "7.5");
            productos = List.of(p1, p2, p3, p4);
            productos.forEach(p -> pc.addProducto(p));
        } catch (Exception e) {
            System.out.println("Error al cargar Productos: " + e.getMessage());
        }

        try {
            sc.setStockProducto(sc.getSucursalById(1), pc.getProductoById(1), 5);
            sc.setStockProducto(sc.getSucursalById(1), pc.getProductoById(2), 10);
            sc.setStockProducto(sc.getSucursalByName("B"), pc.getProductoByName("Producto 1"), 20);
            sc.setStockProducto(sc.getSucursalByName("B"), pc.getProductoByName("Producto 2"), 20);
        } catch (Exception e) {
            System.out.println("Error al cargar Stocks en sucursales: " + e.getMessage());
        }

        RutaDTO r1 = null;
        RutaDTO r2 = null;
        RutaDTO r3 = null;
        RutaDTO r4 = null;
        RutaDTO r5 = null;
        RutaDTO r6 = null;
        RutaDTO r7 = null;
        RutaDTO r8 = null;
        RutaDTO r9 = null;
        RutaDTO r10 = null;
        RutaDTO r11 = null;
        RutaDTO r12 = null;
        RutaDTO r13 = null;
        RutaDTO r14 = null;
        RutaDTO r15 = null;
        RutaDTO r16 = null;
        RutaDTO r17 = null;
        RutaDTO r18 = null;
        RutaDTO r19 = null;

        List<RutaDTO> rutas = new ArrayList<RutaDTO>();
        try {
            r1 = RutaForm.validarRuta("600", sc.getSucursalByName("B"), sc.getSucursalByName("C"), "02:30", true);
            r2 = RutaForm.validarRuta("600", sc.getSucursalByName("D"), sc.getSucursalByName("F"), "02:00", true);
            r3 = RutaForm.validarRuta("600", sc.getSucursalByName("X"), sc.getSucursalByName("G"), "02:45", true);
            r4 = RutaForm.validarRuta("600", sc.getSucursalByName("F"), sc.getSucursalByName("Y"), "01:00", true);
            r5 = RutaForm.validarRuta("500", sc.getSucursalByName("Puerto"), sc.getSucursalByName("C"), "02:00", true);
            r6 = RutaForm.validarRuta("500", sc.getSucursalByName("C"), sc.getSucursalByName("F"), "02:00", true);
            r7 = RutaForm.validarRuta("500", sc.getSucursalByName("F"), sc.getSucursalByName("G"), "01:30", true);
            r8 = RutaForm.validarRuta("400", sc.getSucursalByName("G"), sc.getSucursalByName("E"), "02:30", true);
            r9 = RutaForm.validarRuta("300", sc.getSucursalByName("Puerto"), sc.getSucursalByName("B"), "03:00", true);
            r10 = RutaForm.validarRuta("300", sc.getSucursalByName("B"), sc.getSucursalByName("D"), "01:00", true);
            r11 = RutaForm.validarRuta("300", sc.getSucursalByName("G"), sc.getSucursalByName("Casa Central"), "04:00",
                    true);
            r12 = RutaForm.validarRuta("200", sc.getSucursalByName("Puerto"), sc.getSucursalByName("X"), "02:30", true);
            r13 = RutaForm.validarRuta("200", sc.getSucursalByName("X"), sc.getSucursalByName("Y"), "03:30", true);
            r14 = RutaForm.validarRuta("200", sc.getSucursalByName("Y"), sc.getSucursalByName("Z"), "01:00", true);
            r15 = RutaForm.validarRuta("100", sc.getSucursalByName("Z"), sc.getSucursalByName("Casa Central"), "01:30",
                    true);
            r16 = RutaForm.validarRuta("100", sc.getSucursalByName("X"), sc.getSucursalByName("C"), "02:15", true);
            r17 = RutaForm.validarRuta("100", sc.getSucursalByName("C"), sc.getSucursalByName("D"), "03:15", true);
            r18 = RutaForm.validarRuta("100", sc.getSucursalByName("D"), sc.getSucursalByName("E"), "02:45", true);
            r19 = RutaForm.validarRuta("100", sc.getSucursalByName("E"), sc.getSucursalByName("Casa Central"), "01:00",
                    true);

            rutas = List.of(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);
            rutas.forEach(r -> rc.addRuta(r));
        } catch (Exception e) {
            System.out.println("Error al cargar rutas: " + e.getMessage());
        }

        OrdenProvisionDTO o1 = null;
        OrdenProvisionDTO o2 = null;

        Map<ProductoDTO, Integer> listaProductos = new HashMap<ProductoDTO, Integer>();

        try {
            listaProductos.put(pc.getProductoById(1), 4);
            listaProductos.put(pc.getProductoById(2), 8);
            o1 = OrdenProvisionForm.validarOrdenProvision(sc.getSucursalByName("C"), "48:30", listaProductos);
            o2 = OrdenProvisionForm.validarOrdenProvision(sc.getSucursalByName("X"), "32:00", listaProductos);
            oc.addOrdenProvision(o1);
            oc.addOrdenProvision(o2);
        } catch (Exception e) {
            System.out.println("Error al cargar ordenes: " + e.getMessage());
        }
    }

    private static void pruebasIniciales(){
        EntityManagerUtil.limpiarDB();

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        SucursalController sc = new SucursalController(entityManager);
        RutaController rc = new RutaController(entityManager);
        ProductoController pc = new ProductoController(entityManager);
        OrdenProvisionController oc = new OrdenProvisionController(entityManager);
        SucursalDTO sPuerto = null;
        SucursalDTO sB = null;
        SucursalDTO sC = null;
        SucursalDTO sD = null;
        SucursalDTO sE = null;
        SucursalDTO sF = null;
        SucursalDTO sG = null;
        SucursalDTO sX = null;
        SucursalDTO sY = null;
        SucursalDTO sZ = null;
        SucursalDTO sCasaCentral = null;

        List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
        try {
            sPuerto = SucursalForm.validarSucursal("Puerto", "08:00", "20:00", true);
            sB = SucursalForm.validarSucursal("B", "09:00", "20:00", true);
            sC = SucursalForm.validarSucursal("C", "10:00", "19:00", true);
            sD = SucursalForm.validarSucursal("D", "09:00", "18:00", true);
            sE = SucursalForm.validarSucursal("E", "10:00", "19:00", true);
            sF = SucursalForm.validarSucursal("F", "10:00", "19:00", true);
            sG = SucursalForm.validarSucursal("G", "10:00", "19:00", true);
            sX = SucursalForm.validarSucursal("X", "10:00", "19:00", true);
            sY = SucursalForm.validarSucursal("Y", "10:00", "19:00", true);
            sZ = SucursalForm.validarSucursal("Z", "10:00", "19:00", true);
            sCasaCentral = SucursalForm.validarSucursal("Casa Central", "08:00", "18:00", true);
            sucursales = List.of(sPuerto, sB, sC, sD, sE, sF, sG, sX, sY, sZ, sCasaCentral);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

        sucursales.forEach(s -> sc.addSucursal(s));

        System.out.println(sc.getAllSucursales());

        sc.deleteSucursal(sc.getSucursalById(4));

        System.out.println(sc.getAllSucursales());

        sc.addSucursal(sD);

        System.out.println(sc.getAllSucursales());

        SucursalDTO sAux = new SucursalDTO();
        sAux.setNombre("B");

        try {
            sc.addSucursal(sAux);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sc.getSucursalesByEstado(true));

        System.out.println(sc.getSucursalesByEstado(false));

        ProductoDTO p1 = null;
        ProductoDTO p2 = null;
        ProductoDTO p3 = null;
        ProductoDTO p4 = null;
        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
        try {
            p1 = ProductoForm.validarProducto("Producto 1", "Descripcion 1", "100.0", "5.0");
            p2 = ProductoForm.validarProducto("Producto 2", "Descripcion 2", "50.0", "10.0");
            p3 = ProductoForm.validarProducto("Producto 3", "Descripcion 3", "200.0", "2.5");
            p4 = ProductoForm.validarProducto("Producto 4", "Descripcion 4", "100.5", "7.5");
            productos = List.of(p1, p2, p3, p4);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        productos.forEach(p -> pc.addProducto(p));

        System.out.println(pc.getAllProductos());

        sc.setStockProducto(sc.getSucursalById(1), pc.getProductoById(1), 5);
        sc.setStockProducto(sc.getSucursalById(1), pc.getProductoById(2), 10);

        System.out.println(sc.getStockProductos(sc.getSucursalById(1)));

        System.out.println(sc.getStockProducto(sc.getSucursalById(1), pc.getProductoById(1)));

        System.out.println(sc.getStockProducto(sc.getSucursalById(1), pc.getProductoById(3)));

        RutaDTO r1 = null;
        RutaDTO r2 = null;
        RutaDTO r3 = null;
        RutaDTO r4 = null;
        RutaDTO r5 = null;
        RutaDTO r6 = null;
        RutaDTO r7 = null;
        RutaDTO r8 = null;
        RutaDTO r9 = null;
        RutaDTO r10 = null;
        RutaDTO r11 = null;
        RutaDTO r12 = null;
        RutaDTO r13 = null;
        RutaDTO r14 = null;
        RutaDTO r15 = null;
        RutaDTO r16 = null;
        RutaDTO r17 = null;
        RutaDTO r18 = null;
        RutaDTO r19 = null;

        List<RutaDTO> rutas = new ArrayList<RutaDTO>();
        try {
            r1 = RutaForm.validarRuta("600", sc.getSucursalByName("B"), sc.getSucursalByName("C"), "02:30", true);
            r2 = RutaForm.validarRuta("600", sc.getSucursalByName("D"), sc.getSucursalByName("F"), "02:00", true);
            r3 = RutaForm.validarRuta("600", sc.getSucursalByName("X"), sc.getSucursalByName("G"), "02:45", true);
            r4 = RutaForm.validarRuta("600", sc.getSucursalByName("F"), sc.getSucursalByName("Y"), "01:00", true);
            r5 = RutaForm.validarRuta("500", sc.getSucursalByName("Puerto"), sc.getSucursalByName("C"), "02:00", true);
            r6 = RutaForm.validarRuta("500", sc.getSucursalByName("C"), sc.getSucursalByName("F"), "02:00", true);
            r7 = RutaForm.validarRuta("500", sc.getSucursalByName("F"), sc.getSucursalByName("G"), "01:30", true);
            r8 = RutaForm.validarRuta("400", sc.getSucursalByName("G"), sc.getSucursalByName("E"), "02:30", true);
            r9 = RutaForm.validarRuta("300", sc.getSucursalByName("Puerto"), sc.getSucursalByName("B"), "03:00", true);
            r10 = RutaForm.validarRuta("300", sc.getSucursalByName("B"), sc.getSucursalByName("D"), "01:00", true);
            r11 = RutaForm.validarRuta("300", sc.getSucursalByName("G"), sc.getSucursalByName("Casa Central"), "04:00",
                    true);
            r12 = RutaForm.validarRuta("200", sc.getSucursalByName("Puerto"), sc.getSucursalByName("X"), "02:30", true);
            r13 = RutaForm.validarRuta("200", sc.getSucursalByName("X"), sc.getSucursalByName("Y"), "03:30", true);
            r14 = RutaForm.validarRuta("200", sc.getSucursalByName("Y"), sc.getSucursalByName("Z"), "01:00", true);
            r15 = RutaForm.validarRuta("100", sc.getSucursalByName("Z"), sc.getSucursalByName("Casa Central"), "01:30",
                    true);
            r16 = RutaForm.validarRuta("100", sc.getSucursalByName("X"), sc.getSucursalByName("C"), "02:15", true);
            r17 = RutaForm.validarRuta("100", sc.getSucursalByName("C"), sc.getSucursalByName("D"), "03:15", true);
            r18 = RutaForm.validarRuta("100", sc.getSucursalByName("D"), sc.getSucursalByName("E"), "02:45", true);
            r19 = RutaForm.validarRuta("100", sc.getSucursalByName("E"), sc.getSucursalByName("Casa Central"), "01:00",
                    true);

            rutas = List.of(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);

        } catch (Exception e) {
            System.out.println("error rutas: " + e.getMessage());
        }
        rutas.forEach(r -> rc.addRuta(r));

        System.out.println(rc.getAllRutas());

        OrdenProvisionDTO o1 = null;
        OrdenProvisionDTO o2 = null;

        Map<ProductoDTO, Integer> listaProductos = new HashMap<ProductoDTO, Integer>();
        listaProductos.put(pc.getProductoByName("Producto 1"), 4);
        listaProductos.put(pc.getProductoByName("Producto 2"), 8);

        try {
            o1 = OrdenProvisionForm.validarOrdenProvision(sc.getSucursalByName("C"), "48:30", listaProductos);
            o2 = OrdenProvisionForm.validarOrdenProvision(sc.getSucursalByName("X"), "32:00", listaProductos);
        } catch (Exception e) {
            System.out.println("error ordenes: " + e.getMessage());
        }

        oc.addOrdenProvision(o1);
        oc.addOrdenProvision(o2);
        List<OrdenProvisionDTO> ordenes = oc.getOrdenesProvisionPendientes();
        oc.setOrdenEnProceso(ordenes.get(1));
        System.out.println();
        System.out.println(oc.getAllOrdenesProvision());
        System.out.println(oc.getOrdenesProvisionPendientes());

        System.out.println();

        sc.setStockProducto(sc.getSucursalByName("B"), pc.getProductoByName("Producto 1"), 20);
        sc.setStockProducto(sc.getSucursalByName("B"), pc.getProductoByName("Producto 2"), 20);

        OrdenProvisionManager om = new OrdenProvisionManager(entityManager);
        List<List<RutaDTO>> listaCaminos = om.getCaminosPosibles(o1);
        System.out.println("Caminos Posibles:");
        System.out.println(listaCaminos);
        System.out.println();
        System.out.println("Rutas del camino 1:");
        System.out.println(listaCaminos.get(0));
        System.out.println("Sucursales del camino 1:");
        System.out.println(om.getSucursalesDeCamino(listaCaminos.get(0)));
        System.out.println("Duracion Total:");
        System.out.println(om.getTiempoTotal(listaCaminos.get(0)));

        System.out.println();
        System.out.println("Rutas del camino 2:");
        System.out.println(listaCaminos.get(1));
        System.out.println("Sucursales del camino 2:");
        System.out.println(om.getSucursalesDeCamino(listaCaminos.get(1)));
        System.out.println("Duracion Total:");
        System.out.println(om.getTiempoTotal(listaCaminos.get(1)));

        System.out.println();
        System.out.println("Rutas del camino 3:");
        System.out.println(listaCaminos.get(2));
        System.out.println("Sucursales del camino 3:");
        System.out.println(om.getSucursalesDeCamino(listaCaminos.get(2)));
        System.out.println("Duracion Total:");
        System.out.println(om.getTiempoTotal(listaCaminos.get(2)));

        System.out.println();
        System.out.println("Rutas del camino 4:");
        System.out.println(listaCaminos.get(3));
        System.out.println("Sucursales del camino 4:");
        System.out.println(om.getSucursalesDeCamino(listaCaminos.get(3)));
        System.out.println("Duracion Total:");
        System.out.println(om.getTiempoTotal(listaCaminos.get(3)));

        System.out.println();
        System.out.println("Flujo Máximo:");
        System.out
                .println(om.calcularFlujoMaximo(sc.getSucursalByName("Puerto"), sc.getSucursalByName("Casa Central")));

        System.out.println("Page Rank");
        System.out.println(om.calcularPageRank(0.5, 1, 1));
    }
}
