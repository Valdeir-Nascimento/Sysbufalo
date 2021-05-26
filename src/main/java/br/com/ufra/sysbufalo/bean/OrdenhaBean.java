package br.com.ufra.sysbufalo.bean;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Ordenha;
import br.com.ufra.sysbufalo.rn.AnimalRN;
import br.com.ufra.sysbufalo.rn.OrdenhaRN;
import br.com.ufra.sysbufalo.util.UtilBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class OrdenhaBean {

    private final OrdenhaRN ORDENHA_RN = new OrdenhaRN();
    private List<Ordenha> ordenhas;
    private Ordenha ordenha = new Ordenha();
    private Animal animal;

    public OrdenhaBean() {
    }

    @PostConstruct
    public void init() {
        String animal_id = UtilBean.obterValor("animal_id");
        String ordenha_id = UtilBean.obterValor("ordenha_id");

        try {
            if (animal_id != null) {
                AnimalRN animalRN = new AnimalRN();
                animal = animalRN.obter(new Integer(animal_id));
                ordenhas = ORDENHA_RN.obter(animal);
            }
            if (ordenha_id != null) {
                ordenha = ORDENHA_RN.obter(new Integer(ordenha_id));
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public List<Ordenha> getOrdenhas() {
        return ordenhas;
    }

    public void setOrdenhas(List<Ordenha> ordenhas) {
        this.ordenhas = ordenhas;
    }

    public Ordenha getOrdenha() {
        return ordenha;
    }

    public void setOrdenha(Ordenha ordenha) {
        this.ordenha = ordenha;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void novo() {
        ordenha = new Ordenha();
        ordenha.setAnimal(animal);
    }

    public void salvar() {
        if (ordenha != null) {
            if (ORDENHA_RN.salvar(ordenha)) {
                UtilBean.criarMensagemDeInformacao("Ordenha salva com sucesso!");
                ordenhas = ORDENHA_RN.obterTodos();
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível salvar Ordenha");
            }
        } else {
            UtilBean.criarMensagemDeErro("Nenhuma ordenha selecionada");
        }
    }

    public void excluir() {
        if (ordenha != null) {
            if (ORDENHA_RN.excluir(ordenha)) {
                UtilBean.criarMensagemDeInformacao("Operação de exclusão da Ordenha realizada com sucesso!");
                ordenhas = ORDENHA_RN.obterTodos();
            } else {
                UtilBean.criarMensagemDeErro("Erro ao excluir ordenha!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhuma ordenha selecionada.");
        }
    }

}
