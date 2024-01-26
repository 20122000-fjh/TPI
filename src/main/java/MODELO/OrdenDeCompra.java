
package MODELO;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity

public class OrdenDeCompra implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int numero_orden;
    private LocalDate fecha;
    private String condicion_venta;

    public OrdenDeCompra() {
    }

    public OrdenDeCompra(int numero_orden, LocalDate fecha, String condicion_venta) {
        this.numero_orden = numero_orden;
        this.fecha = fecha;
        this.condicion_venta = condicion_venta;
    }

    public int getNumero_orden() {
        return numero_orden;
    }

    public void setNumero_orden(int numero_orden) {
        this.numero_orden = numero_orden;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCondicion_venta() {
        return condicion_venta;
    }

    public void setCondicion_venta(String condicion_venta) {
        this.condicion_venta = condicion_venta;
    }
}