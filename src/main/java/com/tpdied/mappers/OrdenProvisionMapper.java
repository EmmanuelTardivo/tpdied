package com.tpdied.mappers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.models.OrdenProvision;
import com.tpdied.models.Producto;

public class OrdenProvisionMapper {
    public static OrdenProvision toEntity(OrdenProvisionDTO ordenDTO) {
        OrdenProvision orden = new OrdenProvision();
        Map<Producto, Integer> items = new HashMap<Producto, Integer>();
        orden.setId(ordenDTO.getId());
        orden.setFechaOrden(ordenDTO.getFechaOrden());
        orden.setSucursalDestino(SucursalMapper.toEntity(ordenDTO.getSucursalDestino()));
        ordenDTO.getItemsProductoCantidad()
                .forEach(item -> items.put(ProductoMapper.toEntity(item.getKey()), item.getCantidad()));
        orden.setItemsProductoCantidad(items);
        orden.setLimiteHoras(ordenDTO.getLimiteHoras());
        orden.setEstado(ordenDTO.getEstado());

        return orden;
    }

    public static OrdenProvisionDTO toDto(OrdenProvision orden) {
        OrdenProvisionDTO ordenDTO = new OrdenProvisionDTO();
        Map<Producto, Integer> items = new HashMap<Producto, Integer>();
        ordenDTO.setId(orden.getId());
        ordenDTO.setFechaOrden(orden.getFechaOrden());
        ordenDTO.setSucursalDestino(SucursalMapper.toDto(orden.getSucursalDestino()));
        ordenDTO.getItemsProductoCantidad()
                .forEach(item -> items.put(ProductoMapper.toDto(item.getKey()), item.getCantidad()));
        ordenDTO.setItemsProductoCantidad(items);
        ordenDTO.setLimiteHoras(orden.getLimiteHoras());
        ordenDTO.setEstado(orden.getEstado());

        return ordenDTO;
    }
}
