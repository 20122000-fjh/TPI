/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Proveedor;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IProveedorDAO {
    public EntityManager getEntityManager();
    public void create(Proveedor proveedor);
    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<Proveedor> findProveedorEntities();
    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult);
    public Proveedor findProveedor(int id);
    public int getProveedorCount();
    public int obtenerUltimoIdRegistrado();
    public int encontrarIdPorNombre(String nombreProveedor);
    public void destroyByCUIT(long cuit) throws NonexistentEntityException;
}
