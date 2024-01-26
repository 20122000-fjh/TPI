/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lenovo
 */
@Entity
public class MetodoDePago implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long codigoPedido;
    private long numeroTarjeta;
    private String tipo;

    public MetodoDePago() {
    }

    public MetodoDePago(long codigoPedido, long numeroTarjeta, String tipo) {
        this.codigoPedido = codigoPedido;
        this.numeroTarjeta = numeroTarjeta;
        this.tipo = tipo;
    }

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    
    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}