package br.com.ifpb.hibernate_restaurant_order_system.service;

import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoRequestDTO;
import br.com.ifpb.hibernate_restaurant_order_system.dto.prato.PratoResponseDTO;
import br.com.ifpb.hibernate_restaurant_order_system.model.Prato;
import br.com.ifpb.hibernate_restaurant_order_system.repository.PratoDAO;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class PratoService {

    private final PratoDAO pratoDAO;

    public PratoService(EntityManagerFactory emf) {
        this.pratoDAO = new PratoDAO(emf);
    }

    public PratoResponseDTO save(PratoRequestDTO pratoRequestDTO) {
        // A entidade espera um BigDecimal
        Prato prato = new Prato(
            pratoRequestDTO.getNome(),
            pratoRequestDTO.getDescricao(),
            pratoRequestDTO.getPreco()
        );

        Prato pratoSalvo = pratoDAO.save(prato);
        // Chama o método conversor privado
        return converterParaDTO(pratoSalvo);
    }

    public List<PratoResponseDTO> findAll() {
        List<Prato> pratos = pratoDAO.findAll();
        // Usa o método conversor para mapear a lista
        return pratos.stream()
                     .map(this::converterParaDTO)
                     .collect(Collectors.toList());
    }

    // Método conversor privado
    private PratoResponseDTO converterParaDTO(Prato prato) {
        if (prato == null) return null;
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getPreco()
        );
    }
}