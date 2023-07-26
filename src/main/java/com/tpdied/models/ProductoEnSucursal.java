package com.tpdied.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto_en_sucursal")
public class ProductoEnSucursal implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", foreignKey = @ForeignKey(name = "PRODUCTO_SUCURSAL_FK"))
    private Producto producto;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", foreignKey = @ForeignKey(name = "SUCURSAL_PRODUCTO_FK"))
    private Sucursal sucursal;

    @Column(name = "cantidad")
    private Integer cantidad;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
        result = prime * result + ((producto == null) ? 0 : producto.hashCode());
        result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
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
        ProductoEnSucursal other = (ProductoEnSucursal) obj;
        if (producto == null) {
            if (other.producto != null)
                return false;
        } else if (!producto.equals(other.producto))
            return false;
        if (sucursal == null) {
            if (other.sucursal != null)
                return false;
        } else if (!sucursal.equals(other.sucursal))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProductoEnSucursal [producto=" + producto.getNombre() + ", sucursal=" + sucursal.getNombre() + ", cantidad=" + cantidad + "]";
    }
    
}
