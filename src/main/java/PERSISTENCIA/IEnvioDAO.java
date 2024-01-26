/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Envio;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IEnvioDAO {
    public EntityManager getEntityManager();
    public void create(Envio envio) throws PreexistingEntityException, Exception;
    public void edit(Envio envio) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<Envio> findEnvioEntities();
    public List<Envio> findEnvioEntities(int maxResults, int firstResult);
    public Envio findEnvio(int id);
    public int getEnvioCount();
}
