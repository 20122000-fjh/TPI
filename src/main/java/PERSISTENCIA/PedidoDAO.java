/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Pedido;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author fedem
 */
public class PedidoDAO implements IPedidoDAO {

    public PedidoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PedidoDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Pedido pedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pedido = em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = pedido.getCodigo();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    @Override
    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido findPedido(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    @Override
    public long obtenerUltimoIdRegistrado() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT MAX(p.codigo) FROM Pedido p");
            Long ultimoId = (Long) query.getSingleResult();
            return ultimoId != null ? ultimoId : 0; // Manejar el caso cuando no hay registros
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Long> obtenerPedidosPorDNITransportista(long dniTransportista) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT CODIGO FROM PEDIDO WHERE TRANSPORTISTA_DNI = ?1")
                    .setParameter(1, dniTransportista);

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
    
    @Override
    public String obtenerEstadoPorCodigoPedido(long codigoPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT ESTADO FROM PEDIDO WHERE CODIGO = ?1")
                    .setParameter(1, codigoPedido);

            // Ejecutar la consulta nativa para obtener el ESTADO para un pedido dado
            String estado = (String) query.getSingleResult();

            em.getTransaction().commit();

            return estado;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public String obtenerDestinoPorCodigoPedido(long codigoPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT DESTINO FROM PEDIDO WHERE CODIGO = ?1")
                    .setParameter(1, codigoPedido);

            // Ejecutar la consulta nativa para obtener el ESTADO para un pedido dado
            String estado = (String) query.getSingleResult();

            em.getTransaction().commit();

            return estado;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public void actualizarEstadoPedido(long codigoPedido, String nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Consulta para actualizar el campo ESTADO
            Query query = em.createNativeQuery("UPDATE PEDIDO p SET p.ESTADO = ?1 WHERE p.codigo = ?2");
            query.setParameter(1, nuevoEstado);
            query.setParameter(2, codigoPedido);

            // Ejecutar la actualización
            query.executeUpdate();

            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public Date obtenerFechaEntregaPorCodigoPedido(long codigoPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT FECHAENTREGA FROM PEDIDO WHERE CODIGO = ?1")
                    .setParameter(1, codigoPedido);

            // Ejecutar la consulta nativa para obtener la FECHAENTREGA para un pedido dado
            Date fechaEntrega = (Date) query.getSingleResult();

            em.getTransaction().commit();

            return fechaEntrega;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}