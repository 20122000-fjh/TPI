package MODELO;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Descarga.class)
public class Descarga_ { 

    public static volatile SingularAttribute<Descarga, LocalDate> fecha_llegada;
    public static volatile SingularAttribute<Descarga, Long> id;
    public static volatile SingularAttribute<Descarga, Long> almacen_id;
    public static volatile SingularAttribute<Descarga, String> observacion;

}