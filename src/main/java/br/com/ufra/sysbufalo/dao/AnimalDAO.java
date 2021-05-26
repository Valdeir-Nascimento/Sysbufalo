
package br.com.ufra.sysbufalo.dao;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Fazenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class AnimalDAO extends GenericDAO<Animal>{
    public List<Animal> obter(Fazenda fazenda) {
        EntityManager em = getEntityManager();
        String sql = "select a "
                + " from Animal a "
                + " where a.fazenda = :fazenda ";
        Query query = em.createQuery(sql);
        List<Animal> resposta = null;
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            resposta = (List<Animal>) query
                    .setParameter("fazenda", fazenda)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
