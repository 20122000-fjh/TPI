package PERSISTENCIA;

import MODELO.OrdenDeCompra;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OrdenDeCompraDAO implements IOrdenDeCompraDAO {

    public OrdenDeCompraDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public OrdenDeCompraDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(OrdenDeCompra ordenDeCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ordenDeCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(OrdenDeCompra ordenDeCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ordenDeCompra = em.merge(ordenDeCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ordenDeCompra.getNumero_orden();
                if (findOrdenDeCompra(id) == null) {
                    throw new NonexistentEntityException("The ordenDeCompra with id " + id + " no longer exists.");
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
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenDeCompra ordenDeCompra;
            try {
                ordenDeCompra = em.getReference(OrdenDeCompra.class, id);
                ordenDeCompra.getNumero_orden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenDeCompra with id " + id + " no longer exists.", enfe);
            }
            em.remove(ordenDeCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<OrdenDeCompra> findOrdenDeCompraEntities() {
        return findOrdenDeCompraEntities(true, -1, -1);
    }

    @Override
    public List<OrdenDeCompra> findOrdenDeCompraEntities(int maxResults, int firstResult) {
        return findOrdenDeCompraEntities(false, maxResults, firstResult);
    }

    private List<OrdenDeCompra> findOrdenDeCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenDeCompra.class));
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
    public OrdenDeCompra findOrdenDeCompra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenDeCompra.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getOrdenDeCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenDeCompra> rt = cq.from(OrdenDeCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}