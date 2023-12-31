package com.tpdied.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.tpdied.models.EstadoOrden;

public class OrdenProvisionDTO {

    private Integer id;
    private LocalDateTime fechaOrden;
    private SucursalDTO sucursalDestino;
    private Duration limiteTiempo;
    private Map<ProductoDTO, Integer> itemsProductoCantidad;
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

    public SucursalDTO getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(SucursalDTO sucursalDestino) {
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

    public Map<ProductoDTO, Integer> getItemsProductoCantidad() {
        return itemsProductoCantidad;
    }

    public void setItemsProductoCantidad(Map<ProductoDTO, Integer> itemsProductoCantidad) {
        this.itemsProductoCantidad = itemsProductoCantidad;
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
        OrdenProvisionDTO other = (OrdenProvisionDTO) obj;
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

    @Override
    public String toString() {
        return "OrdenProvisionDTO [id=" + id + ", fechaOrden="
                + fechaOrden.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ", sucursalDestino="
                + sucursalDestino
                + ", limiteHoras=" + formatDuration(limiteTiempo) + ", itemsProductoCantidad=" + itemsProductoCantidad
                + ", estado="
                + estado + "]";
    }

    private static String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        return String.format("%02d:%02d", hours, minutes);
    }

    public Double getPeso() {
        return itemsProductoCantidad.entrySet().stream().mapToDouble(i -> i.getValue()).sum();
    }
}
