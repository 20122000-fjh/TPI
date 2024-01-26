/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Transportista;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author fedem
 */
public class TransportistaDAO implements ITransportistaDAO {

    public TransportistaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TransportistaDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Transportista transportista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Transportista transportista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transportista = em.merge(transportista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = transportista.getDni();
                if (findTransportista(id) == null) {
                    throw new NonexistentEntityException("The transportista with id " + id + " no longer exists.");
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
            Transportista transportista;
            try {
                transportista = em.getReference(Transportista.class, id);
                transportista.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transportista with id " + id + " no longer exists.", enfe);
            }
            em.remove(transportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Transportista> findTransportistaEntities() {
        return findTransportistaEntities(true, -1, -1);
    }

    @Override
    public List<Transportista> findTransportistaEntities(int maxResults, int firstResult) {
        return findTransportistaEntities(false, maxResults, firstResult);
    }

    private List<Transportista> findTransportistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transportista.class));
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
    public Transportista findTransportista(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transportista.class, id);
        } finally {
            em.close();
        }
    }

   

    @Override
    public int getTransportistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transportista> rt = cq.from(Transportista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Long obtenerDniPrimerTransportistaDisponible() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Transportista> root = cq.from(Transportista.class);

            cq.select(root.get("dni"))
              .where(cb.equal(root.get("disponibilidad"), 1));

            TypedQuery<Long> query = em.createQuery(cq);
            query.setMaxResults(1); // Obtener solo el primer resultado

            List<Long> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                return resultados.get(0);
            } else {
                return null; // No se encontraron transportistas disponibles
            }
        } finally {
            em.close();
        }
    }
}