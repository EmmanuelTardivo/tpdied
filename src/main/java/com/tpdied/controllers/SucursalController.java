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
import jakarta.persistence.NoResultException;

public class SucursalController {

    private SucursalDao sucursalDao;

    public SucursalController(EntityManager entityManager) {
        sucursalDao = new SucursalDao(entityManager);
    }

    public List<SucursalDTO> getAllEntities() {
        return SucursalMapper.toDto(sucursalDao.getAll());
    }

    public SucursalDTO getEntityById(int id) {
        Sucursal sucursal = sucursalDao.getById(id);
        return sucursal != null ? SucursalMapper.toDto(sucursal) : null;
    }

    public SucursalDTO getEntityByName(String name) {
         try {
            Sucursal sucursal = sucursalDao.getByName(name);
            return SucursalMapper.toDto(sucursal);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<SucursalDTO> getEntityByHorarioApertura(LocalTime horarioApertura) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioApertura(horarioApertura));
    }

    public List<SucursalDTO> getEntityByHorarioCierre(LocalTime horarioCierre) {
        return SucursalMapper.toDto(sucursalDao.getByHorarioCierre(horarioCierre));
    }

    public List<SucursalDTO> getEntityByOperativa(Boolean operativa) {
        return SucursalMapper.toDto(sucursalDao.getByOperativa(operativa));
    }

    public void addEntity(SucursalDTO dto) {
        String name = dto.getNombre();
        if (sucursalDao.getByName(name) != null)
            throw new IllegalArgumentException("Ya existe una sucursal con el mismo nombre.");
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.save(sucursal);
    }

    public void updateEntity(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.update(sucursal);
    }

    public void deleteEntity(SucursalDTO dto) {
        Sucursal sucursal = (SucursalMapper.toEntity(dto));
        sucursalDao.delete(sucursal);
    }

    public void setStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto, Integer cantidad) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        sucursal.updateProductoCantidadEnStock(producto, cantidad);
        sucursalDao.update(sucursal);
    }

    public Map<Producto,Integer> getStockProductos(SucursalDTO sucursalDto) {
        return SucursalMapper.toEntity(sucursalDto).getListaProductoCantidadEnStock();
    }

    public Integer getStockProducto(SucursalDTO sucursalDto, ProductoDTO productoDto) {
        Sucursal sucursal = SucursalMapper.toEntity(sucursalDto);
        Producto producto = ProductoMapper.toEntity(productoDto);
        Optional<Integer> stock = sucursal.getCantidadProductoEnStock(producto);
        return stock.isPresent() ? stock.get() : 0;
    }

    public void setSucursalOperativa(SucursalDTO suscursalDto){
        Sucursal sucursal = SucursalMapper.toEntity(suscursalDto);
        sucursal.setEstado(true);
        sucursalDao.update(sucursal);
    }

    public void setSucursalNoOperativa(SucursalDTO suscursalDto){
        Sucursal sucursal = SucursalMapper.toEntity(suscursalDto);
        sucursal.setEstado(false);
        sucursalDao.update(sucursal);
    }
}
