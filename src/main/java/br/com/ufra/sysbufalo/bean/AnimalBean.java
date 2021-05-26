
package br.com.ufra.sysbufalo.bean;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Ordenha;
import br.com.ufra.sysbufalo.entidade.Parto;
import br.com.ufra.sysbufalo.entidade.PesoAnimal;
import br.com.ufra.sysbufalo.rn.AnimalRN;
import br.com.ufra.sysbufalo.rn.OrdenhaRN;
import br.com.ufra.sysbufalo.util.UtilBean;
import br.com.ufra.sysbufalo.util.UtilRelatorios;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@SessionScoped
public class AnimalBean {

    private final AnimalRN ANIMAL_RN = new AnimalRN();
    private final OrdenhaRN ORDENHA_RN = new OrdenhaRN();
    private Fazenda fazenda;
    private Animal animal = new Animal();
    private Animal animalSelecionado;
    private List<Animal> animais;
    
    private PesoAnimal pesoSelecionado;
    private Parto partoSelecionado;
    private Ordenha ordenhaSelecionado;
    
    public AnimalBean() {
    }
    
    public String preparaNovoAnimal(){
        fazenda = (Fazenda) UtilBean.getAttribute("fazendaSelecionada");
        animal = new Animal();
        animal.setFazenda(fazenda);
        return "novoAnimal";
    }
    
    public String preparaVisualizarAnimal() {
        animal = animalSelecionado;
        UtilBean.setAttribute("animalSelecionado", animal);  
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SysbufaloUFRA/pages/animal/visualizarAnimal.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "visualizarAnimal";
    }
    
    public String preparaAlterarAnimal() {
        animal = animalSelecionado;
        return "editarAnimal";
    }

    public void novo() {
        animal = new Animal();
        animal.setFazenda(fazenda);
    }
    
     public String salvar() {
        if (animal != null) {
            Integer novo = animal.getId();
            if (ANIMAL_RN.salvar(animal)) {
                UtilBean.criarMensagemDeInformacao(animal.getBrinco()+ " Salvo com sucesso!");
                animais = ANIMAL_RN.obter(fazenda);
                if(novo == null || novo == 0){
                    return "visualizarFazenda";
                } else {
                    return "visualizarAnimal";
                }
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível salvar " + animal.getBrinco());
            }
        } else {
            UtilBean.criarMensagemDeErro("Nenhum animal selecionado");
        }
        return "";
    }
    
    public String excluir() {
        if (animal != null) {
            if (ANIMAL_RN.excluir(animal)) {
                UtilBean.criarMensagemDeInformacao("Operação de exclusão do animal realizada com sucesso!");
                animais = ANIMAL_RN.obterTodos();
                return "visualizarFazenda";
            } else {
                UtilBean.criarMensagemDeErro("Erro ao excluir animal!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public String prepararNovoPeso(){
        pesoSelecionado = new PesoAnimal();
        return "novoPeso";
    }
    
    public String cadastrarPeso() {
        if (animal != null) {
            pesoSelecionado.setAnimal(animal);
            pesoSelecionado.setData(new Date());
            if (ANIMAL_RN.cadastrarPeso(pesoSelecionado)) {
                animais = ANIMAL_RN.obterTodos();
                for(Animal a:animais){
                    if(a.getId().equals(animal.getId())){
                        animal.setPesoAnimalList(a.getPesoAnimalList());
                    }
                }
                pesoSelecionado = new PesoAnimal();
                UtilBean.criarMensagemDeInformacao("Operação de cadastro de peso realizada com sucesso!");
                return "visualizarAnimal";
            } else {
                UtilBean.criarMensagemDeErro("Erro ao cadastrar!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public String excluirPeso() {
        if (animal != null) {
            if (pesoSelecionado != null) {
                if (ANIMAL_RN.excluirPeso(pesoSelecionado)) {
                    animais = ANIMAL_RN.obterTodos();
                    for(Animal a:animais){
                        if(a.getId().equals(animal.getId())){
                            animal.setPesoAnimalList(a.getPesoAnimalList());
                        }
                    }
                    pesoSelecionado = new PesoAnimal();
                    UtilBean.criarMensagemDeInformacao("Operação de exclusão de peso realizada com sucesso!");
                    return "visualizarAnimal";
                } else {
                    UtilBean.criarMensagemDeErro("Erro ao excluir!");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Nenhum peso selecionado.");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public void gerarRelatorio() throws JRException, FileNotFoundException{
        try {
            HashMap parametros = new HashMap();
            //parametros.put("animal_id", animal.getId());
            UtilRelatorios.imprimeRelatorio("OrdenhaPorAnimal", parametros, ORDENHA_RN.obterListaRelatorio(animal));
        } catch (Exception ex) {
            Logger.getLogger(AnimalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String prepararNovoParto(){
        partoSelecionado = new Parto();
        return "novoParto";
    }
    
    public String preparaAlterarParto(){
        if(animal != null && partoSelecionado != null) {
            return "editarParto";
        }else{
            UtilBean.criarMensagemDeAviso("Nenhum parto selecionado.");
            return "";
        }
    }
    
    public String cadastrarParto(){
        if(animal != null && partoSelecionado != null) {
            partoSelecionado.setAnimal(animal);
            //partoSelecionado.setOrdem(ANIMAL_RN.obterOrdemParto(animal));
            if (ANIMAL_RN.cadastrarParto(partoSelecionado)) {
                UtilBean.criarMensagemDeInformacao("Operação de cadastro de parto realizada com sucesso!");
                animais = ANIMAL_RN.obterTodos();
                for(Animal a:animais){
                    if(a.getId().equals(animal.getId())){
                        animal.setPartoList(a.getPartoList());
                    }
                }
                partoSelecionado = new Parto();
                return "visualizarAnimal";
            } else {
                UtilBean.criarMensagemDeErro("Erro ao cadastrar!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public String excluirParto() {
        if (animal != null) {
            if (partoSelecionado != null) {
                if (ANIMAL_RN.excluirParto(partoSelecionado)) {
                    animais = ANIMAL_RN.obterTodos();
                    for(Animal a:animais){
                        if(a.getId().equals(animal.getId())){
                            animal.setPartoList(a.getPartoList());
                        }
                    }
                    partoSelecionado = new Parto();
                    UtilBean.criarMensagemDeInformacao("Operação de exclusão de parto realizada com sucesso!");
                    return "visualizarAnimal";
                } else {
                    UtilBean.criarMensagemDeErro("Erro ao excluir!");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Nenhum parto selecionado.");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public String prepararNovoOrdenha(){
        ordenhaSelecionado = new Ordenha();
        return "novoOrdenha";
    }
    
    public String preparaAlterarOrdenha(){
        if(animal != null && ordenhaSelecionado != null) {
            ordenhaSelecionado.setNmOrdenhador(ordenhaSelecionado.getOrdenhador().getNome());
            return "editarOrdenha";
        }else{
            UtilBean.criarMensagemDeAviso("Nenhuma ordenha selecionado.");
            return "";
        }
    } 
    
    public String cadastrarOrdenha() {
        if(animal != null && ordenhaSelecionado != null) {
            ordenhaSelecionado.setAnimal(animal);
            if (ORDENHA_RN.salvar(ordenhaSelecionado)) {
                UtilBean.criarMensagemDeInformacao("Operação de cadastro de ordenha realizada com sucesso!");
                animais = ANIMAL_RN.obterTodos();
                for(Animal a:animais){
                    if(a.getId().equals(animal.getId())){
                        animal.setOrdenhaList(a.getOrdenhaList());
                    }
                }
                ordenhaSelecionado = new Ordenha();
                return "visualizarAnimal";
            } else {
                UtilBean.criarMensagemDeErro("Erro ao cadastrar!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public String excluirOrdenha() {
        if (animal != null) {
            if (ordenhaSelecionado != null) {
                if (ORDENHA_RN.excluir(ordenhaSelecionado)) {
                    animais = ANIMAL_RN.obterTodos();
                    for(Animal a:animais){
                        if(a.getId().equals(animal.getId())){
                        animal.setOrdenhaList(a.getOrdenhaList());
                    }
                    }
                    ordenhaSelecionado = new Ordenha();
                    UtilBean.criarMensagemDeInformacao("Operação de exclusão de ordernha realizada com sucesso!");
                    return "visualizarAnimal";
                } else {
                    UtilBean.criarMensagemDeErro("Erro ao excluir!");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Nenhum ordenha selecionado.");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum animal selecionado.");
        }
        return "";
    }
    
    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getAnimais() {
        fazenda = (Fazenda) UtilBean.getAttribute("fazendaSelecionada");
        animal.setFazenda(fazenda);
        animais = ANIMAL_RN.obter(fazenda);
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public PesoAnimal getPesoSelecionado() {
        return pesoSelecionado;
    }

    public void setPesoSelecionado(PesoAnimal pesoSelecionado) {
        this.pesoSelecionado = pesoSelecionado;
    }

    public Animal getAnimalSelecionado() {
        return animalSelecionado;
    }

    public void setAnimalSelecionado(Animal animalSelecionado) {
        this.animalSelecionado = animalSelecionado;
    }

    public Parto getPartoSelecionado() {
        return partoSelecionado;
    }

    public void setPartoSelecionado(Parto partoSelecionado) {
        this.partoSelecionado = partoSelecionado;
    }

    public Ordenha getOrdenhaSelecionado() {
        return ordenhaSelecionado;
    }

    public void setOrdenhaSelecionado(Ordenha ordenhaSelecionado) {
        this.ordenhaSelecionado = ordenhaSelecionado;
    }

}
