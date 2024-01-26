package MODELO;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Carga.class)
public class Carga_ { 

    public static volatile SingularAttribute<Carga, Long> id;
    public static volatile SingularAttribute<Carga, LocalDate> fechaPartida;
    public static volatile SingularAttribute<Carga, Long> almacen_id;
    public static volatile SingularAttribute<Carga, String> observacion;

}