/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * @author Lenovo
 */
@Entity
public class Contrato implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String descripcion;
    private float valor;
    private Date fecha_finalizacion;
    private Date fecha_inicio;
    private int proveedor_id;

    public Contrato() {
    }

    public Contrato(int id, String descripcion,  float valor, Date fecha_finalizacion, Date fecha_inicio, int proveedor_id) {
        this.id = id;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha_finalizacion = fecha_finalizacion;
        this.fecha_inicio = fecha_inicio;
        this.proveedor_id = proveedor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getProveedor() {
        return proveedor_id;
    }

    public void setProveedor(int proveedor) {
        this.proveedor_id = proveedor;
    }
    
    
}