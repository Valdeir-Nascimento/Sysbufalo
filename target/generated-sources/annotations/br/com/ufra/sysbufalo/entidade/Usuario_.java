package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, String> celular;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile ListAttribute<Usuario, UsuarioHasFazenda> usuarioHasFazendaList;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, Fazenda> fazenda;
    public static volatile SingularAttribute<Usuario, String> email;

}