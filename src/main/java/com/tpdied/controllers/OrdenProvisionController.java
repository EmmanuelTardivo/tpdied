package com.tpdied.controllers;

import java.util.List;

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

    public List<OrdenProvisionDTO> getAllOrdenesProvision() {
        return OrdenProvisionMapper.toDto(ordenProvisionDao.getAll());
    }

    public List<OrdenProvisionDTO> getOrdenesProvisionPendientes() {
        return OrdenProvisionMapper.toDto(ordenProvisionDao.getOrdenesPendientes());
    }

    public OrdenProvisionDTO getOrdenProvisionById(int id) {
        OrdenProvision orden = ordenProvisionDao.getById(id);
        return orden != null ? OrdenProvisionMapper.toDto(orden) : null;
    }

    public void addOrdenProvision(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        if (ordenProvisionDao.getAll().contains(orden))
            throw new IllegalArgumentException("Ya existe la orden.");
        ordenProvisionDao.save(orden);
    }

    public void updateOrdenProvision(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        ordenProvisionDao.update(orden);
    }

    public void deleteOrdenProvision(OrdenProvisionDTO dto) {
        OrdenProvision orden = (OrdenProvisionMapper.toEntity(dto));
        ordenProvisionDao.delete(orden);
    }

}