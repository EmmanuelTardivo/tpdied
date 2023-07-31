package com.tpdied.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tpdied.dto.SucursalDTO;
import com.tpdied.models.Sucursal;

public class SucursalMapper {

    public static Sucursal toEntity(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalDTO.getId());
        sucursal.setHoraApertura(sucursalDTO.getHoraApertura());
        sucursal.setHoraCierre(sucursalDTO.getHoraCierre());
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setEstado(sucursalDTO.getEstado());

        return sucursal;
    }

    public static SucursalDTO toDto(Sucursal sucursal) {
        SucursalDTO sucursalDTO = new SucursalDTO();
        sucursalDTO.setId(sucursal.getId());
        sucursalDTO.setHoraApertura(sucursal.getHoraApertura());
        sucursalDTO.setHoraCierre(sucursal.getHoraCierre());
        sucursalDTO.setNombre(sucursal.getNombre());
        sucursalDTO.setEstado(sucursal.getEstado());

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
}
