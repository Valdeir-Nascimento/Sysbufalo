/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author VALDEIR
 */
@Embeddable
public class UsuarioHasFazendaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fazenda")
    private int fazenda;

    public UsuarioHasFazendaPK() {
    }

    public UsuarioHasFazendaPK(int usuario, int fazenda) {
        this.usuario = usuario;
        this.fazenda = fazenda;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getFazenda() {
        return fazenda;
    }

    public void setFazenda(int fazenda) {
        this.fazenda = fazenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuario;
        hash += (int) fazenda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasFazendaPK)) {
            return false;
        }
        UsuarioHasFazendaPK other = (UsuarioHasFazendaPK) object;
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.fazenda != other.fazenda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.UsuarioHasFazendaPK[ usuario=" + usuario + ", fazenda=" + fazenda + " ]";
    }
    
}
