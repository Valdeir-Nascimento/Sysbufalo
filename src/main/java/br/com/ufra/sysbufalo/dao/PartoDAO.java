/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.sysbufalo.dao;

import br.com.ufra.sysbufalo.entidade.Animal;
import br.com.ufra.sysbufalo.entidade.Parto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author VALDEIR
 */
public class PartoDAO extends GenericDAO<Parto>{
    
    public List<Parto> obterListaPartoPorAnimal(Animal a) {
        EntityManager em = getEntityManager();
        String sql = "select a "
                + " from Parto a "
                + " where a.animal = :animal "
                + " order by a.dataParto ";
        Query query = em.createQuery(sql);
        List<Parto> resposta = new ArrayList<>();
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            resposta = (List<Parto>) query
                    .setParameter("animal", a)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public int obterOrdemParto(Animal a) {
        EntityManager em = getEntityManager();
        String sql = "select max(a.ordem) "
                + " from Parto a "
                + " where a.animal = :animal ";
        Query query = em.createQuery(sql);
        int ordem = 0;
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            ordem = (int) query
                    .setParameter("animal", a)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return ordem;
    }
    
}
