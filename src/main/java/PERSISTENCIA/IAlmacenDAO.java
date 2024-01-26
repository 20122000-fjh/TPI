/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Almacen;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IAlmacenDAO {
    public EntityManager getEntityManager();
    public void create(Almacen almacen);
    public void edit(Almacen almacen) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public List<Almacen> findAlmacenEntities();
    public List<Almacen> findAlmacenEntities(int maxResults, int firstResult);
    public Almacen findAlmacen(long id);
    public int getAlmacenCount();
    public long obtenerUltimoIdRegistrado();
    public String obtenerUbicacionPorID(long idAlmacen);
}
