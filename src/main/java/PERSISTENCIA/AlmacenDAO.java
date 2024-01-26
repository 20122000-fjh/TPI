package PERSISTENCIA;

import MODELO.Almacen;
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

public class AlmacenDAO implements IAlmacenDAO {

    public AlmacenDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AlmacenDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Almacen almacen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(almacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Almacen almacen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            almacen = em.merge(almacen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = almacen.getId();
                if (findAlmacen(id) == null) {
                    throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.");
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
            Almacen almacen;
            try {
                almacen = em.getReference(Almacen.class, id);
                almacen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.", enfe);
            }
            em.remove(almacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Almacen> findAlmacenEntities() {
        return findAlmacenEntities(true, -1, -1);
    }

    @Override
    public List<Almacen> findAlmacenEntities(int maxResults, int firstResult) {
        return findAlmacenEntities(false, maxResults, firstResult);
    }

    private List<Almacen> findAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Almacen.class));
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
    public Almacen findAlmacen(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Almacen.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Almacen> rt = cq.from(Almacen.class);
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
            Query query = em.createQuery("SELECT MAX(a.id) FROM Almacen a");
            Long ultimoId = (Long) query.getSingleResult();
            return ultimoId != null ? ultimoId : 0; // Manejar el caso cuando no hay registros
        } finally {
            em.close();
        }
    }

    @Override
    public String obtenerUbicacionPorID(long idAlmacen) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT UBICACION FROM ALMACEN WHERE ID = ?1")
                    .setParameter(1, idAlmacen);

            // Ejecutar la consulta nativa para obtener el NOMBRE para un ID de producto dado
            String nombreProducto = (String) query.getSingleResult();

            em.getTransaction().commit();

            return nombreProducto;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
}