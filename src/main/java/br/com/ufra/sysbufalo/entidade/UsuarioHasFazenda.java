/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VALDEIR
 */
@Entity
@Table(name = "usuario_has_fazenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioHasFazenda.findAll", query = "SELECT u FROM UsuarioHasFazenda u")
    , @NamedQuery(name = "UsuarioHasFazenda.findByUsuario", query = "SELECT u FROM UsuarioHasFazenda u WHERE u.usuarioHasFazendaPK.usuario = :usuario")
    , @NamedQuery(name = "UsuarioHasFazenda.findByFazenda", query = "SELECT u FROM UsuarioHasFazenda u WHERE u.usuarioHasFazendaPK.fazenda = :fazenda")
    , @NamedQuery(name = "UsuarioHasFazenda.findByPerfil", query = "SELECT u FROM UsuarioHasFazenda u WHERE u.perfil = :perfil")})
public class UsuarioHasFazenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioHasFazendaPK usuarioHasFazendaPK;
    @Size(max = 45)
    @Column(name = "perfil")
    private String perfil;
    @JoinColumn(name = "fazenda", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fazenda fazenda1;
    @JoinColumn(name = "usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public UsuarioHasFazenda() {
    }

    public UsuarioHasFazenda(UsuarioHasFazendaPK usuarioHasFazendaPK) {
        this.usuarioHasFazendaPK = usuarioHasFazendaPK;
    }

    public UsuarioHasFazenda(int usuario, int fazenda) {
        this.usuarioHasFazendaPK = new UsuarioHasFazendaPK(usuario, fazenda);
    }

    public UsuarioHasFazendaPK getUsuarioHasFazendaPK() {
        return usuarioHasFazendaPK;
    }

    public void setUsuarioHasFazendaPK(UsuarioHasFazendaPK usuarioHasFazendaPK) {
        this.usuarioHasFazendaPK = usuarioHasFazendaPK;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Fazenda getFazenda1() {
        return fazenda1;
    }

    public void setFazenda1(Fazenda fazenda1) {
        this.fazenda1 = fazenda1;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioHasFazendaPK != null ? usuarioHasFazendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasFazenda)) {
            return false;
        }
        UsuarioHasFazenda other = (UsuarioHasFazenda) object;
        if ((this.usuarioHasFazendaPK == null && other.usuarioHasFazendaPK != null) || (this.usuarioHasFazendaPK != null && !this.usuarioHasFazendaPK.equals(other.usuarioHasFazendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.UsuarioHasFazenda[ usuarioHasFazendaPK=" + usuarioHasFazendaPK + " ]";
    }
    
}
