package com.tpdied.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;


@Entity
@Table(name = "Item_orden")
public class ItemOrden implements Serializable {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden", foreignKey = @ForeignKey(name = "ITEM_ORDEN_FK"))
    private OrdenProvision orden;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", foreignKey = @ForeignKey(name = "ITEM_PRODUCTO_FK"))
    private Producto producto;
    
    @Column(name = "cantidad")
    private Integer cantidad;

    public OrdenProvision getOrden() {
        return orden;
    }

    public void setOrden(OrdenProvision orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orden == null) ? 0 : orden.hashCode());
        result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
        ItemOrden other = (ItemOrden) obj;
        if (orden == null) {
            if (other.orden != null)
                return false;
        } else if (!orden.equals(other.orden))
            return false;
        if (producto == null) {
            if (other.producto != null)
                return false;
        } else if (!producto.equals(other.producto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemOrden [orden=" + orden.getId() + ", producto=" + producto.getNombre() + ", cantidad=" + cantidad + "]";
    }
    
    
}
