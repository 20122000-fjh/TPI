package MODELO;

import MODELO.Producto;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Long> codigo;
    public static volatile SingularAttribute<Pedido, String> estado;
    public static volatile SingularAttribute<Pedido, Integer> id_centrodistribucion;
    public static volatile SingularAttribute<Pedido, Long> numero_tarjeta;
    public static volatile SingularAttribute<Pedido, Date> fechaEntrega;
    public static volatile SingularAttribute<Pedido, Date> fechaCreacion;
    public static volatile SingularAttribute<Pedido, String> destino;
    public static volatile SingularAttribute<Pedido, Long> dni_cliente;
    public static volatile ListAttribute<Pedido, Producto> productos;
    public static volatile SingularAttribute<Pedido, Long> transportista_dni;

}