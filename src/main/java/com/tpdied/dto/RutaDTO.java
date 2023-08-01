package com.tpdied.dto;

import java.time.LocalTime;

public class RutaDTO {

    private Integer id;
    private SucursalDTO sucursalOrigen;
    private SucursalDTO sucursalDestino;
    private Double capacidadEnKilos;
    private LocalTime duracionViaje;
    private Boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SucursalDTO getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(SucursalDTO sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    public SucursalDTO getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(SucursalDTO sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public Double getCapacidadEnKilos() {
        return capacidadEnKilos;
    }

    public void setCapacidadEnKilos(Double capacidadEnKilos) {
        this.capacidadEnKilos = capacidadEnKilos;
    }

    public LocalTime getDuracionViaje() {
        return duracionViaje;
    }

    public void setDuracionViaje(LocalTime duracionViaje) {
        this.duracionViaje = duracionViaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String estadoToString() {
        return estado ? "Operativo" : "No operativo";
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sucursalOrigen == null) ? 0 : sucursalOrigen.hashCode());
        result = prime * result + ((sucursalDestino == null) ? 0 : sucursalDestino.hashCode());
        result = prime * result + ((capacidadEnKilos == null) ? 0 : capacidadEnKilos.hashCode());
        result = prime * result + ((duracionViaje == null) ? 0 : duracionViaje.hashCode());
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
        RutaDTO other = (RutaDTO) obj;
        if (sucursalOrigen == null) {
            if (other.sucursalOrigen != null)
                return false;
        } else if (!sucursalOrigen.equals(other.sucursalOrigen))
            return false;
        if (sucursalDestino == null) {
            if (other.sucursalDestino != null)
                return false;
        } else if (!sucursalDestino.equals(other.sucursalDestino))
            return false;
        if (capacidadEnKilos == null) {
            if (other.capacidadEnKilos != null)
                return false;
        } else if (!capacidadEnKilos.equals(other.capacidadEnKilos))
            return false;
        if (duracionViaje == null) {
            if (other.duracionViaje != null)
                return false;
        } else if (!duracionViaje.equals(other.duracionViaje))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ruta [id=" + id + ", sucursalOrigen=" + sucursalOrigen.getNombre() + ", sucursalDestino="
                + sucursalDestino.getNombre()
                + ", capacidadEnKilos=" + capacidadEnKilos + ", duracionViaje=" + duracionViaje + ", estado="
                + estadoToString()
                + "]";
    }

}