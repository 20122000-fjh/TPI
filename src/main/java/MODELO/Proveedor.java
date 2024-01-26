package MODELO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String razon_social;
    private long telefono;
    private String correo_electronico;
    private int rendimiento;
    private long CUIT;
    private String categoria;
    /*private List<Envio> envios ;
    private List<Contrato> contratos;*/

    public Proveedor() {
    }

    public Proveedor(int id,String razon_social, long telefono, String correo_electronico, int rendimiento, long CUIT, String categoria) {
        this.id = id;
        this.razon_social = razon_social;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.rendimiento = rendimiento;
        this.CUIT = CUIT;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public int getRendimiento() {
        return rendimiento;
    }

    
    public void setRendimiento(int rendimiento) {
        this.rendimiento = rendimiento;
    }

    public long getCUIT() {
        return CUIT;
    }

    public void setCUIT(long CUIT) {
        this.CUIT = CUIT;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /*public List<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(List<Envio> envios) {
        this.envios = envios;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }*/
    
}