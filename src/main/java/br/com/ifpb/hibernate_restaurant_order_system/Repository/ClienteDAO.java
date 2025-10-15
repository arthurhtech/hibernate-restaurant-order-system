package br.com.ifpb.hibernate_restaurant_order_system.repository;

import br.com.ifpb.hibernate_restaurant_order_system.Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ClienteDAO {

    // EntityManagerFactory
    private final EntityManagerFactory emf;

    public ClienteDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Cliente save(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(cliente);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao salvar o cliente: " + e.getMessage(), e);
            }
            return cliente;
    }

    public void delete(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
            try {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
        catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }

    public Cliente findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findAll() {
        EntityManager em = emf.createEntityManager();

        String jpql = "select c from Cliente c";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public void update(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try  {
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
