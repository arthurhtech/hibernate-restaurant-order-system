package br.com.ifpb.hibernate_restaurant_order_system.Repository;

import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Cliente cliente) {
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }
        catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }

    public void delete(Cliente cliente) {
        try{
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
        catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }

    public Cliente find(Long id) {
        return em.find(Cliente.class, id);
    }

    public void update(Cliente cliente) {
        try{
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        }
        catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }
}
