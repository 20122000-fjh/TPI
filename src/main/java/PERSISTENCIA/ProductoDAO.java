/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Producto;
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
public class ProductoDAO implements IProductoDAO {

    public ProductoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProductoDAO(){
        emf = Persistence.createEntityManagerFactory("PERSISTENCIA");
    }
    
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto = em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    @Override
    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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
    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    @Override
    public int obtenerUltimoIdRegistrado() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT MAX(p.id) FROM Producto p");
            Integer ultimoId = (Integer) query.getSingleResult();
            return ultimoId != null ? ultimoId : 0; // Manejar el caso cuando no hay registros
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<String> obtenerNombresProductos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<String> cq = em.getCriteriaBuilder().createQuery(String.class);
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(rt.get("nombre")); // Reemplaza "nombre" con el nombre real del campo en tu clase Producto
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public int encontrarIdPorNombre(String nombreProveedor) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p.id FROM Producto p WHERE p.nombre = :nombre");
            query.setParameter("nombre", nombreProveedor);
            List<Integer> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }
    
    @Override
    public String obtenerNombreProductoPorID(int idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT NOMBRE FROM PRODUCTO WHERE ID = ?1")
                    .setParameter(1, idProducto);

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
    
    @Override
    public float obtenerPrecioProductoPorID(int idProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Utiliza el índice del parámetro en lugar del nombre
            Query query = em.createNativeQuery("SELECT PRECIO FROM PRODUCTO WHERE ID = ?1")
                    .setParameter(1, idProducto);

            // Ejecutar la consulta nativa para obtener el PRECIO para un ID de producto dado
            float precioProducto = (float) query.getSingleResult();

            em.getTransaction().commit();

            return precioProducto;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public void actualizarPrecioProducto(String nombreProducto, float nuevoPrecio) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Consulta para actualizar el campo ESTADO
            Query query = em.createNativeQuery("UPDATE PRODUCTO p SET p.PRECIO = ?1 WHERE p.NOMBRE = ?2");
            query.setParameter(1, nuevoPrecio);
            query.setParameter(2, nombreProducto);

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
    public void actualizarStockProducto(String nombreProducto, int nuevoStock) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Consulta para actualizar el campo ESTADO
            Query query = em.createNativeQuery("UPDATE PRODUCTO p SET p.STOCK = ?1 WHERE p.NOMBRE = ?2");
            query.setParameter(1, nuevoStock);
            query.setParameter(2, nombreProducto);

            // Ejecutar la actualización
            query.executeUpdate();

            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}