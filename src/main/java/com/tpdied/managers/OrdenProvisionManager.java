package com.tpdied.managers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<List<RutaDTO>> getCaminosPosibles(OrdenProvisionDTO orden) {

        List<List<RutaDTO>> caminos = new ArrayList<>();
        SucursalDTO destino = orden.getSucursalDestino();
        Integer limiteTiempoEnMinutos = pasarAMinutos(orden.getLimiteTiempo());
        List<SucursalDTO> sucursalesCandidatas = getOrigenesPosibles(orden);
        Double pesoOrden = orden.getPeso();

        for (SucursalDTO candidata : sucursalesCandidatas) {
            caminos.addAll(getCaminos(candidata, destino, limiteTiempoEnMinutos, pesoOrden));
        }
        return caminos;

    }

    private List<List<RutaDTO>> getCaminos(SucursalDTO origen, SucursalDTO destino, int limiteTiempoEnMinutos,
            double pesoOrden) {
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

    public double calcularFlujoMaximo(SucursalDTO origen, SucursalDTO sumidero) {
        double flujoMaximo = 0;
        List<RutaDTO> rutasOperativas = rutaController.getRutasByEstado(true);

        // Inicialmente, asignar un flujo de 0 a todas las rutas operativas
        for (RutaDTO ruta : rutasOperativas) {
            ruta.setFlujo(0.0);
        }

        // Continuar buscando caminos de aumento y aumentando el flujo máximo
        List<RutaDTO> caminoDeAumento;
        while ((caminoDeAumento = encontrarCaminoDeAumento(rutasOperativas, origen, sumidero)) != null) {
            double flujoDeAumento = obtenerFlujoDeAumento(caminoDeAumento);
            aumentarFlujoEnCamino(caminoDeAumento, flujoDeAumento);
            flujoMaximo += flujoDeAumento;
        }

        return flujoMaximo;
    }

    private List<RutaDTO> encontrarCaminoDeAumento(List<RutaDTO> rutasOperativas, SucursalDTO origen,
            SucursalDTO sumidero) {
        Set<SucursalDTO> visitados = new HashSet<>();
        List<RutaDTO> camino = new ArrayList<>();
        if (DFS(origen, sumidero, visitados, camino, rutasOperativas)) {
            return camino;
        }
        return null;
    }

    private boolean DFS(SucursalDTO actual, SucursalDTO sumidero, Set<SucursalDTO> visitados, List<RutaDTO> camino,
            List<RutaDTO> rutasOperativas) {
        visitados.add(actual);

        if (actual.equals(sumidero)) {
            return true;
        }

        for (RutaDTO ruta : rutasOperativas) {
            if (ruta.getSucursalOrigen().equals(actual) && !visitados.contains(ruta.getSucursalDestino())
                    && ruta.getCapacidadEnKilos() - ruta.getFlujo() > 0) {
                camino.add(ruta);
                if (DFS(ruta.getSucursalDestino(), sumidero, visitados, camino, rutasOperativas)) {
                    return true;
                }
                camino.remove(ruta);
            }
        }
        return false;
    }

    private double obtenerFlujoDeAumento(List<RutaDTO> caminoDeAumento) {
        double flujoDeAumento = Double.MAX_VALUE; // Inicializar el flujo de aumento con un valor grande
        for (RutaDTO ruta : caminoDeAumento) {
            double capacidadResidual = ruta.getCapacidadEnKilos() - ruta.getFlujo();
            flujoDeAumento = Math.min(flujoDeAumento, capacidadResidual);
        }
        return flujoDeAumento;
    }

    private void aumentarFlujoEnCamino(List<RutaDTO> caminoDeAumento, double flujoDeAumento) {
        for (RutaDTO ruta : caminoDeAumento) {
            ruta.setFlujo(ruta.getFlujo() + flujoDeAumento);
        }
    }

    public Map<SucursalDTO, Double> calcularPageRank(double factorAmortiguacion, int iteraciones, double tolerancia) {
        Map<SucursalDTO, Double> pageRanks = new HashMap<>();
        Map<SucursalDTO, Integer> gradoSaliente = new HashMap<>();
        List<SucursalDTO> sucursalesOperativas = sucursalController.getSucursalesByEstado(true);
        List<RutaDTO> rutasOperativas = rutaController.getRutasByEstado(true);
        sucursalesOperativas.forEach(s -> gradoSaliente.put(s, getNumEnlacesSalientes(rutasOperativas, s)));
        Double diferenciaMaxima = -Double.MAX_VALUE;
        Double diferenciaActual = -Double.MAX_VALUE;
        int numIteraciones = 0;
        // Inicializar el PageRank de todas las sucursales con 1
        for (SucursalDTO sucursal : sucursalesOperativas) {
            pageRanks.put(sucursal, 1.0);
        }

        // Calcular el PageRank iterativamente
        while (numIteraciones < iteraciones && diferenciaMaxima < tolerancia) {
            Map<SucursalDTO, Double> newPageRanks = new HashMap<>();

            // Calcular la suma de los PageRanks de las sucursales vecinas
            for (SucursalDTO sucursal : sucursalesOperativas) {
                double pageRankVecina = 0;
                for (RutaDTO ruta : rutasOperativas) {
                    if (ruta.getSucursalDestino().equals(sucursal)) {
                        SucursalDTO vecina = ruta.getSucursalOrigen();
                        double pageRankVecino = pageRanks.get(vecina);
                        pageRankVecina += pageRankVecino / gradoSaliente.get(vecina);
                    }
                }
                newPageRanks.put(sucursal, pageRankVecina);
            }

            // Actualizar el PageRank de cada sucursal con la fórmula
            for (SucursalDTO sucursal : sucursalesOperativas) {
                double nuevoPageRank = (1 - factorAmortiguacion) + factorAmortiguacion * newPageRanks.get(sucursal);
                diferenciaActual = Math.abs(nuevoPageRank - pageRanks.get(sucursal));
                diferenciaMaxima = Math.max(diferenciaMaxima, diferenciaActual);
                pageRanks.put(sucursal, nuevoPageRank);
            }
            numIteraciones++;
        }
        return pageRanks;
    }

    private int getNumEnlacesSalientes(List<RutaDTO> rutas, SucursalDTO sucursal) {
        return (int) rutas.stream()
                .filter(ruta -> ruta.getSucursalOrigen().equals(sucursal))
                .count();
    }

    public List<SucursalDTO> getSucursalesDeCamino(List<RutaDTO> rutas) {
        return rutas.stream()
                .flatMap(ruta -> Stream.of(ruta.getSucursalOrigen(), ruta.getSucursalDestino()))
                .collect(Collectors.toList());
    }

    public String getTiempoTotal(List<RutaDTO> rutas) {
        return formatDuration(rutas.stream()
                    .map(ruta -> ruta.getDuracionViaje())
                    .reduce(Duration.ZERO, Duration::plus));
    }

    private String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
    
        return String.format("%02d:%02d", hours, minutes);
    }
}