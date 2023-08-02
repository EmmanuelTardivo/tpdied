package com.tpdied.managers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.tpdied.controllers.RutaController;
import com.tpdied.controllers.SucursalController;
import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;
import jakarta.persistence.EntityManager;

public class OrdenProvisionManager {

    private RutaController rutaController;
    private SucursalController sucursalController;

    public OrdenProvisionManager(EntityManager entityManager) {
        rutaController = new RutaController(entityManager);
        sucursalController = new SucursalController(entityManager);
    }

    public List<List<RutaDTO>> getCaminos(OrdenProvisionDTO orden) {

        SucursalDTO sucursalDestino = orden.getSucursalDestino();
        Map<ProductoDTO, Integer> productosSolicitados = orden.getItemsProductoCantidad();
        List<SucursalDTO> sucursales = sucursalController.getSucursalesByEstado(true);
        sucursales.remove(sucursalDestino);
        sucursales = sucursales.stream()
                .filter(s -> satisfaceLaOrden(s.getListaProductoCantidadEnStock(), productosSolicitados))
                .collect(Collectors.toList());
        List<List<RutaDTO>> caminos = new ArrayList<>();
        for (SucursalDTO origen : sucursales) {
            List<RutaDTO> caminoActual = new ArrayList<>();
            encontrarCaminos(origen, sucursalDestino, new ArrayList<>(), caminoActual, caminos,
                    pasarAMinutos(orden.getLimiteTiempo()));
        }

        return caminos;
    }

    private boolean satisfaceLaOrden(Map<ProductoDTO, Integer> stock, Map<ProductoDTO, Integer> order) {
        Predicate<Map.Entry<ProductoDTO, Integer>> isSatisfied = entry -> {
            ProductoDTO producto = entry.getKey();
            int cantidadSolicitada = entry.getValue();
            return stock.containsKey(producto) && stock.get(producto) >= cantidadSolicitada;
        };

        return order.entrySet().stream().allMatch(isSatisfied);
    }

    private void encontrarCaminos(SucursalDTO actual, SucursalDTO destino,
            List<RutaDTO> caminoRecorrido, List<RutaDTO> caminoActual, List<List<RutaDTO>> resultado,
            int limiteTiempoEnMinutos) {

        if (actual.equals(destino)) {
            Integer duracionTotalMinutos = caminoActual.stream()
                    .mapToInt(r -> pasarAMinutos(r.getDuracionViaje())).sum();
            if (duracionTotalMinutos <= limiteTiempoEnMinutos) {
                resultado.add(new ArrayList<>(caminoActual));
            }
            return;
        }

        List<RutaDTO> rutasDestino = rutaController.getRutasBySucursalDestino(actual);
        for (RutaDTO ruta : rutasDestino) {
            if (!caminoRecorrido.contains(ruta)) {
                caminoActual.add(ruta);
                caminoRecorrido.add(ruta);
                encontrarCaminos(ruta.getSucursalDestino(), destino, caminoRecorrido, caminoActual, resultado,
                        limiteTiempoEnMinutos);
                caminoActual.remove(ruta);
                caminoRecorrido.remove(ruta);
            }
        }
    }

    private Integer pasarAMinutos(Duration tiempo) {
        return (int) tiempo.toMinutes();
    }
}