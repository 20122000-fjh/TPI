/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Transportista;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface ITransportistaDAO {
    public EntityManager getEntityManager();
    public void create(Transportista transportista);
    public void edit(Transportista transportista) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public List<Transportista> findTransportistaEntities();
    public List<Transportista> findTransportistaEntities(int maxResults, int firstResult);
    public Transportista findTransportista(long id);
    public int getTransportistaCount();
    public Long obtenerDniPrimerTransportistaDisponible();
}
