package com.tpdied.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tpdied.dao.OrdenProvisionDao;
import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.mappers.OrdenProvisionMapper;
import com.tpdied.models.OrdenProvision;

public class OrdenProvisionController {
    private OrdenProvisionDao ordenProvisionDao;

    public OrdenProvisionController() {
        ordenProvisionDao = new OrdenProvisionDao();
    }

    public List<OrdenProvisionDTO> getAllEntities() {
        List<OrdenProvision> ordenes = ordenProvisionDao.getAll();
        return ordenes.stream()
                .map(OrdenProvisionMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrdenProvisionDTO getEntityById(int id) {
        Optional<OrdenProvision> orden = ordenProvisionDao.getById(id);
        return orden.isPresent() ? OrdenProvisionMapper.toDto(orden.get()) : null;
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