package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Usuario;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(Fazenda.class)
public class Fazenda_ { 

    public static volatile ListAttribute<Fazenda, Animal> animalList;
    public static volatile SingularAttribute<Fazenda, String> cidade;
    public static volatile SingularAttribute<Fazenda, String> estado;
    public static volatile ListAttribute<Fazenda, Usuario> usuarioList;
    public static volatile SingularAttribute<Fazenda, String> nome;
    public static volatile ListAttribute<Fazenda, UsuarioHasFazenda> usuarioHasFazendaList;
    public static volatile SingularAttribute<Fazenda, Integer> id;

}