package com.tpdied.models;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer id;

    @Column(name = "hora_apertura")
    private LocalTime horaApertura;

    @Column(name = "hora_cierre")
    private LocalTime horaCierre;
    
    @Column(name = "nombre_sucursal")
    private String nombre;
    
    @Column(name = "estado_sucursal")
    private Boolean estado; // operativo true, no operativo false

    @ElementCollection
    @CollectionTable(
        name = "stock_sucursal",
        joinColumns = @JoinColumn(name = "id_sucursal"),
        foreignKey = @ForeignKey(name = "STOCK_SUCURSAL_FK")
    )
    @MapKeyJoinColumn(name = "id_producto", foreignKey = @ForeignKey(name = "STOCK_PRODUCTO_FK"))
    @Column(name = "cantidad")
    private Map<Producto, Integer> listaProductoCantidadEnStock = new HashMap<>();

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = LocalTime.parse(horaApertura);
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = LocalTime.parse(horaCierre);
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
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Sucursal other = (Sucursal) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sucursal [id=" + id + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre + ", nombre="
                + nombre + ", estado=" + estadoToString() + "]";
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Map<Producto, Integer> getListaProductoCantidadEnStock() {
        return listaProductoCantidadEnStock;
    }

    public void updateProductoCantidadEnStock(Producto p, Integer cant) {
        listaProductoCantidadEnStock.putIfAbsent(p, cant);
    }

    public Optional<Integer> getCantidadProductoEnStock(Producto p) {
        return Optional.ofNullable(listaProductoCantidadEnStock.get(p));
    }

}
