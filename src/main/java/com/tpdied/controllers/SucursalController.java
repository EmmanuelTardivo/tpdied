package com.tpdied.controllers;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tpdied.dao.SucursalDao;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.mappers.ProductoMapper;
import com.tpdied.mappers.SucursalMapper;
import com.tpdied.models.Producto;
import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;

/**
 * Esta clase representa un controlador para gestionar operaciones relacionadas
 * con las sucursales de una empresa.
 */
public class SucursalController {

    private SucursalDao sucursalDao;

    /**
     * Crea una nueva instancia de SucursalController con el EntityManager
     * especificado.
     *
     * @param entityManager El EntityManager que se utilizará para acceder a la base
     *                      de datos.
     */
    public SucursalController(EntityManager entityManager) {
        sucursalDao = new SucursalDao(entityManager);
    }

    /**
     * Obtiene una lista de todas las sucursales disponibles en la base de datos.
     *
     * @return Una lista de objetos SucursalDTO que representan las sucursales
     *         disponibles.
     */
    public List<SucursalDTO> getAllSucursales() {
        return SucursalMapper.toDto(sucursalDao.getAll());
    }

    /**
     * Obtiene una sucursal por su ID desde la base de datos.
     *
     * @param id El ID de la sucursal a buscar.
     * @return Un objeto SucursalDTO que representa la sucursal encontrada, o null
     *         si no se encuentra ninguna con ese ID.
     */
    public SucursalDTO getSucursalById(int id) {
        Sucursal sucursal = sucursalDao.getById(id);
        return sucursal != null ? SucursalMapper.toDto(sucursal) : null;
    }

    /**
     * Obtiene una sucursal por su nombre desde la base de datos.
     *
     * @param name El nombre de la sucursal a buscar.
     * @return Un objeto SucursalDTO que representa la sucursal encontrada, o null
     *         si no se encuentra ninguna con ese nombre.
     */
    public SucursalDTO getSucursalByName(String name) {
        Sucursal sucursal = sucursalDao.getByName(name);
        return sucursal != null ? SucursalMapper.toDto(sucursal) : null;
    }

    /**
     * Obtiene una lista de sucursales que abren a una hora de apertura específica.
     *
     * @param horarioApertura La hora de apertura que se usará para buscar
     *                        sucursales.
     * @return Una lista de objetos SucursalDTO que representan las sucursales
     *         encontradas, o una lista vacía si no hay ninguna que cumpla con el
     *         criterio.
     */
    public List<SucursalDTO> getSucursalesByHorarioApertura(LocalTime horarioApertura) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioApertura(horarioApertura));
    }

    /**
     * Obtiene una lista de sucursales que cierran a una hora de cierre específica.
     *
     * @param horarioCierre La hora de cierre que se usará para buscar sucursales.
     * @return Una lista de objetos SucursalDTO que representan las sucursales
     *         encontradas, o una lista vacía si no hay ninguna que cumpla con el
     *         criterio.
     */
    public List<SucursalDTO> getSucursalesByHorarioCierre(LocalTime horarioCierre) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioCierre(horarioCierre));
    }

    /**
     * Obtiene una lista de sucursales que tienen un estado específico (operativas o
     * no operativas).
     *
     * @param estado El estado (true para operativa, false para no operativa) que se
     *               usará para buscar sucursales.
     * @return Una lista de objetos SucursalDTO que representan las sucursales
     *         encontradas, o una lista vacía si no hay ninguna que cumpla con el
     *         criterio.
     */
    public List<SucursalDTO> getSucursalesByEstado(Boolean estado) {
        return SucursalMapper.toDto(sucursalDao.getByEstado(estado));
    }

    /**
     * Agrega una nueva sucursal a la base de datos.
     *
     * @param dto El objeto SucursalDTO que contiene los datos de la nueva sucursal
     *            a agregar.
     * @throws IllegalArgumentException Si ya existe una sucursal con el mismo
     *                                  nombre en la base de datos.
     */
    public void addSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        if (sucursalDao.getAll().contains(sucursal))
            throw new IllegalArgumentException("Ya existe una sucursal con el mismo nombre.");
        sucursalDao.save(sucursal);
    }

    /**
     * Actualiza la información de una sucursal en la base de datos.
     *
     * @param dto El objeto SucursalDTO que contiene los datos actualizados de la
     *            sucursal.
     */
    public void updateSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.update(sucursal);
    }

    /**
     * Elimina una sucursal de la base de datos.
     *
     * @param dto El objeto SucursalDTO que representa la sucursal a eliminar.
     */
    public void deleteSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.delete(sucursal);
    }

    /**
     * Establece la cantidad de stock de un producto en una sucursal.
     *
     * @param sucursalDto El objeto SucursalDTO que representa la sucursal donde se
     *                    actualizará el stock.
     * @param productoDto El objeto ProductoDTO que representa el producto al que se
     *                    actualizará el stock.
     * @param cantidad    La cantidad de stock a establecer.
     */
    public void setStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto, Integer cantidad) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        sucursal.updateProductoCantidadEnStock(producto, cantidad);
        sucursalDao.update(sucursal);
    }

    /**
     * Obtiene un mapa que muestra el stock de todos los productos disponibles en
     * una sucursal.
     *
     * @param sucursalDto El objeto SucursalDTO que representa la sucursal de la que
     *                    se desea obtener el stock de productos.
     * @return Un mapa que asocia cada producto disponible en la sucursal con su
     *         respectiva cantidad en stock.
     */
    public Map<Producto, Integer> getStockProductos(SucursalDTO sucursalDto) {
        return SucursalMapper.toEntity(sucursalDto).getListaProductoCantidadEnStock();
    }

    /**
     * Obtiene la cantidad de stock de un producto específico en una sucursal.
     *
     * @param sucursalDto El objeto SucursalDTO que representa la sucursal donde se buscará el stock del producto.
     * @param productoDto El objeto ProductoDTO que representa el producto del que se desea obtener el stock.
     * @return La cantidad de stock del producto en la sucursal, o 0 si el producto
     *         no está disponible en dicha sucursal.
     */
    public Integer getStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        Optional<Integer> stock = sucursal.getCantidadProductoEnStock(producto);
        return stock.isPresent() ? stock.get() : 0;
    }

    /**
     * Establece una sucursal como operativa.
     *
     * @param sucursalDto El objeto SucursalDTO que representa la sucursal que se
     *                    establecerá como operativa.
     */
    public void setSucursalOperativa(SucursalDTO sucursalDto) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        sucursal.setEstado(true);
        sucursalDao.update(sucursal);
    }

    /**
     * Establece una sucursal como no operativa.
     *
     * @param sucursalDto El objeto SucursalDTO que representa la sucursal que se establecerá como no operativa.
     */
    public void setSucursalNoOperativa(SucursalDTO sucursalDto) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        sucursal.setEstado(false);
        sucursalDao.update(sucursal);
    }
}
