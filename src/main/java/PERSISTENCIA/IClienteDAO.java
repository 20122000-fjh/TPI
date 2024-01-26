/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Cliente;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import PERSISTENCIA.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IClienteDAO {
    public EntityManager getEntityManager();
    public void create(Cliente cliente) throws PreexistingEntityException, Exception;
    public Cliente findCliente(long id);
    public List<Cliente> findClienteEntities();
    public List<Cliente> findClienteEntities(int maxResults, int firstResult);
    public void edit(Cliente cliente) throws NonexistentEntityException, Exception;
    public void destroy(long id) throws NonexistentEntityException;
    public int getClienteCount();
    public void actualizarClientePorDNI(long dni, Cliente clienteActualizado);
}
