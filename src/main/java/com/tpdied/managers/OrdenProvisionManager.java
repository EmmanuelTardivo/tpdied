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

/**
 * Esta clase se encarga de gestionar la provisión de órdenes y calcular flujos
 * máximos en rutas.
 */
public class OrdenProvisionManager {

    private RutaController rutaController;
    private SucursalController sucursalController;

    /**
     * Constructor de la clase.
     * 
     * @param entityManager El EntityManager que se utilizará en los controladores
     *                      de rutas y sucursales.
     */
    public OrdenProvisionManager(EntityManager entityManager) {
        rutaController = new RutaController(entityManager);
        sucursalController = new SucursalController(entityManager);
    }

    /**
     * Obtiene todos los caminos posibles desde las sucursales candidatas hasta la
     * sucursal destino,
     * teniendo en cuenta el tiempo límite y peso de la orden de provisión.
     * 
     * @param orden La OrdenProvisionDTO que contiene los datos de la orden de
     *              provisión.
     * @return Una lista de listas de RutaDTO representando todos los caminos
     *         posibles.
     */
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

    /**
     * Calcula los caminos posibles desde una sucursal de origen hasta la sucursal
     * destino,
     * teniendo en cuenta el tiempo límite y peso de la orden de provisión.
     * 
     * @param origen                La SucursalDTO de origen.
     * @param destino               La SucursalDTO de destino.
     * @param limiteTiempoEnMinutos El límite de tiempo en minutos.
     * @param pesoOrden             El peso de la orden de provisión.
     * @return Una lista de listas de RutaDTO representando los caminos posibles
     *         desde la sucursal de origen.
     */
    private List<List<RutaDTO>> getCaminos(SucursalDTO origen, SucursalDTO destino, int limiteTiempoEnMinutos,
            double pesoOrden) {
        List<List<RutaDTO>> caminos = new ArrayList<>();
        List<RutaDTO> caminoActual = new ArrayList<>();
        getCaminosDesdeOrigen(origen, destino, caminoActual, caminos, limiteTiempoEnMinutos, 0, pesoOrden);
        return caminos;
    }

    /**
     * Método recursivo para encontrar caminos desde una sucursal de origen hasta la
     * sucursal destino.
     * 
     * @param actual                La SucursalDTO actual en el camino.
     * @param destino               La SucursalDTO destino del camino.
     * @param caminoActual          Lista de RutaDTO representando el camino actual.
     * @param caminos               Lista de listas de RutaDTO representando todos
     *                              los caminos encontrados.
     * @param limiteTiempoEnMinutos El límite de tiempo en minutos.
     * @param tiempoTranscurrido    El tiempo transcurrido en el camino actual en
     *                              minutos.
     * @param pesoOrden             El peso de la orden de provisión.
     */
    private void getCaminosDesdeOrigen(SucursalDTO actual, SucursalDTO destino,
            List<RutaDTO> caminoActual, List<List<RutaDTO>> caminos,
            int limiteTiempoEnMinutos, int tiempoTranscurrido, double pesoOrden) {

        if (actual.equals(destino)) { // verificar si se alcanzó el destino
            caminos.add(new ArrayList<>(caminoActual)); // Se encontró un camino válido, se agrega a la lista de caminos
            return;
        }

        List<RutaDTO> rutasDesdeActual = rutaController.getRutasBySucursalOrigen(actual).stream()
                .filter(r -> r.getEstado()).toList();// Se buscan todas las rutas desde las cuales se puede llegar
                                                     // partiendo de la sucursal actual

        for (RutaDTO ruta : rutasDesdeActual) {
            double pesoMaximoRuta = ruta.getCapacidadEnKilos();
            int tiempoActualizado = tiempoTranscurrido + pasarAMinutos(ruta.getDuracionViaje());
            // Verificar si se excede el límite de tiempo antes de explorar la ruta y si el
            // peso se puede transportar
            if (tiempoActualizado <= limiteTiempoEnMinutos && pesoMaximoRuta >= pesoOrden) {
                caminoActual.add(ruta);
                getCaminosDesdeOrigen(ruta.getSucursalDestino(), destino, caminoActual, caminos,
                        limiteTiempoEnMinutos, tiempoActualizado, pesoOrden);
                caminoActual.remove(ruta);// si el camino no conduce al destino entonces se elimina
            }
        }
    }

    /**
     * Obtiene las sucursales candidatas para ser origen de los caminos, excluyendo
     * la sucursal destino.
     * 
     * @param orden La OrdenProvisionDTO que contiene los datos de la orden de
     *              provisión.
     * @return Una lista de SucursalDTO representando las sucursales candidatas.
     */
    private List<SucursalDTO> getOrigenesPosibles(OrdenProvisionDTO orden) {
        List<SucursalDTO> sucursales = sucursalController.getSucursalesByEstado(true);
        sucursales.remove(orden.getSucursalDestino());
        return sucursales.stream()
                .filter(s -> satisfaceLaOrden(s.getListaProductoCantidadEnStock(), orden.getItemsProductoCantidad()))
                .collect(Collectors.toList());
    }

    /**
     * Verifica si un stock de productos satisface una orden de provisión.
     * 
     * @param stock El stock de productos en la sucursal.
     * @param order Los productos y cantidades solicitados en la orden de provisión.
     * @return true si el stock satisface la orden, false en caso contrario.
     */
    private boolean satisfaceLaOrden(Map<ProductoDTO, Integer> stock, Map<ProductoDTO, Integer> order) {
        Predicate<Map.Entry<ProductoDTO, Integer>> isSatisfied = entry -> {
            ProductoDTO producto = entry.getKey();
            int cantidadSolicitada = entry.getValue();
            return stock.containsKey(producto) && stock.get(producto) >= cantidadSolicitada;
        };

        return order.entrySet().stream().allMatch(isSatisfied);
    }

    /**
     * Convierte una duración en minutos.
     * 
     * @param tiempo La duración de tiempo a convertir.
     * @return El valor en minutos de la duración proporcionada.
     */
    private Integer pasarAMinutos(Duration tiempo) {
        return (int) tiempo.toMinutes();
    }

    /**
     * Calcula el flujo máximo desde una sucursal de origen hasta una sucursal
     * sumidero en un grafo de rutas.
     * 
     * @param origen   La SucursalDTO de origen.
     * @param sumidero La SucursalDTO sumidero.
     * @return El flujo máximo entre las dos sucursales.
     */
    public double calcularFlujoMaximo(SucursalDTO origen, SucursalDTO sumidero) {
        double flujoMaximo = 0;
        List<RutaDTO> rutasOperativas = rutaController.getRutasByEstado(true);
        Map<RutaDTO, Double> flujoPorRuta = new HashMap<>();

        for (RutaDTO ruta : rutasOperativas) {
            flujoPorRuta.put(ruta, 0.0); // Inicialmente, asignar un flujo de 0 a todas las rutas operativas
        }

        List<RutaDTO> caminoDeAumento;
        // Continuar buscando caminos de aumento y aumentando el flujo máximo
        while ((caminoDeAumento = encontrarCaminoDeAumento(rutasOperativas, origen, sumidero, flujoPorRuta)) != null) {
            double flujoDeAumento = obtenerFlujoDeAumento(caminoDeAumento, flujoPorRuta);
            aumentarFlujoEnCamino(caminoDeAumento, flujoPorRuta, flujoDeAumento);
            flujoMaximo += flujoDeAumento;
        }

        return flujoMaximo;
    }

    /**
     * Encuentra un camino de aumento desde una sucursal de origen hasta una
     * sucursal sumidero en un grafo de rutas.
     * 
     * @param rutasOperativas Lista de rutas operativas en el grafo.
     * @param origen          La SucursalDTO de origen.
     * @param sumidero        La SucursalDTO sumidero.
     * @param flujoPorRuta    Mapa que contiene el flujo por cada ruta.
     * @return Una lista de RutaDTO representando el camino de aumento, o null si no
     *         se encontró.
     */
    private List<RutaDTO> encontrarCaminoDeAumento(List<RutaDTO> rutasOperativas, SucursalDTO origen,
            SucursalDTO sumidero, Map<RutaDTO, Double> flujoPorRuta) {
        Set<SucursalDTO> visitados = new HashSet<>();
        List<RutaDTO> camino = new ArrayList<>();
        if (DFS(origen, sumidero, visitados, camino, rutasOperativas, flujoPorRuta)) {
            return camino;
        }
        return null;
    }

    /**
     * Realiza una búsqueda en profundidad (DFS) para encontrar un camino de aumento desde una sucursal de origen 
     * hasta una sucursal sumidero en un grafo de rutas.
     * 
     * @param actual La SucursalDTO actual en la búsqueda.
     * @param sumidero La SucursalDTO sumidero.
     * @param visitados Conjunto de sucursales visitadas en el proceso.
     * @param camino Lista de RutaDTO representando el camino actual en la búsqueda.
     * @param rutasOperativas Lista de rutas operativas en el grafo.
     * @param flujoPorRuta Mapa que contiene el flujo por cada ruta.
     * @return true si se encontró un camino de aumento, false en caso contrario.
     */
    private boolean DFS(SucursalDTO actual, SucursalDTO sumidero, Set<SucursalDTO> visitados, List<RutaDTO> camino,
            List<RutaDTO> rutasOperativas, Map<RutaDTO, Double> flujoPorRuta) {
        visitados.add(actual);

        if (actual.equals(sumidero)) { //encuentra el camino
            return true;
        }

        for (RutaDTO ruta : rutasOperativas) {
            if (ruta.getSucursalOrigen().equals(actual) && !visitados.contains(ruta.getSucursalDestino())
                    && ruta.getCapacidadEnKilos() - flujoPorRuta.get(ruta) > 0) {
                camino.add(ruta);
                if (DFS(ruta.getSucursalDestino(), sumidero, visitados, camino, rutasOperativas, flujoPorRuta)) {
                    return true;
                }
                camino.remove(ruta);//si no encontró el destino elimina la ruta
            }
        }
        return false;
    }

    /**
     * Calcula el flujo de aumento en un camino de rutas.
     * 
     * @param caminoDeAumento Lista de RutaDTO representando el camino de aumento.
     * @param flujoPorRuta Mapa que contiene el flujo por cada ruta.
     * @return El valor del flujo de aumento en el camino de rutas.
     */
    private double obtenerFlujoDeAumento(List<RutaDTO> caminoDeAumento, Map<RutaDTO, Double> flujoPorRuta) {
        double flujoDeAumento = Double.MAX_VALUE; // Inicializar el flujo de aumento con un valor grande
        for (RutaDTO ruta : caminoDeAumento) {
            double capacidadResidual = ruta.getCapacidadEnKilos() - flujoPorRuta.get(ruta);
            flujoDeAumento = Math.min(flujoDeAumento, capacidadResidual);
        }
        return flujoDeAumento;
    }

    /**
     * Incrementa el flujo en las rutas de un camino de aumento.
     * 
     * @param caminoDeAumento Lista de RutaDTO representando el camino de aumento.
     * @param flujoPorRuta Mapa que contiene el flujo por cada ruta.
     * @param flujoDeAumento El valor del flujo de aumento a incrementar.
     */
    private void aumentarFlujoEnCamino(List<RutaDTO> caminoDeAumento, Map<RutaDTO, Double> flujoPorRuta,
            double flujoDeAumento) {
        for (RutaDTO ruta : caminoDeAumento) {
            flujoPorRuta.put(ruta, flujoPorRuta.get(ruta) + flujoDeAumento);
        }
    }

    /**
     * Calcula el PageRank de las sucursales en un grafo de rutas.
     * 
     * @param factorAmortiguacion El factor de amortiguación para el cálculo del PageRank.
     * @param iteraciones El número máximo de iteraciones para el cálculo del PageRank.
     * @param tolerancia La tolerancia para el cálculo del PageRank.
     * @return Un mapa que contiene el PageRank de cada sucursal.
     */
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

    /**
     * Obtiene el número de enlaces salientes de una sucursal en un grafo de rutas.
     * 
     * @param rutas Lista de rutas en el grafo.
     * @param sucursal La SucursalDTO para la cual se desea obtener el número de enlaces salientes.
     * @return El número de enlaces salientes de la sucursal.
     */
    private int getNumEnlacesSalientes(List<RutaDTO> rutas, SucursalDTO sucursal) {
        return (int) rutas.stream()
                .filter(ruta -> ruta.getSucursalOrigen().equals(sucursal))
                .count();
    }

    /**
     * Obtiene las sucursales involucradas en un camino de rutas.
     * 
     * @param rutas Lista de RutaDTO que representan el camino.
     * @return Una lista de SucursalDTO involucradas en el camino.
     */
    public List<SucursalDTO> getSucursalesDeCamino(List<RutaDTO> rutas) {
        return rutas.stream()
                .flatMap(ruta -> Stream.of(ruta.getSucursalOrigen(), ruta.getSucursalDestino()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el tiempo total de un camino de rutas.
     * 
     * @param rutas Lista de RutaDTO que representan el camino.
     * @return Una cadena con el tiempo total en formato HH:mm.
     */
    public String getTiempoTotal(List<RutaDTO> rutas) {
        return formatDuration(rutas.stream()
                .map(ruta -> ruta.getDuracionViaje())
                .reduce(Duration.ZERO, Duration::plus));
    }

    /**
     * Formatea una duración a una cadena con el formato HH:mm.
     * 
     * @param duration La duración a formatear.
     * @return Una cadena con la duración en formato HH:mm.
     */
    private String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        return String.format("%02d:%02d", hours, minutes);
    }
}