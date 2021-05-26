package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Animal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(PesoAnimal.class)
public class PesoAnimal_ { 

    public static volatile SingularAttribute<PesoAnimal, Date> data;
    public static volatile SingularAttribute<PesoAnimal, Float> peso;
    public static volatile SingularAttribute<PesoAnimal, Animal> animal;
    public static volatile SingularAttribute<PesoAnimal, Integer> id;

}