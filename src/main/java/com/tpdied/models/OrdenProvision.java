package com.tpdied.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden_de_provision")
public class OrdenProvision implements Eliminable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer id;

    @Column(name = "fecha_de_orden")
    private LocalDateTime fechaOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino", foreignKey = @ForeignKey(name = "ORDEN_SUCURSAL_FK"))
    private Sucursal sucursalDestino;

    @Column(name = "limite_tiempo")
    private Duration limiteTiempo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_orden", columnDefinition = "VARCHAR(15) CHECK (estado_orden IN ('PENDIENTE', 'EN_PROCESO', 'COMPLETADO'))")
    private EstadoOrden estado;

    @ElementCollection
    @CollectionTable(name = "item_orden", joinColumns = @JoinColumn(name = "id_orden"), foreignKey = @ForeignKey(name = "ITEM_ORDEN_FK"))
    @MapKeyJoinColumn(name = "id_producto", foreignKey = @ForeignKey(name = "ITEM_PRODUCTO_FK"))
    @Column(name = "cantidad")
    private Map<Producto, Integer> itemsProductoCantidad = new HashMap<>();

    @Column(name = "eliminado", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean eliminado = false;

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

    public Duration getLimiteTiempo() {
        return limiteTiempo;
    }

    public void setLimiteTiempo(Duration limiteTiempo) {
        this.limiteTiempo = limiteTiempo;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "OrdenProvision [id=" + id + ", fechaOrden=" + fechaOrden + ", sucursalDestino=" + sucursalDestino
                + ", limiteTiempo=" + formatDuration(limiteTiempo) + ", estado=" + estado + ", itemsProductoCantidad="
                + itemsProductoCantidad + "]";
    }

    private static String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fechaOrden == null) ? 0 : fechaOrden.hashCode());
        result = prime * result + ((sucursalDestino == null) ? 0 : sucursalDestino.hashCode());
        result = prime * result + ((limiteTiempo == null) ? 0 : limiteTiempo.hashCode());
        result = prime * result + ((itemsProductoCantidad == null) ? 0 : itemsProductoCantidad.hashCode());
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
        if (limiteTiempo == null) {
            if (other.limiteTiempo != null)
                return false;
        } else if (!limiteTiempo.equals(other.limiteTiempo))
            return false;
        if (itemsProductoCantidad == null) {
            if (other.itemsProductoCantidad != null)
                return false;
        } else if (!itemsProductoCantidad.equals(other.itemsProductoCantidad))
            return false;
        return true;
    }

    public Map<Producto, Integer> getItemsProductoCantidad() {
        return itemsProductoCantidad;
    }

    public void updateProductoCantidad(Producto p, Integer cant) {
        itemsProductoCantidad.putIfAbsent(p, cant);
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setItemsProductoCantidad(Map<Producto, Integer> itemsProductoCantidad) {
        this.itemsProductoCantidad = itemsProductoCantidad;
    }

}
