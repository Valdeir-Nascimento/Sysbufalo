package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Ordenhador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(Ordenha.class)
public class Ordenha_ { 

    public static volatile SingularAttribute<Ordenha, Date> data;
    public static volatile SingularAttribute<Ordenha, Animal> animal;
    public static volatile SingularAttribute<Ordenha, Integer> id;
    public static volatile SingularAttribute<Ordenha, Boolean> manual;
    public static volatile SingularAttribute<Ordenha, Ordenhador> ordenhador;
    public static volatile SingularAttribute<Ordenha, Float> quantidadeProduzida;

}