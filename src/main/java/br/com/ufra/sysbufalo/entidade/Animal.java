/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")
    , @NamedQuery(name = "Animal.findById", query = "SELECT a FROM Animal a WHERE a.id = :id")
    , @NamedQuery(name = "Animal.findByBrinco", query = "SELECT a FROM Animal a WHERE a.brinco = :brinco")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "brinco")
    private String brinco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    private List<Parto> partoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal", fetch = FetchType.EAGER)
    private List<PesoAnimal> pesoAnimalList;
    @JoinColumn(name = "fazenda", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fazenda fazenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    private List<Ordenha> ordenhaList;
    
    @Transient
    private String peso;
    @Transient
    private String inicioProducao;

    public Animal() {
    }

    public Animal(Integer id) {
        this.id = id;
    }

    public Animal(Integer id, String brinco) {
        this.id = id;
        this.brinco = brinco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrinco() {
        return brinco;
    }

    public void setBrinco(String brinco) {
        this.brinco = brinco;
    }

    @XmlTransient
    public List<Parto> getPartoList() {
        return partoList;
    }

    public void setPartoList(List<Parto> partoList) {
        this.partoList = partoList;
    }

    @XmlTransient
    public List<PesoAnimal> getPesoAnimalList() {
        return pesoAnimalList;
    }

    public void setPesoAnimalList(List<PesoAnimal> pesoAnimalList) {
        this.pesoAnimalList = pesoAnimalList;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    @XmlTransient
    public List<Ordenha> getOrdenhaList() {
        return ordenhaList;
    }

    public void setOrdenhaList(List<Ordenha> ordenhaList) {
        this.ordenhaList = ordenhaList;
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.Animal[ id=" + id + " ]";
    }
    
    public String getPeso() {
        if (pesoAnimalList != null && pesoAnimalList.size() > 0) {
            Collections.sort(pesoAnimalList);
            PesoAnimal pa = pesoAnimalList.get(0);
            peso = pa.getPeso()+" Kg";
        } else {
            peso = "Nenhum peso cadastrado.";
        }
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getInicioProducao() {
        return inicioProducao;
    }

    public void setInicioProducao(String inicioProducao) {
        this.inicioProducao = inicioProducao;
    }
    
}
