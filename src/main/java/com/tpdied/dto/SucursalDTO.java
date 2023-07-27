package com.tpdied.dto;

import java.time.LocalTime;

public class SucursalDTO {
    
    private Integer id;

    private LocalTime horaApertura;

    private LocalTime horaCierre;

    private String nombre;

    private Boolean estado; // operativo true, no operativo false

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
    public String toString() {
        return "SucursalDTO [id=" + id + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", nombre="
                + nombre + ", estado=" + estadoToString() + "]";
    }

    
}
