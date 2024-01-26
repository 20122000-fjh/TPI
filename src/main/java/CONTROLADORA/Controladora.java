/*
 * aaaaaaa
 */
package CONTROLADORA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import VISTA.*;
import MODELO.*;
import PERSISTENCIA.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author fedem
 */
public class Controladora implements ActionListener{
    private VistaMenuPrincipal menuPrincipal;
    private VistaRegistroCliente registroCliente;
    private VistaBajaCliente bajaCliente;
    private VistaRegistroProveedor registroProveedor;
    private VistaBajaProveedor bajaProveedor;
    private VistaRegistroTransportista registroTransportista;
    private VistaBajaTransportista bajaTransportista;
    private VistaRegistroProducto registroProducto;
    private VistaHacerPedido hacerPedido;
    private VistaVerHistorial verHistorial;
    private VistaModificacionCliente modificarCliente;
    private VistaEntregarPedido entregarPedido;
    private VistaCancelarPedido cancelarPedido;
    private VistaModificarPrecio modificarPrecioProducto;
    private VistaModificarStock modificarStockProducto;
    private VistaRegistroAlmacen registroAlmacen;
    private VistaBajaAlmacen bajaAlmacen;
    private VistaRegistroContrato registroContrato;
    ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    private List<Integer> productos_id;
    private List<Long> pedidos_id;
    private List<Almacen> almacenes;
    private List<Integer> ubicaciones;
    

    public Controladora(VistaMenuPrincipal menuPrincipal, VistaRegistroCliente registroCliente, VistaBajaCliente bajaCliente, VistaRegistroProveedor registroProveedor,VistaRegistroTransportista registroTransportista,VistaBajaProveedor bajaProveedor,VistaBajaTransportista bajaTransportista,VistaRegistroProducto registroProducto,VistaHacerPedido hacerPedido,VistaVerHistorial verHistorial,VistaModificacionCliente modificarCliente, VistaEntregarPedido entregarPedido,VistaCancelarPedido cancelarPedido,VistaModificarPrecio modificarPrecioProducto,VistaModificarStock modificarStockProducto, VistaRegistroAlmacen registroAlmacen,VistaBajaAlmacen bajaAlmacen,VistaRegistroContrato registroContrato){
        this.menuPrincipal = menuPrincipal;
        this.registroCliente = registroCliente;
        this.bajaCliente = bajaCliente;
        this.registroProveedor = registroProveedor;
        this.registroTransportista = registroTransportista;
        this.bajaProveedor = bajaProveedor;
        this.bajaTransportista = bajaTransportista;
        this.registroProducto = registroProducto;
        this.hacerPedido = hacerPedido;
        this.verHistorial = verHistorial;
        this.modificarCliente = modificarCliente;
        this.entregarPedido = entregarPedido;
        this.cancelarPedido = cancelarPedido;
        this.modificarPrecioProducto = modificarPrecioProducto;
        this.modificarStockProducto = modificarStockProducto;
        this.registroAlmacen = registroAlmacen;
        this.bajaAlmacen = bajaAlmacen;
        this.registroContrato = registroContrato;
        
        menuPrincipal.btnAlta.setActionCommand("MenuAlta");
        menuPrincipal.btnAlta.addActionListener(this);
        menuPrincipal.btnBaja.setActionCommand("MenuBaja");
        menuPrincipal.btnBaja.addActionListener(this);
        menuPrincipal.btnHacerPedido.setActionCommand("MenuPedido");
        menuPrincipal.btnHacerPedido.addActionListener(this);
        menuPrincipal.btnAltaProveedor.setActionCommand("MenuAltaProveedor");
        menuPrincipal.btnAltaProveedor.addActionListener(this);
        menuPrincipal.btnBajaProveedor.setActionCommand("MenuBajaProveedor");
        menuPrincipal.btnBajaProveedor.addActionListener(this);
        menuPrincipal.btnAltaTransportista.setActionCommand("MenuAltaTransportista");
        menuPrincipal.btnAltaTransportista.addActionListener(this);
        menuPrincipal.btnBajaTransportista.setActionCommand("MenuBajaTransportista");
        menuPrincipal.btnBajaTransportista.addActionListener(this);
        menuPrincipal.btnAltaProducto.setActionCommand("MenuAltaProducto");
        menuPrincipal.btnAltaProducto.addActionListener(this);
        menuPrincipal.btnVerHistorial.setActionCommand("MenuVerHistorial");
        menuPrincipal.btnVerHistorial.addActionListener(this);
        menuPrincipal.btnModificar.setActionCommand("MenuModificacion");
        menuPrincipal.btnModificar.addActionListener(this);
        menuPrincipal.btnEntregarPedido.setActionCommand("EntregarPedido");
        menuPrincipal.btnEntregarPedido.addActionListener(this);
        menuPrincipal.btnCancelarPedido.setActionCommand("MenuCancelacionPedido");
        menuPrincipal.btnCancelarPedido.addActionListener(this);
        menuPrincipal.btnModificarPrecio.setActionCommand("MenuModificacionPrecio");
        menuPrincipal.btnModificarPrecio.addActionListener(this);
        menuPrincipal.btnModificarStock.setActionCommand("MenuModificacionStock");
        menuPrincipal.btnModificarStock.addActionListener(this);
        menuPrincipal.btnAltaAlmacen.setActionCommand("MenuAltaAlmacen");
        menuPrincipal.btnAltaAlmacen.addActionListener(this);
        menuPrincipal.btnBajaAlmacen.setActionCommand("MenuBajaAlmacen");
        menuPrincipal.btnBajaAlmacen.addActionListener(this);
        menuPrincipal.btnAltaContrato.setActionCommand("MenuAltaContrato");
        menuPrincipal.btnAltaContrato.addActionListener(this);
        
        registroCliente.btnRegistrar.setActionCommand("ClienteRegistrar");
        registroCliente.btnRegistrar.addActionListener(this);
        registroCliente.btnVolver.setActionCommand("ClienteVolver");
        registroCliente.btnVolver.addActionListener(this);
        
        bajaCliente.btnBaja.setActionCommand("BajaCliente");
        bajaCliente.btnBaja.addActionListener(this);
        bajaCliente.btnVolver.setActionCommand("BajaVolver");
        bajaCliente.btnVolver.addActionListener(this);
        
        registroProveedor.btnRegistrar.setActionCommand("ProveedorRegistrar");
        registroProveedor.btnRegistrar.addActionListener(this);
        registroProveedor.btnVolver.setActionCommand("ProveedorVolver");
        registroProveedor.btnVolver.addActionListener(this);
        
        bajaProveedor.btnBaja.setActionCommand("BajaProveedor");
        bajaProveedor.btnBaja.addActionListener(this);
        bajaProveedor.btnVolver.setActionCommand("BajaProveedorVolver");
        bajaProveedor.btnVolver.addActionListener(this);
        
        registroTransportista.btnRegistrar.setActionCommand("TransportistaRegistrar");
        registroTransportista.btnRegistrar.addActionListener(this);
        registroTransportista.btnVolver.setActionCommand("TransportistaVolver");
        registroTransportista.btnVolver.addActionListener(this);
        
        bajaTransportista.btnBaja.setActionCommand("BajaTransportista");
        bajaTransportista.btnBaja.addActionListener(this);
        bajaTransportista.btnVolver.setActionCommand("BajaTransportistaVolver");
        bajaTransportista.btnVolver.addActionListener(this);
        
        registroProducto.btnRegistrar.setActionCommand("ProductoRegistrar");
        registroProducto.btnRegistrar.addActionListener(this);
        registroProducto.btnVolver.setActionCommand("RegistroProductoVolver");
        registroProducto.btnVolver.addActionListener(this);
        registroProducto.btnMostrarAlmacenes.setActionCommand("MostrarAlmacenes");
        registroProducto.btnMostrarAlmacenes.addActionListener(this);
        registroProducto.btnMostrarUbicaciones.setActionCommand("MostrarUbicaciones");
        registroProducto.btnMostrarUbicaciones.addActionListener(this);
        
        hacerPedido.btnIngresarProducto.setActionCommand("IngresarProducto");
        hacerPedido.btnIngresarProducto.addActionListener(this);
        hacerPedido.btnHacerPedido.setActionCommand("HacerPedido");
        hacerPedido.btnHacerPedido.addActionListener(this);
        hacerPedido.btnVolver.setActionCommand("HacerPedidoVolver");
        hacerPedido.btnVolver.addActionListener(this);
        
        verHistorial.btnVolver.setActionCommand("VerHistorialVolver");
        verHistorial.btnVolver.addActionListener(this);
        verHistorial.btnVerHistorial.setActionCommand("VerHistorial");
        verHistorial.btnVerHistorial.addActionListener(this);
        
        modificarCliente.btnVolver.setActionCommand("ModificacionClienteVolver");
        modificarCliente.btnVolver.addActionListener(this);
        modificarCliente.btnModificarCliente.setActionCommand("ClienteModificar");
        modificarCliente.btnModificarCliente.addActionListener(this);
        
        entregarPedido.btnVolver.setActionCommand("EntregarPedidoVolver");
        entregarPedido.btnVolver.addActionListener(this);
        entregarPedido.btnMostrarPedidos.setActionCommand("MostrarPedidos");
        entregarPedido.btnMostrarPedidos.addActionListener(this);
        entregarPedido.btnEntregar.setActionCommand("EntregaPedido");
        entregarPedido.btnEntregar.addActionListener(this);
        
        cancelarPedido.btnVolver.setActionCommand("CancelarPedidoVolver");
        cancelarPedido.btnVolver.addActionListener(this);
        cancelarPedido.btnMostrarPedidos.setActionCommand("CancelarMostrarPedidos");
        cancelarPedido.btnMostrarPedidos.addActionListener(this);
        cancelarPedido.btnCancelarPedido.setActionCommand("CancelarPedido");
        cancelarPedido.btnCancelarPedido.addActionListener(this);
        
        modificarPrecioProducto.btnVolver.setActionCommand("ModificarPrecioVolver");
        modificarPrecioProducto.btnVolver.addActionListener(this);
        modificarPrecioProducto.btnModificarPrecio.setActionCommand("ModificarPrecio");
        modificarPrecioProducto.btnModificarPrecio.addActionListener(this);
        
        modificarStockProducto.btnVolver.setActionCommand("ModificarStockVolver");
        modificarStockProducto.btnVolver.addActionListener(this);
        modificarStockProducto.btnModificarStock.setActionCommand("ModificarStock");
        modificarStockProducto.btnModificarStock.addActionListener(this);
        
        registroAlmacen.btnVolver.setActionCommand("RegistroAlmacenVolver");
        registroAlmacen.btnVolver.addActionListener(this);
        registroAlmacen.btnRegistrar.setActionCommand("AlmacenRegistrar");
        registroAlmacen.btnRegistrar.addActionListener(this);
        
        bajaAlmacen.btnVolver.setActionCommand("BajaAlmacenVolver");
        bajaAlmacen.btnVolver.addActionListener(this);
        bajaAlmacen.btnBaja.setActionCommand("BajaAlmacen");
        bajaAlmacen.btnBaja.addActionListener(this);
        
        registroContrato.btnVolver.setActionCommand("RegistroContratoVolver");
        registroContrato.btnVolver.addActionListener(this);
        registroContrato.btnRegistrar.setActionCommand("ContratoRegistrar");
        registroContrato.btnRegistrar.addActionListener(this);
        
        this.productos_id = new ArrayList<>();
        this.pedidos_id = new ArrayList<>();
        this.almacenes = new ArrayList<>();
        
        this.menuPrincipal.setLocationRelativeTo(null);
        this.registroCliente.setLocationRelativeTo(null);
        this.bajaCliente.setLocationRelativeTo(null);
        this.registroProveedor.setLocationRelativeTo(null);
        this.registroTransportista.setLocationRelativeTo(null);
        this.bajaProveedor.setLocationRelativeTo(null);
        this.bajaTransportista.setLocationRelativeTo(null);
        this.registroProducto.setLocationRelativeTo(null);
        this.hacerPedido.setLocationRelativeTo(null);
        this.verHistorial.setLocationRelativeTo(null);
        this.modificarCliente.setLocationRelativeTo(null);
        this.entregarPedido.setLocationRelativeTo(null);
        this.cancelarPedido.setLocationRelativeTo(null);
        this.modificarPrecioProducto.setLocationRelativeTo(null);
        this.modificarStockProducto.setLocationRelativeTo(null);
        this.registroAlmacen.setLocationRelativeTo(null);
        this.bajaAlmacen.setLocationRelativeTo(null);
        this.registroContrato.setLocationRelativeTo(null);
        
    }
    public void mostrarMenu(){
        this.menuPrincipal.setVisible(true);
    }
    
    public void altaCliente(Cliente cliente) throws Exception{
        
        controladorPersistencia.crearCliente(cliente);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("ClienteRegistrar".equals(e.getActionCommand()))
        {
            long dni = Long.parseLong(registroCliente.txtDni.getText());
            String nombre = registroCliente.txtNombre.getText();
            String apellido = registroCliente.txtApellido.getText();
            long telefono = Long.parseLong(registroCliente.txtTelefono.getText());
            String correo = registroCliente.txtCorreo.getText();
            String direccion = registroCliente.txtDireccion.getText();
            String usuario = registroCliente.txtUsuario.getText();
            String contraseña = registroCliente.txtContraseña.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try{
                date = dateFormat.parse(registroCliente.txtNacimiento.getText());
            }catch (ParseException f) {
                System.err.println("Error al parsear la fecha: " + f.getMessage());
            }
            Cliente clienteAlta;
            clienteAlta = new Cliente(dni,nombre,apellido,telefono,correo,direccion,usuario,contraseña,date);
            try {
                controladorPersistencia.crearCliente(clienteAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("ClienteVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroCliente.setVisible(false);
        }
        if("MenuAlta".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroCliente.setVisible(true);
        }
        if("MenuBaja".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            bajaCliente.setVisible(true);
        }
        if("BajaCliente".equals(e.getActionCommand())){
            int id = Integer.parseInt(bajaCliente.txtDni.getText());
            controladorPersistencia.borrarCliente(id);
        }
        if("BajaVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            bajaCliente.setVisible(false);
        }
        if("MenuAltaProveedor".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroProveedor.setVisible(true);
        }
        if("ProveedorRegistrar".equals(e.getActionCommand()))
        {
            long cuit = Long.parseLong(registroProveedor.cuit.getText());
            long telefono = Long.parseLong(registroProveedor.telefono.getText());
            String correo = registroProveedor.correo.getText();
            String categoria = registroProveedor.categoria.getText();
            String razon_social = registroProveedor.razonsocial.getText();
            Proveedor proveedorAlta;
            int id = controladorPersistencia.obtenerUltimoIDProveedor() + 1;
            proveedorAlta = new Proveedor(id,razon_social,telefono,correo,0,cuit,categoria);
            try {
                controladorPersistencia.crearProveedor(proveedorAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("ProveedorVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroProveedor.setVisible(false);
        }
        if("MenuAltaTransportista".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroTransportista.setVisible(true);
        }
        if("TransportistaRegistrar".equals(e.getActionCommand()))
        {
            long dni = Long.parseLong(registroTransportista.txtDNI.getText());
            String nombre = registroTransportista.txtNombre.getText();
            String apellido = registroTransportista.txtApellido.getText();
            Transportista transportistaAlta;
            transportistaAlta = new Transportista(dni,nombre,apellido,true);
            try {
                controladorPersistencia.crearTransportista(transportistaAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("TransportistaVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroTransportista.setVisible(false);
        }
        if("MenuBajaProveedor".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            bajaProveedor.setVisible(true);
        }
        if("BajaProveedor".equals(e.getActionCommand())){
            int id = Integer.parseInt(bajaProveedor.txtCUIT.getText());
            controladorPersistencia.borrarProveedor(id);
        }
        if("BajaProveedorVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            bajaProveedor.setVisible(false);
        }
        if("MenuBajaTransportista".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            bajaTransportista.setVisible(true);
        }
        if("BajaTransportista".equals(e.getActionCommand())){
            int dni = Integer.parseInt(bajaTransportista.txtDNI.getText());
            controladorPersistencia.borrarTransportista(dni);
        }
        if("BajaTransportistaVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            bajaTransportista.setVisible(false);
        }
        if("MenuAltaProducto".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroProducto.setVisible(true);
        }
        if("ProductoRegistrar".equals(e.getActionCommand()))
        {
            int id = controladorPersistencia.obtenerUltimoIDProducto() + 1;
            String nombre = registroProducto.txtNombre.getText();
            String categoria = registroProducto.txtCategoria.getText();
            int stock = Integer.parseInt(registroProducto.txtStock.getText());
            float precio = Float.parseFloat(registroProducto.txtPrecio.getText());
            String nombreProveedor = registroProducto.txtProveedor.getText();
            int id_proveedor = controladorPersistencia.encontrarProveedorPorNombre(nombreProveedor);
            long id_almacen = Long.parseLong(registroProducto.txtIDAlmacen.getText());
            int id_ubicacion = Integer.parseInt(registroProducto.txtIDUbicacion.getText());
            Producto productoAlta;
            productoAlta = new Producto(id,nombre,categoria,true,precio,stock,id_proveedor);
            try {
                controladorPersistencia.crearProducto(productoAlta);
                controladorPersistencia.insertarRelacionProductoAlmacen(id_almacen,id);
                controladorPersistencia.insertarRelacionProductoUbicacion(id,id_ubicacion);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("RegistroProductoVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroProducto.setVisible(false);
        }
        if("MenuPedido".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            hacerPedido.setVisible(true);
            List<String> productos = controladorPersistencia.obtenerNombresProductos();
            StringBuilder productosDisponibles = new StringBuilder();
            for (String producto : productos) {
                productosDisponibles.append(producto).append("\n");
            }
            hacerPedido.txtProductosDisponibles.setText(productosDisponibles.toString());
        }
        if("IngresarProducto".equals(e.getActionCommand())){
            String producto = hacerPedido.txtProductos.getText();
            this.productos_id.add(controladorPersistencia.encontrarProductoPorNombre(producto));
            for (int id : this.productos_id) {
                System.out.println("ID: " + id);
            }
            
        }
        if("HacerPedido".equals(e.getActionCommand()))
        {
            long id = controladorPersistencia.obtenerUltimoIDPedido() + 1;
            String estado = "En Camino";
            long dni = Long.parseLong(hacerPedido.txtDNI.getText());
            String destino = hacerPedido.txtDestino.getText();
            Date fecha_creacion = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha_creacion);
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            Date fecha_entrega = calendar.getTime();
            long dni_transportista = controladorPersistencia.obtenerDniPrimerTransportistaDisponible();
            int id_centro = controladorPersistencia.obtenerIdPrimerCentroDeDistribucion();
            long numero = Long.parseLong(hacerPedido.txtNumeroTarjeta.getText());
            String tipo = hacerPedido.listaTipo.getSelectedValue().toString();
            MetodoDePago metodoAlta;
            metodoAlta = new MetodoDePago(id,numero,tipo);
            Pedido pedidoAlta;
            pedidoAlta = new Pedido(id,destino,estado,fecha_entrega,fecha_creacion,id,dni_transportista,id_centro,dni);
            try {
                controladorPersistencia.crearMetodoPago(metodoAlta);
                controladorPersistencia.crearPedido(pedidoAlta);
                controladorPersistencia.insertarRelacionClientePedido(dni, id);
                for (int iter : this.productos_id) {
                    controladorPersistencia.insertarRelacionProductoPedido(id, iter);
                }
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("HacerPedidoVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            hacerPedido.setVisible(false);
        }
        if("MenuVerHistorial".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            verHistorial.setVisible(true);
        }
        if("VerHistorialVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            verHistorial.setVisible(false);
        }
        if("VerHistorial".equals(e.getActionCommand())){
            float total_pedido = 0;
            long dni = Long.parseLong(verHistorial.txtDNI.getText());
            this.pedidos_id = controladorPersistencia.obtenerPedidosPorDNI(dni);
            for (long iter : this.pedidos_id) {
                String estado = controladorPersistencia.obtenerEstadoPorCodigoPedido(iter);
                Date fechaentrega = controladorPersistencia.obtenerFechaEntregaPorCodigoPedido(iter);
                this.productos_id = controladorPersistencia.obtenerProductosIDPorCodigoPedido(iter);
                String mensaje_producto = "";
                for (int iter2 : this.productos_id) {
                    String nombre = controladorPersistencia.obtenerNombreProductoPorID(iter2);
                    float precio = controladorPersistencia.obtenerPrecioProductoPorID(iter2);
                    total_pedido += precio;
                    mensaje_producto += "Producto: " + nombre + " Precio: " + precio + "\n";
                }
                JOptionPane.showMessageDialog(null,"Pedido ID: " + iter + "\n" + "Estado: " + estado + "\n" + "Fecha de Entrega: " + fechaentrega + "\n" + mensaje_producto + "\n" + "Precio del pedido : $" + total_pedido);
                total_pedido = 0;
            }
        }
        if("MenuModificacion".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            modificarCliente.setVisible(true);
        }
        if("ClienteModificar".equals(e.getActionCommand()))
        {
            long dni = Long.parseLong(modificarCliente.txtDNI.getText());
            String nombre = modificarCliente.txtnombre.getText();
            String apellido = modificarCliente.txtapellido.getText();
            long telefono = Long.parseLong(modificarCliente.txttelefono.getText());
            String correo = modificarCliente.txtcorreo.getText();
            String direccion = modificarCliente.txtdireccion.getText();
            String usuario = modificarCliente.txtusuario.getText();
            String contraseña = modificarCliente.txtcontraseña.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try{
                date = dateFormat.parse(modificarCliente.txtnacimiento.getText());
            }catch (ParseException ee) {
                System.err.println("Error al parsear la fecha: " + ee.getMessage());
            }
            Cliente clienteAlta;
            clienteAlta = new Cliente(dni,nombre,apellido,telefono,correo,direccion,usuario,contraseña,date);
            try {
                controladorPersistencia.actualizarClientePorDNI(dni, clienteAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("ModificacionClienteVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            modificarCliente.setVisible(false);
        }
        if("EntregarPedido".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            entregarPedido.setVisible(true);
        }
        if("EntregarPedidoVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            entregarPedido.setVisible(false);
        }
        if("MostrarPedidos".equals(e.getActionCommand())){
            long dni = Long.parseLong(entregarPedido.txtDNI.getText());
            this.pedidos_id = controladorPersistencia.obtenerPedidosPorDNITransportista(dni);
            String mensaje = "";
            for (long iter : this.pedidos_id) {
                String estado = controladorPersistencia.obtenerEstadoPorCodigoPedido(iter);
                String destino = controladorPersistencia.obtenerDestinoPorCodigoPedido(iter);
                mensaje = mensaje + "Pedido ID: " + iter + "\n" + "Estado: " + estado + "\n" + "Destino: " + destino + "\n" + "--------------------------" + "\n";
            }
            JOptionPane.showMessageDialog(null,mensaje);
        }
        if("EntregaPedido".equals(e.getActionCommand())){
            long codigo = Long.parseLong(entregarPedido.txtID.getText());
            controladorPersistencia.actualizarEstadoPedido(codigo, "Entregado");
        }
        if("MenuCancelacionPedido".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            cancelarPedido.setVisible(true);
        }
        if("CancelarPedidoVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            cancelarPedido.setVisible(false);
        }
        if("CancelarMostrarPedidos".equals(e.getActionCommand())){
            float total_pedido = 0;
            long dni = Long.parseLong(cancelarPedido.txtDNI.getText());
            this.pedidos_id = controladorPersistencia.obtenerPedidosPorDNI(dni);
            String mensaje = "";
            for (long iter : this.pedidos_id) {
                String estado = controladorPersistencia.obtenerEstadoPorCodigoPedido(iter);
                Date fechaentrega = controladorPersistencia.obtenerFechaEntregaPorCodigoPedido(iter);
                this.productos_id = controladorPersistencia.obtenerProductosIDPorCodigoPedido(iter);
                String mensaje_producto = "";
                for (int iter2 : this.productos_id) {
                    String nombre = controladorPersistencia.obtenerNombreProductoPorID(iter2);
                    float precio = controladorPersistencia.obtenerPrecioProductoPorID(iter2);
                    total_pedido += precio;
                    mensaje_producto += "Producto: " + nombre + " Precio: " + precio + "\n";
                }
                mensaje +=  "Pedido ID: " + iter + "\n" + "Estado: " + estado + "\n" + "Fecha de Entrega: " + fechaentrega + "\n" + mensaje_producto + "\n" + "Precio del pedido : $" + total_pedido + "\n" + "-----------------" + "\n";
                total_pedido = 0;
            }
            JOptionPane.showMessageDialog(null,mensaje);
        }
        if("CancelarPedido".equals(e.getActionCommand())){
            long codigo = Long.parseLong(cancelarPedido.txtID.getText());
            controladorPersistencia.actualizarEstadoPedido(codigo, "Cancelado");
        }
        if("MenuModificacionPrecio".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            modificarPrecioProducto.setVisible(true);
        }
        if("ModificarPrecio".equals(e.getActionCommand())){
            String nombre_producto = modificarPrecioProducto.txtNombre.getText();
            float nuevo_precio = Float.parseFloat(modificarPrecioProducto.txtPrecio.getText());
            controladorPersistencia.actualizarPrecioProducto(nombre_producto, nuevo_precio);
        }
        if("ModificarPrecioVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            modificarPrecioProducto.setVisible(false);
        }
        if("MenuModificacionStock".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            modificarStockProducto.setVisible(true);
        }
        if("ModificarStockVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            modificarStockProducto.setVisible(false);
        }
        if("ModificarStock".equals(e.getActionCommand())){
            String nombre_producto = modificarStockProducto.txtNombre.getText();
            int nuevo_stock = Integer.parseInt(modificarStockProducto.txtStock.getText());
            controladorPersistencia.actualizarStockProducto(nombre_producto, nuevo_stock);
        }
        if("MenuAltaAlmacen".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroAlmacen.setVisible(true);
        }
        if("AlmacenRegistrar".equals(e.getActionCommand()))
        {
           String ubicacion = registroAlmacen.txtUbicacion.getText();
           long id = controladorPersistencia.obtenerUltimoIdRegistradoAlmacen() + 1;
           Almacen almacenAlta;
           almacenAlta = new Almacen(id,ubicacion);
            try {
                controladorPersistencia.crearAlmacen(almacenAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("RegistroAlmacenVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroAlmacen.setVisible(false);
        }
        if("MenuBajaAlmacen".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            bajaAlmacen.setVisible(true);
            this.almacenes = controladorPersistencia.encontrarAlmacenes();
            String mensaje = "";
            for (Almacen iter : this.almacenes) {
                mensaje += "Almacen ID: " + iter.getId() + "\n" + "Ubicacion: " + iter.getUbicacion() + "\n" + "-----------------------------------" + "\n";
            }
            JOptionPane.showMessageDialog(null,mensaje);
        }
        if("BajaAlmacenVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            bajaAlmacen.setVisible(false);
        }
        if("BajaAlmacen".equals(e.getActionCommand())){
            long id = Integer.parseInt(bajaAlmacen.txtID.getText());
            controladorPersistencia.borrarAlmacen(id);
        }
        if("MostrarAlmacenes".equals(e.getActionCommand())){
            this.almacenes = controladorPersistencia.encontrarAlmacenes();
            String mensaje = "";
            for (Almacen iter : this.almacenes) {
                mensaje += "Almacen ID: " + iter.getId() + "\n" + "Ubicacion: " + iter.getUbicacion() + "\n" + "-----------------------------------" + "\n";
            }
            JOptionPane.showMessageDialog(null,mensaje);
        }
        if("MenuAltaContrato".equals(e.getActionCommand())){
            menuPrincipal.setVisible(false);
            registroContrato.setVisible(true);
        }
        if("ContratoRegistrar".equals(e.getActionCommand()))
        {
            String nombreProveedor = registroContrato.txtNombreProveedor.getText();
            int idProveedor = controladorPersistencia.encontrarProveedorPorNombre(nombreProveedor);
            String descripcion = registroContrato.txtDescripcion.getText();
            float valor = Float.parseFloat(registroContrato.txtValor.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = null;
            Date fechaFin = null;
            int id = controladorPersistencia.obtenerUltimoIdRegistradoContrato() + 1;
            try{
                fechaInicio = dateFormat.parse(registroContrato.txtFechaInicio.getText());
                fechaFin = dateFormat.parse(registroContrato.txtFechaFin.getText());
            }catch (ParseException f) {
                System.err.println("Error al parsear la fecha: " + f.getMessage());
            }
            Contrato contratoAlta;
            contratoAlta = new Contrato(id,descripcion,valor,fechaFin,fechaInicio,idProveedor);
            try {
                controladorPersistencia.crearContrato(contratoAlta);
            } catch (Exception ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("RegistroContratoVolver".equals(e.getActionCommand())){
            menuPrincipal.setVisible(true);
            registroContrato.setVisible(false);
        }
        if("MostrarUbicaciones".equals(e.getActionCommand())){
            long id_almacen = Long.parseLong(registroProducto.txtIDAlmacen.getText());
            this.ubicaciones = controladorPersistencia.obtenerIDUbicacionesPorIDAlmacen(id_almacen);
            String mensaje = "";
            for (int iter : this.ubicaciones) {
                mensaje += "Ubicaion ID: " + iter + "\n" + "-----------------------------------" + "\n";
            }
            JOptionPane.showMessageDialog(null,mensaje);
        }
    }
}