/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author Lenovo
 */
@Entity
public class UbicacionDeAlmacenamiento implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_zona;
    private List <Producto> productos;

    public UbicacionDeAlmacenamiento() {
    }

    public UbicacionDeAlmacenamiento(int id_zona) {
        this.id_zona = id_zona;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
    
}