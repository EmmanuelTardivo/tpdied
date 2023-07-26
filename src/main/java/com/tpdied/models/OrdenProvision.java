package com.tpdied.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orden_de_provision")
public class OrdenProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer id;

    @Column(name = "fecha_de_orden")
    private LocalDateTime fechaOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino", foreignKey = @ForeignKey(name = "ORDEN_SUCURSAL_FK"))
    private Sucursal sucursalDestino;

    @Column(name = "limite_horas")
    private Integer limiteHoras;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_orden")
    private EstadoOrden estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(LocalDateTime fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public Integer getLimiteHoras() {
        return limiteHoras;
    }

    public void setLimiteHoras(Integer limiteHoras) {
        this.limiteHoras = limiteHoras;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fechaOrden == null) ? 0 : fechaOrden.hashCode());
        result = prime * result + ((sucursalDestino == null) ? 0 : sucursalDestino.hashCode());
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
        OrdenProvision other = (OrdenProvision) obj;
        if (fechaOrden == null) {
            if (other.fechaOrden != null)
                return false;
        } else if (!fechaOrden.equals(other.fechaOrden))
            return false;
        if (sucursalDestino == null) {
            if (other.sucursalDestino != null)
                return false;
        } else if (!sucursalDestino.equals(other.sucursalDestino))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrdenProvision [id=" + id + ", fechaOrden=" + fechaOrden + ", sucursalDestino=" + sucursalDestino.getNombre()
                + ", limiteHoras=" + limiteHoras + ", estado=" + estado + "]";
    }
}
