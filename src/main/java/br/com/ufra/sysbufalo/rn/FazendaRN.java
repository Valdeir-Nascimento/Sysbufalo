
package br.com.ufra.sysbufalo.rn;

import br.com.ufra.sysbufalo.dao.GenericDAO;
import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Usuario;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda;
import java.util.ArrayList;
import java.util.List;


public class FazendaRN {
    private final GenericDAO<Fazenda> FAZENDA_DAO = new GenericDAO<Fazenda>();
    private final GenericDAO<UsuarioHasFazenda> USUARIOFAZENDA_DAO = new GenericDAO<UsuarioHasFazenda>();
    
    public boolean salvar(Fazenda fazenda) {
        if (fazenda == null) {
            return false;
        } else {
            if (fazenda.getId() == null || fazenda.getId() == 0) {
                return FAZENDA_DAO.criar(fazenda);
            } else {
                return FAZENDA_DAO.alterar(fazenda);
            }
        }
    }

    public List<Fazenda> obterTodos() {
        return FAZENDA_DAO.obterTodos(Fazenda.class);
    }
    
    public List<Fazenda> obterTodosUsuario(Usuario u) {
        List<Fazenda> retorno = new ArrayList<>();
        List<UsuarioHasFazenda> listaUF = FAZENDA_DAO.obterTodasFazendasUsuario(u);
        for (UsuarioHasFazenda uf : listaUF) {
            Fazenda f = uf.getFazenda1();
            f.setPerfil(uf.getPerfil());
            retorno.add(f);
        }
        return retorno;
    }

    public Fazenda obter(Integer id) {
        return FAZENDA_DAO.obter(Fazenda.class, id);
    }
    
    public boolean excluir(Fazenda fazenda) {
        if (fazenda == null) {
            return false;
        } else {
            return FAZENDA_DAO.excluir(fazenda);
            
        }
    }
    
    public boolean salvarUsuarioFazenda(UsuarioHasFazenda uf) {
        return USUARIOFAZENDA_DAO.criar(uf);
    }
}
