package com.tpdied.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tpdied.dao.SucursalDao;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.mappers.SucursalMapper;
import com.tpdied.models.Sucursal;

public class SucursalController {

    private SucursalDao sucursalDao;

    public SucursalController() {
        sucursalDao = new SucursalDao();
    }

    public List<SucursalDTO> getAllEntities() {
        List<Sucursal> sucursales = sucursalDao.getAll();
        return sucursales.stream()
                .map(SucursalMapper::toDto)
                .collect(Collectors.toList());
    }

    public SucursalDTO getEntityById(int id) {
        Optional<Sucursal> sucursal = sucursalDao.getById(id);
        return sucursal.isPresent() ? SucursalMapper.toDto(sucursal.get()) : null;
    }

    public void addEntity(SucursalDTO dto) {
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

}
