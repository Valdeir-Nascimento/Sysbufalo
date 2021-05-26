package br.com.ufra.sysbufalo.dao;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Ordenha;
import br.com.ufra.sysbufalo.entidade.Ordenhador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class OrdenhaDAO extends GenericDAO<Ordenha>{
     public List<Ordenha> obter(Animal animal) {
        EntityManager em = getEntityManager();
        String sql = "select o from Ordenha o "
                + " where o.animal = :animal ";
        Query query = em.createQuery(sql);
        List<Ordenha> resposta = null;
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            resposta = (List<Ordenha>) query
                    .setParameter("animal", animal)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Ordenhador buscaOrdenhadorPorNome(String nmOrdenhador) {
        EntityManager em = getEntityManager();
        String sql = "select o from Ordenhador o "
                + " where o.nome = :nome ";
        Query query = em.createQuery(sql);
        Ordenhador resposta = null;
        try {
            resposta = (Ordenhador) query
                    .setParameter("nome", nmOrdenhador)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
