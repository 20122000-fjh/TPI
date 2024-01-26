package MODELO;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(OrdenDeCompra.class)
public class OrdenDeCompra_ { 

    public static volatile SingularAttribute<OrdenDeCompra, Integer> numero_orden;
    public static volatile SingularAttribute<OrdenDeCompra, LocalDate> fecha;
    public static volatile SingularAttribute<OrdenDeCompra, String> condicion_venta;

}