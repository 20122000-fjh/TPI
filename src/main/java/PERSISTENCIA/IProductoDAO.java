/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PERSISTENCIA;

import MODELO.Producto;
import PERSISTENCIA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author francisco
 */
public interface IProductoDAO {
    public EntityManager getEntityManager();
    public void create(Producto producto);
    public void edit(Producto producto) throws NonexistentEntityException, Exception;
    public void destroy(int id) throws NonexistentEntityException;
    public List<Producto> findProductoEntities();
    public List<Producto> findProductoEntities(int maxResults, int firstResult);
    public Producto findProducto(int id);
    public int getProductoCount();
    public int obtenerUltimoIdRegistrado();
    public List<String> obtenerNombresProductos();
    public int encontrarIdPorNombre(String nombreProveedor);
    public String obtenerNombreProductoPorID(int idProducto);
    public float obtenerPrecioProductoPorID(int idProducto);
    public void actualizarPrecioProducto(String nombreProducto, float nuevoPrecio);
    public void actualizarStockProducto(String nombreProducto, int nuevoStock);
}
