/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Contrato;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IContratoDAO {
    public EntityManager getEntityManager();
    public void create(Contrato contrato);
    public void edit(Contrato contrato) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<Contrato> findContratoEntities();
    public List<Contrato> findContratoEntities(int maxResults, int firstResult);
    public Contrato findContrato(int id);
    public int getContratoCount();
    public int obtenerUltimoIdRegistrado();
}
