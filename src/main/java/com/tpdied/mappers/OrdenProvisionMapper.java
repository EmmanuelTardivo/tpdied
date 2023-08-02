package com.tpdied.mappers;

import java.util.List;
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
        orden.setLimiteTiempo(ordenDTO.getLimiteTiempo());
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
        ordenDTO.setLimiteTiempo(orden.getLimiteTiempo());
        ordenDTO.setEstado(orden.getEstado());

        return ordenDTO;
    }

    public static List<OrdenProvision> toEntity(List<OrdenProvisionDTO> ordenesDto) {
        return ordenesDto.stream()
                .map(OrdenProvisionMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<OrdenProvisionDTO> toDto(List<OrdenProvision> ordenes) {
        return ordenes.stream()
                .map(OrdenProvisionMapper::toDto)
                .collect(Collectors.toList());
    }
}
