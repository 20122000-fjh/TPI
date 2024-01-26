/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.UbicacionDeAlmacenamiento;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IUbicacionDeAlmacenamientoDAO {
    public EntityManager getEntityManager();
    public void create(UbicacionDeAlmacenamiento ubicacionDeAlmacenamiento);
    public void edit(UbicacionDeAlmacenamiento ubicacionDeAlmacenamiento) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<UbicacionDeAlmacenamiento> findUbicacionDeAlmacenamientoEntities();
    public List<UbicacionDeAlmacenamiento> findUbicacionDeAlmacenamientoEntities(int maxResults, int firstResult);
    public UbicacionDeAlmacenamiento findUbicacionDeAlmacenamiento(int id);
    public int getUbicacionDeAlmacenamientoCount();
}
