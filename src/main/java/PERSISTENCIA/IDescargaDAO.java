/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Descarga;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IDescargaDAO {
    public EntityManager getEntityManager();
    public void create(Descarga descarga) throws PreexistingEntityException, Exception;
    public void edit(Descarga descarga) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public List<Descarga> findDescargaEntities();
    public List<Descarga> findDescargaEntities(int maxResults, int firstResult);
    public Descarga findDescarga(long id);
    public int getDescargaCount();
    public long obtenerUltimoIdRegistrado();
}
