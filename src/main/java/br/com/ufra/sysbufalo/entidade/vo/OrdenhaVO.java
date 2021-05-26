
package br.com.ufra.sysbufalo.entidade.vo;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.entidade.Ordenhador;
import java.util.Date;


public class OrdenhaVO {
    
    public Fazenda fazenda;
    public Animal animal;
    public Ordenhador ordenhador;
    public Boolean manual;
    public Date data;
    public Float quantidadeProduzida;

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

    public Ordenhador getOrdenhador() {
        return ordenhador;
    }

    public void setOrdenhador(Ordenhador ordenhador) {
        this.ordenhador = ordenhador;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Float getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(Float quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }
    
}
