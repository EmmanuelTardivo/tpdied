package com.tpdied;

import java.time.LocalTime;

import com.tpdied.controllers.SucursalController;
import com.tpdied.dao.ProductoDao;
import com.tpdied.dao.RutaDao;
import com.tpdied.dao.SucursalDao;
import com.tpdied.models.Producto;
import com.tpdied.models.Ruta;
import com.tpdied.models.Sucursal;
import com.tpdied.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        SucursalDao sDao = new SucursalDao(entityManager);
        RutaDao rDao = new RutaDao(entityManager);
        ProductoDao pDao = new ProductoDao(entityManager);

        Sucursal s1 = new Sucursal();
        s1.setEstado(true);
        s1.setHoraApertura(LocalTime.parse("08:00"));
        s1.setHoraCierre(LocalTime.parse("20:00"));
        s1.setNombre("Sucursal 1");
        System.out.println(s1);
        sDao.save(s1);
        System.out.println(sDao.getById(1));

        Sucursal s2 = new Sucursal();
        s2.setEstado(true);
        s2.setHoraApertura(LocalTime.parse("08:00"));
        s2.setHoraCierre(LocalTime.parse("08:00"));
        s2.setNombre("Sucursal 2");
        System.out.println(s2);
        sDao.save(s2);
        System.out.println(sDao.getById(2));
        System.out.println(sDao.getAll());

        Ruta r1 = new Ruta();
        r1.setEstado(true);
        r1.setCapacidadEnKilos(50.0);
        r1.setSucursalDestino(s1);
        r1.setSucursalOrigen(s2);
        r1.setDuracionViaje(LocalTime.parse("08:00"));
        System.out.println(r1);
        rDao.save(r1);
        System.out.println(rDao.getById(1));
        System.out.println(rDao.getAll());

        Producto p1 = new Producto();
        p1.setDescripcion("chancletas!!");
        p1.setNombre("chancletas");
        p1.setPeso(0.70);
        p1.setPrecio(4999.99);
        System.out.println(p1);
        pDao.save(p1);
        System.out.println(pDao.getById(1));
        
        s1.getListaProductoCantidadEnStock();
        s1.getCantidadProductoEnStock(p1);
        s1.updateProductoCantidadEnStock(p1, 10);
        s1.getListaProductoCantidadEnStock();
        s1.getCantidadProductoEnStock(p1);

        sDao.update(s1);
        
        SucursalController sc = new SucursalController(entityManager);
        System.out.println(sc.getSucursalById(1));
        System.out.println(sc.getAllSucursales());
        System.out.println(sc.getSucursalById(3));
        
    }
}
