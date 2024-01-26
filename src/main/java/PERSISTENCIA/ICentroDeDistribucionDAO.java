/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.CentroDeDistribucion;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface ICentroDeDistribucionDAO {
    public EntityManager getEntityManager();
    public void create(CentroDeDistribucion centroDeDistribucion);
    public void edit(CentroDeDistribucion centroDeDistribucion) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<CentroDeDistribucion> findCentroDeDistribucionEntities();
    public List<CentroDeDistribucion> findCentroDeDistribucionEntities(int maxResults, int firstResult);
    public CentroDeDistribucion findCentroDeDistribucion(int id);
    public int getCentroDeDistribucionCount();
    public Integer obtenerIdPrimerCentroDeDistribucion();
}
