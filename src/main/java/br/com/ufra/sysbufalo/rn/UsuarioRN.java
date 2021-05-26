package br.com.ufra.sysbufalo.rn;

import br.com.ufra.sysbufalo.dao.UsuarioDAO;
import br.com.ufra.sysbufalo.entidade.Usuario;
import java.io.Serializable;
import java.util.List;

public class UsuarioRN implements Serializable {

    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    public boolean salvar(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getId() == null || usuario.getId() == 0) {
                return USUARIO_DAO.criar(usuario);
            } else {
                return USUARIO_DAO.alterar(usuario);
            }
        }
    }

    public List<Usuario> obterTodos() {
        return USUARIO_DAO.obterTodos(Usuario.class);
    }

    public Usuario obter(Integer id) {
        return USUARIO_DAO.obter(Usuario.class, id);
    }

    public boolean excluir(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            return USUARIO_DAO.excluir(usuario);

        }
    }

    public Usuario verificaUsuario(Usuario u) {
        return USUARIO_DAO.verificaUsuario(u);
    }
}
