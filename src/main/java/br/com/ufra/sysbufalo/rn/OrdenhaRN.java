package br.com.ufra.sysbufalo.rn;

import br.com.ufra.sysbufalo.dao.OrdenhaDAO;
import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Ordenha;
import br.com.ufra.sysbufalo.entidade.Ordenhador;
import br.com.ufra.sysbufalo.entidade.vo.OrdenhaVO;
import java.util.ArrayList;
import java.util.List;


public class OrdenhaRN {

    private final OrdenhaDAO ORDENHA_DAO = new OrdenhaDAO();

    public boolean salvar(Ordenha ordenha) {
        if (ordenha == null) {
            return false;
        } else {
            Ordenhador ordenhador = ORDENHA_DAO.buscaOrdenhadorPorNome(ordenha.getNmOrdenhador());
            if (ordenhador == null) {
                ordenhador = new Ordenhador();
                ordenhador.setNome(ordenha.getNmOrdenhador());
                ordenhador = (Ordenhador) ORDENHA_DAO.criarComRetorno(ordenhador);
            }
            ordenha.setOrdenhador(ordenhador);
            if (ordenha.getId() == null || ordenha.getId() == 0) {
                return ORDENHA_DAO.criar(ordenha);
            } else {
                return ORDENHA_DAO.alterar(ordenha);
            }
        }
    }

    public List<Ordenha> obterTodos() {
        return ORDENHA_DAO.obterTodos(Ordenha.class);
    }

    public Ordenha obter(Integer id) {
        return ORDENHA_DAO.obter(Ordenha.class, id);
    }

    public List<Ordenha> obter(Animal animal) {
        return ORDENHA_DAO.obter(animal);
    }
    
    public List<OrdenhaVO> obterListaRelatorio(Animal animal) {
        List<OrdenhaVO> retorno = null;
        List<Ordenha> lista = ORDENHA_DAO.obter(animal);
        if (lista != null && lista.size() > 0) {
            retorno = new ArrayList<>();
            for (Ordenha o : lista) {
                OrdenhaVO vo = new OrdenhaVO();
                vo.setFazenda(o.getAnimal().getFazenda());
                vo.setAnimal(o.getAnimal());
                vo.setData(o.getData());
                vo.setManual(o.getManual());
                vo.setOrdenhador(o.getOrdenhador());
                vo.setQuantidadeProduzida(o.getQuantidadeProduzida());
                retorno.add(vo);
            }
        }
        return retorno;
    }

    public boolean excluir(Ordenha ordenha) {
        if (ordenha == null) {
            return false;
        } else {
            return ORDENHA_DAO.excluir(ordenha);

        }
    }
    
}
