package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Animal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(Parto.class)
public class Parto_ { 

    public static volatile SingularAttribute<Parto, Date> fimProducao;
    public static volatile SingularAttribute<Parto, Integer> ordem;
    public static volatile SingularAttribute<Parto, Date> dataParto;
    public static volatile SingularAttribute<Parto, Animal> animal;
    public static volatile SingularAttribute<Parto, Integer> id;
    public static volatile SingularAttribute<Parto, Date> inicioProducao;

}