package com.tpdied.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.tpdied.dao.OrdenProvisionDao;
import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.mappers.OrdenProvisionMapper;
import com.tpdied.models.OrdenProvision;

import jakarta.persistence.EntityManager;

public class OrdenProvisionController {

    private OrdenProvisionDao ordenProvisionDao;

    public OrdenProvisionController(EntityManager entityManager) {
        ordenProvisionDao = new OrdenProvisionDao(entityManager);
    }

    public List<OrdenProvisionDTO> getAllEntities() {
        List<OrdenProvision> ordenes = ordenProvisionDao.getAll();
        return ordenes.stream()
                .map(OrdenProvisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrdenProvisionDTO getEntityById(int id) {
        OrdenProvision orden = ordenProvisionDao.getById(id);
        return orden != null ? OrdenProvisionMapper.toDto(orden) : null;
    }

    public void addEntity(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        ordenProvisionDao.save(orden);
    }

    public void updateEntity(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        ordenProvisionDao.update(orden);
    }

    public void deleteEntity(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        ordenProvisionDao.delete(orden);
    }

}