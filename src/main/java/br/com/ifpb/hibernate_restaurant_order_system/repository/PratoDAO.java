package br.com.ifpb.hibernate_restaurant_order_system.repository;

import br.com.ifpb.hibernate_restaurant_order_system.model.Prato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PratoDAO {
    private final EntityManagerFactory emf;

    public PratoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public Prato save (Prato prato){
        try(EntityManager em = emf.createEntityManager()){
            try {
                em.getTransaction().begin();
                em.persist(prato);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao salvar o prato: " + e.getMessage(), e);
            }
            return prato;
        }
    }

    public Prato findById(long id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Prato.class, id);
        }
    }

    
    public List<Prato> findAll() {
        try(EntityManager em = emf.createEntityManager()){
            String jpql = "select p from Prato p";
            return em.createQuery(jpql, Prato.class).getResultList();
        }
    }
}