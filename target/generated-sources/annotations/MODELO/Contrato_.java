package MODELO;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T11:11:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Contrato.class)
public class Contrato_ { 

    public static volatile SingularAttribute<Contrato, String> descripcion;
    public static volatile SingularAttribute<Contrato, Date> fecha_inicio;
    public static volatile SingularAttribute<Contrato, Float> valor;
    public static volatile SingularAttribute<Contrato, Integer> proveedor_id;
    public static volatile SingularAttribute<Contrato, Integer> id;
    public static volatile SingularAttribute<Contrato, Date> fecha_finalizacion;

}