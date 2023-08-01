package com.tpdied.dto;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class SucursalDTO {

    private Integer id;

    private LocalTime horaApertura;

    private LocalTime horaCierre;

    private String nombre;

    private Boolean estado; // operativo true, no operativo false

    private Map<ProductoDTO, Integer> listaProductoCantidadEnStock = new HashMap<ProductoDTO, Integer>();

    public Map<ProductoDTO, Integer> getListaProductoCantidadEnStock() {
        return listaProductoCantidadEnStock;
    }

    public void setListaProductoCantidadEnStock(Map<ProductoDTO, Integer> listaProductoCantidadEnStock) {
        this.listaProductoCantidadEnStock = listaProductoCantidadEnStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    private String estadoToString() {
        return estado ? "Operativo" : "No operativo";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SucursalDTO other = (SucursalDTO) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SucursalDTO [id=" + id + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", nombre="
                + nombre + ", estado=" + estadoToString() + "]";
    }

}
