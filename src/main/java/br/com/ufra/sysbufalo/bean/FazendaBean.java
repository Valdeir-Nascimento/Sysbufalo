
package br.com.ufra.sysbufalo.bean;

import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Usuario;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazendaPK;
import br.com.ufra.sysbufalo.rn.AnimalRN;
import br.com.ufra.sysbufalo.rn.FazendaRN;
import br.com.ufra.sysbufalo.util.UtilBean;
import br.com.ufra.sysbufalo.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class FazendaBean implements Serializable{
    
   
    private Fazenda fazenda = new Fazenda();
    private Fazenda fazendaSelecionada;
    private final FazendaRN FAZENDA_RN = new FazendaRN();
    private final AnimalRN ANIMAL_RN = new AnimalRN();
    private Boolean habilitarNovaFazenda = false;
    
    private List<Fazenda> fazendas = FAZENDA_RN.obterTodos(); // Obter Todos
    
    public FazendaBean() {
    }
    
    public String preparaNovoFazenda(){
        fazenda = new Fazenda();
        return "novoFazenda";
    }
    
    public String preparaVisualizarFazenda() {
        fazenda = fazendaSelecionada;
        UtilBean.setAttribute("fazendaSelecionada", fazenda);  
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SysbufaloUFRA/pages/fazenda/visualizarFazenda.jsf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "visualizarFazenda";
    }
    
    public String preparaAlterarFazenda() {
        fazenda = fazendaSelecionada;
        return "editarFazenda";
    }

    public List<Fazenda> getFazendas() {
        habilitarNovaFazenda = true;
        Usuario u = (Usuario) UtilBean.getAttribute("user_id");  
        fazendas = FAZENDA_RN.obterTodosUsuario(u);
        /*
        for (Fazenda f : fazendas) {
            if (f.getPerfil() != null && f.getPerfil().trim().toUpperCase().equals("ADM")) {
                habilitarNovaFazenda = true;
            }
        }
        */
        return fazendas;
    }

    public void setFazendas(List<Fazenda> fazendas) {
        this.fazendas = fazendas;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public void novo() {
        fazenda = new Fazenda();
    }

    public String salvar() {
        if (fazenda != null) {
            if (FAZENDA_RN.salvar(fazenda)) {
                
                Usuario u = (Usuario) UtilBean.getAttribute("user_id");  
                UsuarioHasFazendaPK ufpK = new UsuarioHasFazendaPK(u.getId(), fazenda.getId());
                UsuarioHasFazenda uf = new UsuarioHasFazenda();
                uf.setUsuarioHasFazendaPK(ufpK);
                uf.setUsuario1(u);
                uf.setFazenda1(fazenda);
                uf.setPerfil("ADM");
                FAZENDA_RN.salvarUsuarioFazenda(uf);
                
                UtilBean.criarMensagemDeInformacao(fazenda.getNome() + " salva com sucesso!");
                fazendas = FAZENDA_RN.obterTodosUsuario(u);
                UtilBean.setAttribute("fazendaSelecionada", fazenda);  
                return "visualizarFazenda";
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível salvar " + fazenda.getNome());
            }
        } else {
            UtilBean.criarMensagemDeErro("Nenhuma fazenda selecionada");
        }
        return "";
    }

    public String excluir() {
        if (fazenda != null) {
            if (FAZENDA_RN.excluir(fazenda)) {
                UtilBean.criarMensagemDeInformacao(fazenda.getNome() + ": excluída com sucesso!");
                Usuario u = (Usuario) UtilBean.getAttribute("user_id");  
                fazendas = FAZENDA_RN.obterTodosUsuario(u);
                return "listarFazenda";
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível excluir " + fazenda.getNome());
            }
        } else {
            UtilBean.criarMensagemDeErro("Nenhuma fazenda selecionada");
        }
        return "";
    }
    
    public void gerarRelatorio() {
        try {
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("AnimaisPorFazenda", parametros, ANIMAL_RN.obter(fazenda));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Fazenda getFazendaSelecionada() {
        return fazendaSelecionada;
    }

    public void setFazendaSelecionada(Fazenda fazendaSelecionada) {
        this.fazendaSelecionada = fazendaSelecionada;
    }

    public Boolean getHabilitarNovaFazenda() {
        return habilitarNovaFazenda;
    }

    public void setHabilitarNovaFazenda(Boolean habilitarNovaFazenda) {
        this.habilitarNovaFazenda = habilitarNovaFazenda;
    }
    
}
