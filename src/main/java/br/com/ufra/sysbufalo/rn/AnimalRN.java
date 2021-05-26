
package br.com.ufra.sysbufalo.rn;

import br.com.ufra.sysbufalo.dao.AnimalDAO;
import br.com.ufra.sysbufalo.dao.OrdenhaDAO;
import br.com.ufra.sysbufalo.dao.PartoDAO;
import br.com.ufra.sysbufalo.dao.PesoAnimalDAO;
import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Parto;
import br.com.ufra.sysbufalo.entidade.PesoAnimal;

import java.util.List;



public class AnimalRN {
    private final AnimalDAO ANIMAL_DAO = new AnimalDAO();
    private final PesoAnimalDAO PESOANIMAL_DAO = new PesoAnimalDAO();
    private final PartoDAO PARTO_DAO = new PartoDAO();
    private final OrdenhaDAO ORDENHA_DAO = new OrdenhaDAO();

    public boolean salvar(Animal animal) {
        if (animal == null) {
            return false;
        } else {
            if (animal.getId() == null || animal.getId() == 0) {
                return ANIMAL_DAO.criar(animal);
            } else {
                return ANIMAL_DAO.alterar(animal);
            }
        }
    }

    public List<Animal> obterTodos() {
        return ANIMAL_DAO.obterTodos(Animal.class);
    }

    public Animal obter(Integer id) {
        return ANIMAL_DAO.obter(Animal.class, id);
    }

    public boolean excluir(Animal animal) {
        if (animal == null) {
            return false;
        } else {
            return ANIMAL_DAO.excluir(animal);
        }
    }
    
    public List<Animal> obter(Fazenda fazenda){
        return ANIMAL_DAO.obter(fazenda);
    }
    
    public Boolean cadastrarPeso(PesoAnimal peso) {
        return PESOANIMAL_DAO.criar(peso);
    } 
    
    public Boolean excluirPeso(PesoAnimal peso) {
        return PESOANIMAL_DAO.excluir(peso);
    } 
    
    public List<Parto> obterPartos(Animal a) {
        return PARTO_DAO.obterListaPartoPorAnimal(a);
    }
    
    public int obterOrdemParto(Animal a) {
        return PARTO_DAO.obterOrdemParto(a);
    }
    
    public Boolean cadastrarParto(Parto p) {
        if (p.getId() == null || p.getId() == 0) {
            return PARTO_DAO.criar(p);
        } else {
            return PARTO_DAO.alterar(p);
        }
    } 
    
    public Boolean excluirParto(Parto p) {
        return PARTO_DAO.excluir(p);
    } 
    
}
