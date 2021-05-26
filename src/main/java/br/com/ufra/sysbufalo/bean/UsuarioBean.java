package br.com.ufra.sysbufalo.bean;

import br.com.ufra.sysbufalo.entidade.Usuario;
import br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda;

import br.com.ufra.sysbufalo.rn.UsuarioRN;
import br.com.ufra.sysbufalo.util.UtilBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    public final UsuarioRN USUARIO_RN = new UsuarioRN();
    public Usuario usuario = new Usuario();
    public Usuario usuarioVerificado = null;

    private Usuario usuarioSelecionado = new Usuario();
    private List<Usuario> usuarios = USUARIO_RN.obterTodos();

    private UsuarioHasFazenda usuarioFazenda = new UsuarioHasFazenda();

    public UsuarioHasFazenda getUsuarioFazenda() {
        return usuarioFazenda;
    }

    public void setUsuarioFazenda(UsuarioHasFazenda usuarioFazenda) {
        this.usuarioFazenda = usuarioFazenda;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String preparaNovoUsuario() {
        usuario = new Usuario();
        return "novoUsuario";
    }

    public String preparaAlterarUsuario() {
        usuario = usuarioSelecionado;
        return "editarUsuario";
    }

    public void salvar() {
        if (usuario != null) {
            if (usuarioSelecionado != null) {
                if (USUARIO_RN.salvar(usuario)) {
                    UtilBean.criarMensagemDeInformacao(usuario.getNome() + " salvo com sucesso!");
                    usuarios = USUARIO_RN.obterTodos();

                } else {
                    UtilBean.criarMensagemDeErro("Não foi possível salvar " + usuario.getNome());
                }
            }
        } else {
            UtilBean.criarMensagemDeErro("Nenhum usuário selecionado");
        }

    }

    public void excluir() {
        if (usuario != null) {
            if (USUARIO_RN.excluir(usuario)) {
                UtilBean.criarMensagemDeInformacao("Operação de exclusão de usuario realizada com sucesso!");
                usuarios = USUARIO_RN.obterTodos();
               

            } else {
                UtilBean.criarMensagemDeErro("Erro ao excluir usuário!");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum usuario selecionado.");
        }
      
    }

    public String Login() {
        if (verificaUsuario(usuario) == true) {
            RequestContext context = RequestContext.getCurrentInstance();
            UtilBean.setAttribute("autenticado", true);
            UtilBean.setAttribute("user_id", usuarioVerificado);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/SysbufaloUFRA/pages/index.jsf");
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
                e.printStackTrace();
                context.addCallbackParam("loggedIn", false);
            }
            return "index?faces-redirect=true";
        } else {
            //System.out.println("ssssss");
            RequestContext context = RequestContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha Incorretos", ""));
            context.addCallbackParam("loggedIn", false);
            return "login";
        }
    }

    public String Sair() {
        UtilBean.removeAttribute("autenticado");
        UtilBean.removeAttribute("user_id");
        return "login";
    }

    public boolean verificaUsuario(Usuario u) {
        usuarioVerificado = USUARIO_RN.verificaUsuario(u);
        if (usuarioVerificado == null) {
            return false;
        } else {

            return true;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioVerificado() {
        return usuarioVerificado;
    }

    public void setUsuarioVerificado(Usuario usuarioVerificado) {
        this.usuarioVerificado = usuarioVerificado;
    }

}
