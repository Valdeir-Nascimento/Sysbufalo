/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VALDEIR
 */
@Entity
@Table(name = "ordenha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenha.findAll", query = "SELECT o FROM Ordenha o")
    , @NamedQuery(name = "Ordenha.findById", query = "SELECT o FROM Ordenha o WHERE o.id = :id")
    , @NamedQuery(name = "Ordenha.findByData", query = "SELECT o FROM Ordenha o WHERE o.data = :data")
    , @NamedQuery(name = "Ordenha.findByManual", query = "SELECT o FROM Ordenha o WHERE o.manual = :manual")
    , @NamedQuery(name = "Ordenha.findByQuantidadeProduzida", query = "SELECT o FROM Ordenha o WHERE o.quantidadeProduzida = :quantidadeProduzida")})
public class Ordenha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "manual")
    private boolean manual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_produzida")
    private float quantidadeProduzida;
    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;
    @JoinColumn(name = "ordenhador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ordenhador ordenhador;
    
    @Transient
    private String nmOrdenhador;

    public Ordenha() {
    }

    public Ordenha(Integer id) {
        this.id = id;
    }

    public Ordenha(Integer id, Date data, boolean manual, float quantidadeProduzida) {
        this.id = id;
        this.data = data;
        this.manual = manual;
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public float getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(float quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Ordenhador getOrdenhador() {
        return ordenhador;
    }

    public void setOrdenhador(Ordenhador ordenhador) {
        this.ordenhador = ordenhador;
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
        if (!(object instanceof Ordenha)) {
            return false;
        }
        Ordenha other = (Ordenha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.Ordenha[ id=" + id + " ]";
    }

    public String getNmOrdenhador() {
        return nmOrdenhador;
    }

    public void setNmOrdenhador(String nmOrdenhador) {
        this.nmOrdenhador = nmOrdenhador;
    }
    
}
