package com.tpdied.mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tpdied.dto.ProductoDTO;
import com.tpdied.dto.SucursalDTO;
import com.tpdied.models.Producto;
import com.tpdied.models.Sucursal;

public class SucursalMapper {

    public static Sucursal toEntity(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalDTO.getId());
        sucursal.setHoraApertura(sucursalDTO.getHoraApertura());
        sucursal.setHoraCierre(sucursalDTO.getHoraCierre());
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setEstado(sucursalDTO.getEstado());
        sucursal.setListaProductoCantidadEnStock(stockProductosToEntity(sucursalDTO.getListaProductoCantidadEnStock()));

        return sucursal;
    }

    public static SucursalDTO toDto(Sucursal sucursal) {
        SucursalDTO sucursalDTO = new SucursalDTO();
        sucursalDTO.setId(sucursal.getId());
        sucursalDTO.setHoraApertura(sucursal.getHoraApertura());
        sucursalDTO.setHoraCierre(sucursal.getHoraCierre());
        sucursalDTO.setNombre(sucursal.getNombre());
        sucursalDTO.setEstado(sucursal.getEstado());
        sucursalDTO.setListaProductoCantidadEnStock(stockProductosToDto(sucursal.getListaProductoCantidadEnStock()));

        return sucursalDTO;
    }

    public static List<Sucursal> toEntity(List<SucursalDTO> sucursalesDto) {
        return sucursalesDto.stream()
                .map(SucursalMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<SucursalDTO> toDto(List<Sucursal> sucursales) {
        return sucursales.stream()
                .map(SucursalMapper::toDto)
                .collect(Collectors.toList());
    }

    private static Map<ProductoDTO, Integer> stockProductosToDto(Map<Producto, Integer> listaProductoCantidadEnStock) {
        return listaProductoCantidadEnStock.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductoMapper.toDto(entry.getKey()), // Mapear Producto a ProductoDTO
                        Map.Entry::getValue // Obtener el Integer del Map original como value
                ));
    }

    private static Map<Producto, Integer> stockProductosToEntity(
            Map<ProductoDTO, Integer> listaProductoCantidadEnStockDTO) {
        return listaProductoCantidadEnStockDTO.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductoMapper.toEntity(entry.getKey()), // Mapear ProductoDTO a Producto
                        Map.Entry::getValue // Obtener el Integer del Map original como value
                ));
    }
}
