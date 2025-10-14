package br.com.ifpb.hibernate_restaurant_order_system.repository;

import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Cliente save(Cliente cliente) {
        em.persist(cliente);
        return cliente;
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
