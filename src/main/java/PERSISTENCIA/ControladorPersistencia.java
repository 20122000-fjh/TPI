
package PERSISTENCIA;

import MODELO.Cliente;
import MODELO.Proveedor;
import MODELO.Transportista;
import MODELO.*;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ControladorPersistencia {
    AlmacenDAO almacenDAO = new AlmacenDAO();
    CentroDeDistribucionDAO centroDeDistribucionDAO = new CentroDeDistribucionDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    ContratoDAO contratoDAO = new ContratoDAO();
    DescargaDAO descargaDAO = new DescargaDAO();
    EnvioDAO envioDAO = new EnvioDAO();
    MetodoDePagoDAO metodoDePagoDAO = new MetodoDePagoDAO();
    OrdenDeCompraDAO ordenDeCompraDAO = new OrdenDeCompraDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    TransportistaDAO transportistaDAO = new TransportistaDAO();
    UbicacionDeAlmacenamientoDAO ubicacionDeAlmacenamientoDAO = new UbicacionDeAlmacenamientoDAO(); 
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    
    public void crearCliente(Cliente cliente){
        try {
            clienteDAO.create(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarCliente(int id){
        try {
            clienteDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearProveedor(Proveedor proveedor){
        try {
            proveedorDAO.create(proveedor);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarProveedor(long cuit){
        try {
            proveedorDAO.destroyByCUIT(cuit);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int obtenerUltimoIDProveedor(){
        int id = proveedorDAO.obtenerUltimoIdRegistrado();
        return id;
    }
    public void crearTransportista(Transportista transportista){
        try {
            transportistaDAO.create(transportista);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarTransportista(int id){
        try {
            transportistaDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int obtenerUltimoIDProducto(){
        int id = productoDAO.obtenerUltimoIdRegistrado();
        return id;
    }
    public void crearProducto(Producto producto){
        try {
            productoDAO.create(producto);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Proveedor> encontrarProveedores(){
        return proveedorDAO.findProveedorEntities();
    }
    public int encontrarProveedorPorNombre(String nombreProveedor){
        int id = proveedorDAO.encontrarIdPorNombre(nombreProveedor);
        return id;
    }
    public List<String> obtenerNombresProductos() {
        return productoDAO.obtenerNombresProductos();
    }
    public long obtenerUltimoIDPedido(){
        long id = pedidoDAO.obtenerUltimoIdRegistrado();
        return id;
    }
    public int encontrarProductoPorNombre(String nombreProducto){
        int id = productoDAO.encontrarIdPorNombre(nombreProducto);
        return id;
    }
    public Long obtenerDniPrimerTransportistaDisponible() {
        long dni = transportistaDAO.obtenerDniPrimerTransportistaDisponible();
        return dni;
    }
    public Integer obtenerIdPrimerCentroDeDistribucion() {
        int id = centroDeDistribucionDAO.obtenerIdPrimerCentroDeDistribucion();
        return id;
    }
    public void crearMetodoPago(MetodoDePago metodo){
        try {
            metodoDePagoDAO.create(metodo);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearPedido(Pedido pedido){
        try {
            pedidoDAO.create(pedido);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertarRelacionClientePedido(long dniCliente, long codigoPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // Insertar en la tabla intermedia directamente usando una consulta nativa
            em.createNativeQuery("INSERT INTO CLIENTE_PEDIDO (Cliente_DNI, pedidos_CODIGO) VALUES (?, ?)")
                    .setParameter(1, dniCliente)
                    .setParameter(2, codigoPedido)
                    .executeUpdate();
            
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public void insertarRelacionProductoPedido(long codigoPedido,int id_producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // Insertar en la tabla intermedia directamente usando una consulta nativa
            em.createNativeQuery("INSERT INTO PEDIDO_PRODUCTO (Pedido_CODIGO, productos_ID) VALUES (?, ?)")
                    .setParameter(1, codigoPedido)
                    .setParameter(2,id_producto )
                    .executeUpdate();
            
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public List<Long> obtenerPedidosPorDNI(long dniCliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT pedidos_CODIGO FROM CLIENTE_PEDIDO WHERE Cliente_DNI = ?1")
                    .setParameter(1, dniCliente);

            // Ejecutar la consulta nativa para obtener los pedidos_CODIGO para un Cliente_DNI dado
            List<Long> pedidos = query.getResultList();

            em.getTransaction().commit();

            return pedidos;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public String obtenerEstadoPorCodigoPedido(long codigoPedido) {
        return pedidoDAO.obtenerEstadoPorCodigoPedido(codigoPedido);
    }
    public Date obtenerFechaEntregaPorCodigoPedido(long codigoPedido) {
        return pedidoDAO.obtenerFechaEntregaPorCodigoPedido(codigoPedido);
    }
    public List<Integer> obtenerProductosIDPorCodigoPedido(long codigoPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT productos_ID FROM PEDIDO_PRODUCTO WHERE Pedido_CODIGO = ?1")
                    .setParameter(1, codigoPedido);

            // Ejecutar la consulta nativa para obtener los productos_ID para un pedido dado
            List<Integer> productosIDs = query.getResultList();

            em.getTransaction().commit();

            return productosIDs;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public String obtenerNombreProductoPorID(int idProducto) {
        return productoDAO.obtenerNombreProductoPorID(idProducto);
    }
    public float obtenerPrecioProductoPorID(int idProducto) {
        return productoDAO.obtenerPrecioProductoPorID(idProducto);
    }
    public void actualizarClientePorDNI(long dni, Cliente clienteActualizado) {
        clienteDAO.actualizarClientePorDNI(dni, clienteActualizado);
    }
    public List<Long> obtenerPedidosPorDNITransportista(long dniTransportista) {
        return pedidoDAO.obtenerPedidosPorDNITransportista(dniTransportista);
    }
    public String obtenerDestinoPorCodigoPedido(long codigoPedido) {
        return pedidoDAO.obtenerDestinoPorCodigoPedido(codigoPedido);
    }
    public void actualizarEstadoPedido(long codigoPedido, String nuevoEstado) {
        pedidoDAO.actualizarEstadoPedido(codigoPedido, nuevoEstado);
    }
    public void actualizarPrecioProducto(String nombreProducto, float nuevoPrecio) {
        productoDAO.actualizarPrecioProducto(nombreProducto, nuevoPrecio);
    }
    public void actualizarStockProducto(String nombreProducto, int nuevoStock) {
        productoDAO.actualizarStockProducto(nombreProducto, nuevoStock);
    }
    public long obtenerUltimoIdRegistradoAlmacen() {
        return almacenDAO.obtenerUltimoIdRegistrado();
    }
    public void crearAlmacen(Almacen almacen){
        try {
            almacenDAO.create(almacen);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Almacen> encontrarAlmacenes() {
        return almacenDAO.findAlmacenEntities();
    }
    public void borrarAlmacen(long id){
        try {
            almacenDAO.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertarRelacionProductoAlmacen(long codigoAlmacen,int idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // Insertar en la tabla intermedia directamente usando una consulta nativa
            em.createNativeQuery("INSERT INTO ALMACEN_PRODUCTO (Almacen_ID, productos_ID) VALUES (?, ?)")
                    .setParameter(1, codigoAlmacen)
                    .setParameter(2,idProducto )
                    .executeUpdate();
            
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public void insertarRelacionProductoUbicacion(int idProducto,int idUbicacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // Insertar en la tabla intermedia directamente usando una consulta nativa
            em.createNativeQuery("INSERT INTO UBICACIONDEALMACENAMIENTO_PRODUCTO (UbicacionDeAlmacenamiento_ID_ZONA, productos_ID) VALUES (?, ?)")
                    .setParameter(1, idUbicacion)
                    .setParameter(2,idProducto )
                    .executeUpdate();
            
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public int obtenerUltimoIdRegistradoContrato() {
        return contratoDAO.obtenerUltimoIdRegistrado();
    }
    public void crearContrato(Contrato contrato){
        try {
            contratoDAO.create(contrato);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> obtenerIDUbicacionesPorIDAlmacen(long almacenID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT ubicacion_almacenamiento_ID_ZONA FROM ALMACEN_UBICACIONDEALMACENAMIENTO WHERE Almacen_ID = ?1")
                    .setParameter(1, almacenID);

            // Ejecutar la consulta nativa para obtener los pedidos_CODIGO para un Cliente_DNI dado
            List<Integer> ubicaciones = query.getResultList();

            em.getTransaction().commit();

            return ubicaciones;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}