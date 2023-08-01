package com.tpdied;

import java.util.ArrayList;
import java.util.List;

import com.tpdied.controllers.OrdenProvisionController;
import com.tpdied.controllers.ProductoController;
import com.tpdied.controllers.RutaController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.forms.SucursalForm;
import com.tpdied.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManagerUtil.limpiarDB();

        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        SucursalController sc = new SucursalController(entityManager);
        RutaController rc = new RutaController(entityManager);
        ProductoController pc = new ProductoController(entityManager);
        OrdenProvisionController oc = new OrdenProvisionController(entityManager);
        SucursalDTO s1, s2, s3, s4 = null;
        List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
        try {
            s1 = SucursalForm.validarSucursal("Puerto", "08:00", "20:00", true);
            s2 = SucursalForm.validarSucursal("B", "09:00", "20:00", true);
            s3 = SucursalForm.validarSucursal("C", "10:00", "19:00", false);
            s4 = SucursalForm.validarSucursal("D", "09:00", "18:00", true);
            sucursales = List.of(s1, s2, s3, s4);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

        sucursales.forEach(s -> sc.addSucursal(s));

        System.out.println(sc.getAllSucursales());

        sc.deleteSucursal(sc.getSucursalById(4));

        System.out.println(sc.getAllSucursales());

        sc.addSucursal(s4);

        System.out.println(sc.getAllSucursales());

        SucursalDTO s5 = new SucursalDTO();
        s5.setNombre("B");

        try {
            sc.addSucursal(s5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sc.getSucursalesByEstado(true));

        System.out.println(sc.getSucursalesByEstado(false));

        ProductoDTO p1 = new ProductoDTO();
        p1.setNombre("chanclas");
        p1.setDescripcion("las mejores chanclas");
        p1.setPeso(4.0);
        p1.setPrecio(500.0);

        pc.addProducto(p1);

        p1 = pc.getProductoById(1);

        SucursalDTO s1copia = sc.getSucursalById(1);
        sc.setStockProducto(s1copia, p1, 5);

        System.out.println(p1);

        s1copia = sc.getSucursalById(1);
        System.out.println(s1copia.getListaProductoCantidadEnStock());

    }
}
