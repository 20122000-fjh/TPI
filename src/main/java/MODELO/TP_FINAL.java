/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MODELO;

import PERSISTENCIA.ControladorPersistencia;
import CONTROLADORA.Controladora;
import VISTA.*;

/**
 *
 * @author Lenovo
 */
public class TP_FINAL {

    public static void main(String[] args) {
        VistaMenuPrincipal vistaMenuPrincipal = new VistaMenuPrincipal();
        VistaRegistroCliente vistaRegistroCliente = new VistaRegistroCliente();
        VistaBajaCliente vistaBajaCliente = new VistaBajaCliente();
        VistaRegistroProveedor vistaRegistroProveedor = new VistaRegistroProveedor();
        VistaRegistroTransportista vistaRegistroTransportista = new VistaRegistroTransportista();
        VistaBajaProveedor vistaBajaProveedor = new VistaBajaProveedor();
        VistaBajaTransportista vistaBajaTransportista = new VistaBajaTransportista();
        VistaRegistroProducto vistaRegistroProducto = new VistaRegistroProducto();
        VistaHacerPedido vistaHacerPedido = new VistaHacerPedido();
        VistaVerHistorial vistaVerHistorial = new VistaVerHistorial();
        VistaModificacionCliente modificarCliente = new VistaModificacionCliente();
        VistaEntregarPedido entregarPedido = new VistaEntregarPedido();
        VistaCancelarPedido cancelarPedido = new VistaCancelarPedido();
        VistaModificarPrecio modificarPrecioProducto = new VistaModificarPrecio();
        VistaModificarStock modificarStockProducto = new VistaModificarStock();
        VistaRegistroAlmacen registroAlmacen = new VistaRegistroAlmacen();
        VistaBajaAlmacen bajaAlmacen = new VistaBajaAlmacen();
        VistaRegistroContrato registroContrato = new VistaRegistroContrato();
        Controladora controladora = new Controladora(vistaMenuPrincipal,vistaRegistroCliente,vistaBajaCliente,vistaRegistroProveedor,vistaRegistroTransportista,vistaBajaProveedor,vistaBajaTransportista,vistaRegistroProducto,vistaHacerPedido,vistaVerHistorial,modificarCliente,entregarPedido,cancelarPedido,modificarPrecioProducto,modificarStockProducto,registroAlmacen,bajaAlmacen,registroContrato);
        controladora.mostrarMenu();
        ControladorPersistencia controlPersis = new ControladorPersistencia();
    }
}