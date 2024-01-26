/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERSISTENCIA;

import MODELO.UbicacionDeAlmacenamiento;
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

/**
 *
 * @author fedem
 */
public class UbicacionDeAlmacenamientoDAO implements IUbicacionDeAlmacenamientoDAO {

    public UbicacionDeAlmacenamientoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public UbicacionDeAlmacenamientoDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(UbicacionDeAlmacenamiento ubicacionDeAlmacenamiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ubicacionDeAlmacenamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(UbicacionDeAlmacenamiento ubicacionDeAlmacenamiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ubicacionDeAlmacenamiento = em.merge(ubicacionDeAlmacenamiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ubicacionDeAlmacenamiento.getId_zona();
                if (findUbicacionDeAlmacenamiento(id) == null) {
                    throw new NonexistentEntityException("The ubicacionDeAlmacenamiento with id " + id + " no longer exists.");
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
            UbicacionDeAlmacenamiento ubicacionDeAlmacenamiento;
            try {
                ubicacionDeAlmacenamiento = em.getReference(UbicacionDeAlmacenamiento.class, id);
                ubicacionDeAlmacenamiento.getId_zona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubicacionDeAlmacenamiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(ubicacionDeAlmacenamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<UbicacionDeAlmacenamiento> findUbicacionDeAlmacenamientoEntities() {
        return findUbicacionDeAlmacenamientoEntities(true, -1, -1);
    }

    @Override
    public List<UbicacionDeAlmacenamiento> findUbicacionDeAlmacenamientoEntities(int maxResults, int firstResult) {
        return findUbicacionDeAlmacenamientoEntities(false, maxResults, firstResult);
    }

    private List<UbicacionDeAlmacenamiento> findUbicacionDeAlmacenamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UbicacionDeAlmacenamiento.class));
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
    public UbicacionDeAlmacenamiento findUbicacionDeAlmacenamiento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UbicacionDeAlmacenamiento.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
     public int getUbicacionDeAlmacenamientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UbicacionDeAlmacenamiento> rt = cq.from(UbicacionDeAlmacenamiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
