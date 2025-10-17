package br.com.ifpb.hibernate_restaurant_order_system.repository;
import br.com.ifpb.hibernate_restaurant_order_system.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class PedidoDAO {

    private final EntityManagerFactory emf;

    // Construtor para injeção de dependência do EntityManagerFactory

    public PedidoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Método para salvar um novo Pedido no banco de dados

    public Pedido save(Pedido pedido) {
        try(EntityManager em = emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                em.persist(pedido);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao salvar o pedido: " + e.getMessage(), e);
            }
            return pedido;
        }
    }


    // Método para buscar um Pedido pelo seu ID
    public Pedido findById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Pedido.class, id);
        }
    }

    // Método para buscar todos os Pedidos no banco de dados
    public List<Pedido> findAll() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        }
    }


    // Método para atualizar um Pedido existente
    public Pedido update(Pedido pedido) {
        try(EntityManager em = emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                Pedido updatedPedido = em.merge(pedido);
                em.getTransaction().commit();
                return updatedPedido;
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new RuntimeException("Erro ao atualizar o pedido: " + e.getMessage(), e);
            }
        }
    }

    // Método para deletar um Pedido pelo seu ID
    public void delet(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            try {
                em.getTransaction().begin();
                Pedido pedido = em.find(Pedido.class, id);

                if(pedido != null){
                    em.remove(pedido);
                }
                em.getTransaction().commit();
            }
            catch(Exception e){
                em.getTransaction().rollback();

                throw new RuntimeException("Erro ao deletar o pedido: " + e.getMessage(), e);
            }
        }
    }

}
