package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Ordenha;
import br.com.ufra.sysbufalo.entidade.Parto;
import br.com.ufra.sysbufalo.entidade.PesoAnimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(Animal.class)
public class Animal_ { 

    public static volatile ListAttribute<Animal, Parto> partoList;
    public static volatile ListAttribute<Animal, Ordenha> ordenhaList;
    public static volatile SingularAttribute<Animal, String> brinco;
    public static volatile ListAttribute<Animal, PesoAnimal> pesoAnimalList;
    public static volatile SingularAttribute<Animal, Integer> id;
    public static volatile SingularAttribute<Animal, Fazenda> fazenda;

}