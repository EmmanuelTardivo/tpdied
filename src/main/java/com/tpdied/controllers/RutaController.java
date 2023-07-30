
package com.tpdied.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.tpdied.dao.RutaDao;
import com.tpdied.dto.RutaDTO;
import com.tpdied.mappers.RutaMapper;
import com.tpdied.models.Ruta;

import jakarta.persistence.EntityManager;

public class RutaController {

    private RutaDao rutaDao;

    public RutaController(EntityManager entityManager) {
        rutaDao = new RutaDao(entityManager);
    }

    public List<RutaDTO> getAllEntities() {
        List<Ruta> rutas = rutaDao.getAll();
        return rutas.stream()
                .map(RutaMapper::toDto)
                .collect(Collectors.toList());
    }

    public RutaDTO getEntityById(int id) {
        Ruta ruta = rutaDao.getById(id);
        return ruta != null ? RutaMapper.toDto(ruta) : null;
    }

    public void addEntity(RutaDTO dto) {
        Ruta ruta = (RutaMapper.toEntity(dto));
        rutaDao.save(ruta);
    }

    public void updateEntity(RutaDTO dto) {
        Ruta ruta = (RutaMapper.toEntity(dto));
        rutaDao.update(ruta);
    }

    public void deleteEntity(RutaDTO dto) {
        Ruta ruta = (RutaMapper.toEntity(dto));
        rutaDao.delete(ruta);
    }

}

