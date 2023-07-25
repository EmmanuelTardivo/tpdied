package com.tpdied.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ruta")
public class Ruta {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_origen")
    private Sucursal sucursalOrigen;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Sucursal sucursalDestino;
    
    @Column(name = "capacidad_en_kilos")
    private Double capacidadEnKilos;
    
    @Column(name = "duracion_viaje")
    private LocalTime duracionViaje;
    
    @Column(name = "estado_ruta")
    private Boolean estado; // operativo true, no operativo false

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
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
        Ruta other = (Ruta) obj;
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
        return "Ruta [id=" + id + ", sucursalOrigen=" + sucursalOrigen.getNombre() + ", sucursalDestino=" + sucursalDestino.getNombre()
                + ", capacidadEnKilos=" + capacidadEnKilos + ", duracionViaje=" + duracionViaje + ", estado=" + estadoToString()
                + "]";
    }

    
}
