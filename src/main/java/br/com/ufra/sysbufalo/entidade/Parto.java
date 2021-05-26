/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VALDEIR
 */
@Entity
@Table(name = "parto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parto.findAll", query = "SELECT p FROM Parto p")
    , @NamedQuery(name = "Parto.findById", query = "SELECT p FROM Parto p WHERE p.id = :id")
    , @NamedQuery(name = "Parto.findByOrdem", query = "SELECT p FROM Parto p WHERE p.ordem = :ordem")
    , @NamedQuery(name = "Parto.findByDataParto", query = "SELECT p FROM Parto p WHERE p.dataParto = :dataParto")})
public class Parto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordem")
    private int ordem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_parto")
    @Temporal(TemporalType.DATE)
    private Date dataParto;
    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;
    @Column(name = "inicioProducao")
    @Temporal(TemporalType.DATE)
    private Date inicioProducao;
    @Column(name = "fimProducao")
    @Temporal(TemporalType.DATE)
    private Date fimProducao;
    
    public Parto() {
    }

    public Parto(Integer id) {
        this.id = id;
    }

    public Parto(Integer id, int ordem, Date dataParto) {
        this.id = id;
        this.ordem = ordem;
        this.dataParto = dataParto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Date getDataParto() {
        return dataParto;
    }

    public void setDataParto(Date dataParto) {
        this.dataParto = dataParto;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
        if (!(object instanceof Parto)) {
            return false;
        }
        Parto other = (Parto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ufra.sysbufalo.entidade.Parto[ id=" + id + " ]";
    }

    public Date getInicioProducao() {
        return inicioProducao;
    }

    public void setInicioProducao(Date inicioProducao) {
        this.inicioProducao = inicioProducao;
    }

    public Date getFimProducao() {
        return fimProducao;
    }

    public void setFimProducao(Date fimProducao) {
        this.fimProducao = fimProducao;
    }
    
}
