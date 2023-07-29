package com.tpdied.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.tpdied.models.EstadoOrden;

public class OrdenProvisionDTO {
    
    private Integer id;
    private LocalDateTime fechaOrden;
    private SucursalDTO sucursalDestino;
    private Integer limiteHoras;
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
    public Map<ProductoDTO, Integer> getItemsProductoCantidad() {
        return itemsProductoCantidad;
    }
    public void setItemsProductoCantidad(Map<ProductoDTO, Integer> itemsProductoCantidad) {
        this.itemsProductoCantidad = itemsProductoCantidad;
    }
    
    
}
