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

    public Prato update(Prato prato) {
        try(EntityManager em = emf.createEntityManager()){
            try  {
                em.getTransaction().begin();
                // O método 'merge' é usado para atualizar uma entidade
                Prato pratoAtualizado = em.merge(prato);
                em.getTransaction().commit();
                return pratoAtualizado;
            }
            catch(Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao atualizar o prato: " + e.getMessage(), e);
            }
        }
    }

    public void delete(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            try {
                em.getTransaction().begin();
                // Primeiro, encontramos o prato pelo ID
                Prato prato = em.find(Prato.class, id);

                if(prato != null){
                    // Se existir, usamos 'remove' para deletar
                    em.remove(prato);
                }
                em.getTransaction().commit();
            }
            catch(Exception e){
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao deletar o prato: " + e.getMessage(), e);
            }
        }
    }
}