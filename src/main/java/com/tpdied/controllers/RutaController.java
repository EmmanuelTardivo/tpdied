
package com.tpdied.controllers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import com.tpdied.dao.RutaDao;
import com.tpdied.dto.RutaDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.mappers.RutaMapper;
import com.tpdied.mappers.SucursalMapper;
import com.tpdied.models.Ruta;
import com.tpdied.models.Sucursal;

import jakarta.persistence.EntityManager;

public class RutaController {

    private RutaDao rutaDao;

    public RutaController(EntityManager entityManager) {
        rutaDao = new RutaDao(entityManager);
    }

    public List<RutaDTO> getAllRutas() {
        return RutaMapper.toDto(rutaDao.getAll());
    }

    public RutaDTO getRutaById(int id) {
        Ruta ruta = rutaDao.getById(id);
        return ruta != null ? RutaMapper.toDto(ruta) : null;
    }

    public List<RutaDTO> getRutasBySucursalOrigen(SucursalDTO sucursalDto) {
        Sucursal origen = SucursalMapper.toEntity(sucursalDto);
        return RutaMapper.toDto(rutaDao.getBySucursalOrigen(origen));
    }

    public List<RutaDTO> getRutasBySucursalDestino(SucursalDTO sucursalDto) {
        Sucursal destino = SucursalMapper.toEntity(sucursalDto);
        return RutaMapper.toDto(rutaDao.getBySucursalDestino(destino));
    }

    public List<RutaDTO> getRutasByDuracionViaje(Duration duracionViaje) {
        return RutaMapper.toDto(rutaDao.getByDuracionViaje(duracionViaje));
    }

    public List<RutaDTO> getRutasByCapacidadEnKilos(Double capacidadEnKilos) {
        return RutaMapper.toDto(rutaDao.getByCapacidadEnKilos(capacidadEnKilos));
    }

    public List<RutaDTO> getRutasByEstado(Boolean estado) {
        return RutaMapper.toDto(rutaDao.getByEstado(estado));
    }

    public void setRutaOperativa(RutaDTO rutaDto) {
        Ruta ruta = RutaMapper.toEntity(rutaDto);
        ruta.setEstado(true);
        rutaDao.update(ruta);
    }

    public void setRutaNoOperativa(RutaDTO rutaDto) {
        Ruta ruta = RutaMapper.toEntity(rutaDto);
        ruta.setEstado(false);
        rutaDao.update(ruta);
    }

    public void addRuta(RutaDTO dto) {
        Ruta ruta = RutaMapper.toEntity(dto);
        if (rutaDao.getAll().contains(ruta))
            throw new IllegalArgumentException("Ya existe esa ruta.");
        rutaDao.save(ruta);
    }

    public void updateRuta(RutaDTO dto) {
        Ruta ruta = (RutaMapper.toEntity(dto));
        rutaDao.update(ruta);
    }

    public void deleteRuta(RutaDTO dto) {
        Ruta ruta = (RutaMapper.toEntity(dto));
        rutaDao.delete(ruta);
    }
    
    public void setRutasNoOperativa(SucursalDTO sucursal) {
        getRutasBySucursalDestino(sucursal).forEach(r -> setRutaNoOperativa(r));
        getRutasBySucursalOrigen(sucursal).forEach(r -> setRutaNoOperativa(r));
    }
}
