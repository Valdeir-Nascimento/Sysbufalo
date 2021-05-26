/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VALDEIR
 */
@Entity
@Table(name = "fazenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fazenda.findAll", query = "SELECT f FROM Fazenda f")
    , @NamedQuery(name = "Fazenda.findById", query = "SELECT f FROM Fazenda f WHERE f.id = :id")
    , @NamedQuery(name = "Fazenda.findByNome", query = "SELECT f FROM Fazenda f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fazenda.findByCidade", query = "SELECT f FROM Fazenda f WHERE f.cidade = :cidade")
    , @NamedQuery(name = "Fazenda.findByEstado", query = "SELECT f FROM Fazenda f WHERE f.estado = :estado")})
public class Fazenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    @Size(max = 20)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fazenda1")
    private List<UsuarioHasFazenda> usuarioHasFazendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fazenda")
    private List<Animal> animalList;
    @OneToMany(mappedBy = "fazenda")
    private List<Usuario> usuarioList;
    
    @Transient
    private String perfil;

    public Fazenda() {
    }

    public Fazenda(Integer id) {
        this.id = id;
    }

    public Fazenda(Integer id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<UsuarioHasFazenda> getUsuarioHasFazendaList() {
        return usuarioHasFazendaList;
    }

    public void setUsuarioHasFazendaList(List<UsuarioHasFazenda> usuarioHasFazendaList) {
        this.usuarioHasFazendaList = usuarioHasFazendaList;
    }

    @XmlTransient
    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fazenda)) {
            return false;
        }
        Fazenda other = (Fazenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.Fazenda[ id=" + id + " ]";
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
}
