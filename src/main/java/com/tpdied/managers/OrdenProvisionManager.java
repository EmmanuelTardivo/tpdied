package com.tpdied.managers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public Set<List<RutaDTO>> getCaminosPosibles(OrdenProvisionDTO orden) {
        
        Set<List<RutaDTO>> caminos = new HashSet<>();
        SucursalDTO destino = orden.getSucursalDestino();
        Integer limiteTiempoEnMinutos = pasarAMinutos(orden.getLimiteTiempo());
        List<SucursalDTO> sucursalesCandidatas = getOrigenesPosibles(orden);
        Double pesoOrden = orden.getPeso();

        for (SucursalDTO candidata : sucursalesCandidatas) {
            caminos.addAll(getCaminos(candidata, destino, limiteTiempoEnMinutos, pesoOrden));
        }
        return caminos;

    }

    private List<List<RutaDTO>> getCaminos(SucursalDTO origen, SucursalDTO destino, int limiteTiempoEnMinutos, double pesoOrden) {
        List<List<RutaDTO>> caminos = new ArrayList<>();
        List<RutaDTO> caminoActual = new ArrayList<>();
        getCaminosDesdeOrigen(origen, destino, caminoActual, caminos, limiteTiempoEnMinutos, 0, pesoOrden);
        return caminos;
    }

    private void getCaminosDesdeOrigen(SucursalDTO actual, SucursalDTO destino,
            List<RutaDTO> caminoActual, List<List<RutaDTO>> caminos,
            int limiteTiempoEnMinutos, int tiempoTranscurrido, double pesoOrden) {

        if (actual.equals(destino)) { // verificar si se alcanzó el destino
            caminos.add(new ArrayList<>(caminoActual)); // Se encontró un camino válido, se agrega a la lista de caminos
            return;
        }

        List<RutaDTO> rutasDesdeActual = rutaController.getRutasBySucursalOrigen(actual).stream()
                .filter(r -> r.getEstado()).toList();

        for (RutaDTO ruta : rutasDesdeActual) {
            double pesoMaximoRuta = ruta.getCapacidadEnKilos();
            int tiempoActualizado = tiempoTranscurrido + pasarAMinutos(ruta.getDuracionViaje());
            ;
            // Verificar si se excede el límite de tiempo antes de explorar la ruta y si el
            // peso se puede transportar
            if (tiempoActualizado <= limiteTiempoEnMinutos && pesoMaximoRuta >= pesoOrden) {
                caminoActual.add(ruta);
                getCaminosDesdeOrigen(ruta.getSucursalDestino(), destino, caminoActual, caminos,
                        limiteTiempoEnMinutos, tiempoActualizado, pesoOrden);
                caminoActual.remove(ruta);
            }
        }
    }

    private List<SucursalDTO> getOrigenesPosibles(OrdenProvisionDTO orden) {
        List<SucursalDTO> sucursales = sucursalController.getSucursalesByEstado(true);
        sucursales.remove(orden.getSucursalDestino());
        return sucursales.stream()
                .filter(s -> satisfaceLaOrden(s.getListaProductoCantidadEnStock(), orden.getItemsProductoCantidad()))
                .collect(Collectors.toList());
    }

    private boolean satisfaceLaOrden(Map<ProductoDTO, Integer> stock, Map<ProductoDTO, Integer> order) {
        Predicate<Map.Entry<ProductoDTO, Integer>> isSatisfied = entry -> {
            ProductoDTO producto = entry.getKey();
            int cantidadSolicitada = entry.getValue();
            return stock.containsKey(producto) && stock.get(producto) >= cantidadSolicitada;
        };

        return order.entrySet().stream().allMatch(isSatisfied);
    }

    private Integer pasarAMinutos(Duration tiempo) {
        return (int) tiempo.toMinutes();
    }

    
}