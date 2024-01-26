/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.MetodoDePago;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IMetodoDePagoDAO {
    public EntityManager getEntityManager();
    public void create(MetodoDePago metodoDePago) throws PreexistingEntityException, Exception;
    public void edit(MetodoDePago metodoDePago) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public List<MetodoDePago> findMetodoDePagoEntities();
    public List<MetodoDePago> findMetodoDePagoEntities(int maxResults, int firstResult);
    public MetodoDePago findMetodoDePago(long id);
    public int getMetodoDePagoCount();
}
