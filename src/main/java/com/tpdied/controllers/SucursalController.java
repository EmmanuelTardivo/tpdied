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

public class SucursalController {

    private SucursalDao sucursalDao;

    public SucursalController(EntityManager entityManager) {
        sucursalDao = new SucursalDao(entityManager);
    }

    public List<SucursalDTO> getAllSucursales() {
        return SucursalMapper.toDto(sucursalDao.getAll());
    }

    public SucursalDTO getSucursalById(int id) {
        Sucursal sucursal = sucursalDao.getById(id);
        return sucursal != null ? SucursalMapper.toDto(sucursal) : null;
    }

    public SucursalDTO getSucursalByName(String name) {
        Sucursal sucursal = sucursalDao.getByName(name);
        return sucursal != null ? SucursalMapper.toDto(sucursal) : null;
    }

    public List<SucursalDTO> getSucursalesByHorarioApertura(LocalTime horarioApertura) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioApertura(horarioApertura));
    }

    public List<SucursalDTO> getSucursalesByHorarioCierre(LocalTime horarioCierre) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioCierre(horarioCierre));
    }

    public List<SucursalDTO> getSucursalesByEstado(Boolean estado) {
        return SucursalMapper.toDto(sucursalDao.getByEstado(estado));
    }

    public void addSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        if (sucursalDao.getAll().contains(sucursal))
            throw new IllegalArgumentException("Ya existe una sucursal con el mismo nombre.");
        sucursalDao.save(sucursal);
    }

    public void updateSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.update(sucursal);
    }

    public void deleteSucursal(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.delete(sucursal);
    }

    public void setStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto, Integer cantidad) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        sucursal.updateProductoCantidadEnStock(producto, cantidad);
        sucursalDao.update(sucursal);
    }

    public Map<Producto, Integer> getStockProductos(SucursalDTO sucursalDto) {
        return SucursalMapper.toEntity(sucursalDto).getListaProductoCantidadEnStock();
    }

    public Integer getStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        Optional<Integer> stock = sucursal.getCantidadProductoEnStock(producto);
        return stock.isPresent() ? stock.get() : 0;
    }

    public void setSucursalOperativa(SucursalDTO suscursalDto) {
        Sucursal sucursal = SucursalMapper.toEntity(suscursalDto);
        sucursal.setEstado(true);
        sucursalDao.update(sucursal);
    }

    public void setSucursalNoOperativa(SucursalDTO suscursalDto) {
        Sucursal sucursal = SucursalMapper.toEntity(suscursalDto);
        sucursal.setEstado(false);
        sucursalDao.update(sucursal);
    }
}
