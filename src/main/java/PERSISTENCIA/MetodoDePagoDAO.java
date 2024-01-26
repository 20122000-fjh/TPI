package PERSISTENCIA;


import MODELO.MetodoDePago;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MetodoDePagoDAO implements IMetodoDePagoDAO {

    public MetodoDePagoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MetodoDePagoDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(MetodoDePago metodoDePago) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(metodoDePago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMetodoDePago(metodoDePago.getCodigoPedido()) != null) {
                throw new PreexistingEntityException("MetodoDePago " + metodoDePago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(MetodoDePago metodoDePago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            metodoDePago = em.merge(metodoDePago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = metodoDePago.getCodigoPedido();
                if (findMetodoDePago(id) == null) {
                    throw new NonexistentEntityException("The metodoDePago with id " + id + " no longer exists.");
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
            MetodoDePago metodoDePago;
            try {
                metodoDePago = em.getReference(MetodoDePago.class, id);
                metodoDePago.getCodigoPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodoDePago with id " + id + " no longer exists.", enfe);
            }
            em.remove(metodoDePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<MetodoDePago> findMetodoDePagoEntities() {
        return findMetodoDePagoEntities(true, -1, -1);
    }

    @Override
    public List<MetodoDePago> findMetodoDePagoEntities(int maxResults, int firstResult) {
        return findMetodoDePagoEntities(false, maxResults, firstResult);
    }

    private List<MetodoDePago> findMetodoDePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MetodoDePago.class));
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
    public MetodoDePago findMetodoDePago(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MetodoDePago.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getMetodoDePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MetodoDePago> rt = cq.from(MetodoDePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}