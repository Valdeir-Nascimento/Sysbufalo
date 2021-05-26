
package br.com.ufra.sysbufalo.dao;

import br.com.ufra.sysbufalo.entidade.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UsuarioDAO extends GenericDAO<Usuario>{
    
     public Usuario obter(String usuario) {
        EntityManager em = getEntityManager();
        String sql = "select u "
                + " from Usuario u "
                + " where u.email = :email or u.celular = :celular ";
        Query query = em.createQuery(sql);
        List<Usuario> usuarios = null;
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            usuarios = (List<Usuario>) query
                    .setParameter("email", usuario)
                    .setParameter("celular", usuario)
                    .getResultList();
            if (usuarios == null || usuarios.isEmpty()) {
                return null;
            } else {
                return usuarios.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario login(String usuario, String senha) {
        EntityManager em = getEntityManager();
        String sql = "select u "
                + " from Usuario u "
                + " where (u.email = :email or u.celular = :celular) and u.senha = :senha ";
        Query query = em.createQuery(sql);
        List<Usuario> usuarios = null;
        try {
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            usuarios = (List<Usuario>) query
                    .setParameter("email", usuario)
                    .setParameter("celular", usuario)
                    .setParameter("senha", senha)
                    .getResultList();
            if (usuarios == null || usuarios.isEmpty()) {
                return null;
            } else {
                return usuarios.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario verificaUsuario(Usuario u) {
        List<Usuario> user = (List<Usuario>) getEntityManager().createQuery("select u FROM Usuario u WHERE (u.email = '" + u.getEmail() + "' or u.celular = '" + u.getEmail()+ "') AND u.senha = '" + u.getSenha() + "'").getResultList();
        if (user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }
    }
}
