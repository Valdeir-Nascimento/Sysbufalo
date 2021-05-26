
package br.com.ufra.sysbufalo.converter;

import br.com.ufra.sysbufalo.entidade.Fazenda;
import br.com.ufra.sysbufalo.rn.FazendaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter(value = "fazendaConverter")
public class FazendaConverter implements Converter {
    
    FazendaRN RN = new FazendaRN();
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return RN.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }
        
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Fazenda)) {
            return null;
        }
        Fazenda fazenda = (Fazenda) o;
        if (fazenda.getId() == null) {
            return null;
        }
        return String.valueOf(fazenda.getId());
    }
}
