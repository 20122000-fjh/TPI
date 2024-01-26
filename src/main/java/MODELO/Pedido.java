
package MODELO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long codigo;
    private String destino;
    private String estado;
    private Date fechaEntrega;
    private Date fechaCreacion;
    private long numero_tarjeta;
    private long transportista_dni;
    private int id_centrodistribucion;
    private long dni_cliente;
    private List<Producto> productos;

    public Pedido() {
    }

    public Pedido(long codigo, String destino, String estado, Date fechaEntrega, Date fechaCreacion, long numero_tarjeta, long transportista, int id_centrodistribucion, long dni_cliente) {
        this.codigo = codigo;
        this.destino = destino;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.numero_tarjeta = numero_tarjeta;
        this.transportista_dni = transportista;
        this.id_centrodistribucion = id_centrodistribucion;
        this.dni_cliente = dni_cliente;
        /*this.productos = productos;
        this.clientes = clientes;*/
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getMetodoDePago() {
        return numero_tarjeta;
    }

    public void setMetodoDePago(long metodoDePago) {
        this.numero_tarjeta = metodoDePago;
    }

    public long getTransportista() {
        return transportista_dni;
    }

    public void setTransportista(long transportista) {
        this.transportista_dni = transportista;
    }

    public int getId_centrodistribucion() {
        return id_centrodistribucion;
    }

    public void setId_centrodistribucion(int id_centrodistribucion) {
        this.id_centrodistribucion = id_centrodistribucion;
    }

    
    /*public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }*/

    public long getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(long dni_cliente) {
        this.dni_cliente = dni_cliente;
    }
    
}