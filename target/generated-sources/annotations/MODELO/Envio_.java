package MODELO;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Integer> codigo;
    public static volatile SingularAttribute<Envio, LocalDate> fecha_pedido;
    public static volatile SingularAttribute<Envio, LocalDate> fecha_entrega;
    public static volatile SingularAttribute<Envio, Integer> num_ordencompra;

}