package com.tpdied.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tpdied.models.Producto;
import com.tpdied.models.Ruta;
import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

public class EntityManagerUtilTest {

    private static EntityManager entityManager;

    @BeforeAll
    public static void setUp() {
        entityManager = EntityManagerUtil.getEntityManager();
    }

    @AfterAll
    public static void tearDown() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    @Test
    public void testGetEntityManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testLimpiarDB() {

        insertarDatosPrueba(entityManager);

        assertFalse(isDBEmpty(entityManager));

        // Limpiar la base de datos
        EntityManagerUtil.limpiarDB();

        assertTrue(isDBEmpty(entityManager));
    }

    private boolean isDBEmpty(EntityManager entityManager) {

        return isTablaEmpty(entityManager, "Producto") &&
                isTablaEmpty(entityManager, "OrdenProvision") &&
                isTablaItemsEmpty(entityManager) &&
                isTablaEmpty(entityManager, "Ruta") &&
                isTablaStockEmpty(entityManager) &&
                isTablaEmpty(entityManager, "Sucursal");
    }

    private boolean isTablaEmpty(EntityManager entityManager, String tableName) {
        Query query = entityManager.createQuery("SELECT COUNT(t) FROM " + tableName + " t", Long.class);
        Long count = (Long) query.getSingleResult();
        return count == 0L;
    }

    private boolean isTablaStockEmpty(EntityManager entityManager) {
        try {
            Long count = entityManager
                    .createQuery("SELECT COUNT(s) FROM Sucursal s WHERE s.listaProductoCantidadEnStock IS EMPTY",
                            Long.class)
                    .getSingleResult();
            return count == 0L;
        } catch (NoResultException e) {
            return true;
        }
    }

    private boolean isTablaItemsEmpty(EntityManager entityManager) {
        try {
            Long count = entityManager
                    .createQuery("SELECT COUNT(o) FROM OrdenProvision o WHERE o.itemsProductoCantidad IS EMPTY",
                            Long.class)
                    .getSingleResult();
            return count == 0L;
        } catch (NoResultException e) {
            return true;
        }
    }

    private void insertarDatosPrueba(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        // Insertar datos en la tabla Sucursal
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setNombre("Sucursal A");
        sucursal1.setHoraApertura(LocalTime.of(8, 0));
        sucursal1.setHoraCierre(LocalTime.of(18, 0));
        sucursal1.setEstado(true);
        entityManager.persist(sucursal1);

        // Insertar datos en la tabla Producto
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setDescripcion("Descripción del Producto 1");
        producto1.setPeso(1.5);
        producto1.setPrecio(10.0);
        entityManager.persist(producto1);

        // Insertar datos en la tabla Ruta
        Ruta ruta1 = new Ruta();
        ruta1.setCapacidadEnKilos(1000.0);
        ruta1.setDuracionViaje(LocalTime.of(4, 30));
        ruta1.setEstado(true);
        ruta1.setSucursalOrigen(sucursal1);
        ruta1.setSucursalDestino(sucursal1);
        entityManager.persist(ruta1);

        entityManager.getTransaction().commit();
    }
}
