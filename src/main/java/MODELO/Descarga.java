/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lenovo
 */
@Entity
public class Descarga implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private LocalDate fecha_llegada;
    private String observacion;
    private long almacen_id;

    public Descarga() {
    }

    public Descarga(long id, LocalDate fecha_llegada, String observacion,long almacen_id) {
        this.id = id;
        this.fecha_llegada = fecha_llegada;
        this.observacion = observacion;
        this.almacen_id =almacen_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(LocalDate fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public long getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(long almacen_id) {
        this.almacen_id = almacen_id;
    }
    
    
}