
package MODELO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Almacen implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String ubicacion;
    /*private List<UbicacionDeAlmacenamiento> ubicacion_almacenamiento;
    private List<Producto> productos*/;

    public Almacen() {
    }

    public Almacen(long id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /*public List<UbicacionDeAlmacenamiento> getUbicacion_almacenamiento() {
        return ubicacion_almacenamiento;
    }

    public void setUbicacion_almacenamiento(List<UbicacionDeAlmacenamiento> ubicacion_almacenamiento) {
        this.ubicacion_almacenamiento = ubicacion_almacenamiento;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }*/
    
    
}