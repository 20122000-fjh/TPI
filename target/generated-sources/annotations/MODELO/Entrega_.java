package MODELO;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-25T08:18:43", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile SingularAttribute<Entrega, LocalDate> fecha;
    public static volatile SingularAttribute<Entrega, String> estado;
    public static volatile SingularAttribute<Entrega, Long> numeroSeguimiento;
    public static volatile SingularAttribute<Entrega, String> destino;

}