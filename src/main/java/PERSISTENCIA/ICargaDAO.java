/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Carga;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface ICargaDAO {
    public EntityManager getEntityManager();
    public void create(Carga carga);
    public void edit(Carga carga) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public List<Carga> findCargaEntities();
    public List<Carga> findCargaEntities(int maxResults, int firstResult);
    public Carga findCarga(long id);
    public int getCargaCount();
    public long obtenerUltimoIdRegistrado();
}
