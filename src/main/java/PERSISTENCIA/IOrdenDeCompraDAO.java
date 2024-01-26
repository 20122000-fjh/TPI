/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.OrdenDeCompra;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IOrdenDeCompraDAO {
    public EntityManager getEntityManager();
    public void create(OrdenDeCompra ordenDeCompra);
    public void edit(OrdenDeCompra ordenDeCompra) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<OrdenDeCompra> findOrdenDeCompraEntities();
    public List<OrdenDeCompra> findOrdenDeCompraEntities(int maxResults, int firstResult);
    public OrdenDeCompra findOrdenDeCompra(int id);
    public int getOrdenDeCompraCount();
}
