package br.com.ufra.sysbufalo.entidade;

import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Usuario;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazendaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-26T20:08:28")
@StaticMetamodel(UsuarioHasFazenda.class)
public class UsuarioHasFazenda_ { 

    public static volatile SingularAttribute<UsuarioHasFazenda, UsuarioHasFazendaPK> usuarioHasFazendaPK;
    public static volatile SingularAttribute<UsuarioHasFazenda, Fazenda> fazenda1;
    public static volatile SingularAttribute<UsuarioHasFazenda, Usuario> usuario1;
    public static volatile SingularAttribute<UsuarioHasFazenda, String> perfil;

}