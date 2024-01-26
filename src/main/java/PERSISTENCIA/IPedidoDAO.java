/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Pedido;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IPedidoDAO {
    public EntityManager getEntityManager();
    public void create(Pedido pedido) throws PreexistingEntityException, Exception;
    public Pedido findPedido(long id);
    public List<Pedido> findPedidoEntities();
    public List<Pedido> findPedidoEntities(int maxResults, int firstResult);
    public void edit(Pedido pedido) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public int getPedidoCount();
    public long obtenerUltimoIdRegistrado();
    public List<Long> obtenerPedidosPorDNITransportista(long dniTransportista);  
    public String obtenerEstadoPorCodigoPedido(long codigoPedido);
    public String obtenerDestinoPorCodigoPedido(long codigoPedido);
    public void actualizarEstadoPedido(long codigoPedido, String nuevoEstado);
    public Date obtenerFechaEntregaPorCodigoPedido(long codigoPedido);
}
