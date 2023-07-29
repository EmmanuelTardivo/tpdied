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
    public String toString() {
        return "Ruta [id=" + id + ", sucursalOrigen=" + sucursalOrigen.getNombre() + ", sucursalDestino=" + sucursalDestino.getNombre()
                + ", capacidadEnKilos=" + capacidadEnKilos + ", duracionViaje=" + duracionViaje + ", estado=" + estadoToString()
                + "]";
    }
    
}