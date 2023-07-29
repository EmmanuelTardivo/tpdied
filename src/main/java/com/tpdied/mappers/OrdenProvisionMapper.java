package com.tpdied.mappers;

import java.util.Map;
import java.util.stream.Collectors;

import com.tpdied.dto.OrdenProvisionDTO;
import com.tpdied.dto.ProductoDTO;
import com.tpdied.models.OrdenProvision;
import com.tpdied.models.Producto;

public class OrdenProvisionMapper {
    public static OrdenProvision toEntity(OrdenProvisionDTO ordenDTO) {
        OrdenProvision orden = new OrdenProvision();
        orden.setId(ordenDTO.getId());
        orden.setFechaOrden(ordenDTO.getFechaOrden());
        orden.setSucursalDestino(SucursalMapper.toEntity(ordenDTO.getSucursalDestino()));
        Map<Producto, Integer> items = ordenDTO.getItemsProductoCantidad()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                    entry -> ProductoMapper.toEntity(entry.getKey()), Map.Entry::getValue));
        orden.setItemsProductoCantidad(items);
        orden.setLimiteHoras(ordenDTO.getLimiteHoras());
        orden.setEstado(ordenDTO.getEstado());

        return orden;
    }

    public static OrdenProvisionDTO toDto(OrdenProvision orden) {
        OrdenProvisionDTO ordenDTO = new OrdenProvisionDTO();
        ordenDTO.setId(orden.getId());
        ordenDTO.setFechaOrden(orden.getFechaOrden());
        ordenDTO.setSucursalDestino(SucursalMapper.toDto(orden.getSucursalDestino()));
        Map<ProductoDTO, Integer> items = orden.getItemsProductoCantidad()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                    entry -> ProductoMapper.toDto(entry.getKey()), Map.Entry::getValue));
        ordenDTO.setItemsProductoCantidad(items);
        ordenDTO.setLimiteHoras(orden.getLimiteHoras());
        ordenDTO.setEstado(orden.getEstado());

        return ordenDTO;
    }
}
