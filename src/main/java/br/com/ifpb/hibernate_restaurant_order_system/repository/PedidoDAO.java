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
            // Usamos JOIN FETCH para carregar o cliente e a lista de pratos
            // na mesma consulta. Usamos LEFT JOIN FETCH para o caso de um pedido
            // não ter pratos.
            String jpql = "SELECT p FROM Pedido p " +
                    "JOIN FETCH p.cliente " +
                    "LEFT JOIN FETCH p.pratos " +
                    "WHERE p.id = :id";

            // Usamos getResultList() pois find() não suporta fetch joins
            List<Pedido> result = em.createQuery(jpql, Pedido.class)
                    .setParameter("id", id)
                    .getResultList();

            // Retorna o primeiro resultado, ou null se a lista estiver vazia
            return result.isEmpty() ? null : result.getFirst();
        }
    }

    // Método para buscar todos os Pedidos no banco de dados
    public List<Pedido> findAll() {
        try(EntityManager em = emf.createEntityManager()){
            // Usamos DISTINCT para garantir que cada Pedido apareça apenas uma vez,
            // mesmo que ele tenha múltiplos pratos (o que causaria duplicatas no JOIN).
            String jpql = "SELECT DISTINCT p FROM Pedido p " +
                    "JOIN FETCH p.cliente " +
                    "LEFT JOIN FETCH p.pratos";

            return em.createQuery(jpql, Pedido.class).getResultList();
        }
    }
}
