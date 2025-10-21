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

    // Construtor que inicializa o DAO
    public PratoService(EntityManagerFactory emf) {
        this.pratoDAO = new PratoDAO(emf);
    }

    // Converte DTO para Entidade e salva
    public PratoResponseDTO save(PratoRequestDTO pratoRequestDTO) {

        Prato prato = new Prato(
            pratoRequestDTO.getNome(),
            pratoRequestDTO.getDescricao(),
            pratoRequestDTO.getPreco()
        );

        Prato pratoSalvo = pratoDAO.save(prato);
        return converterParaDTO(pratoSalvo);
    }

    // Busca todos os pratos e converte para DTO
    public List<PratoResponseDTO> findAll() {
        List<Prato> pratos = pratoDAO.findAll();

        return pratos.stream()
                     .map(this::converterParaDTO)
                     .collect(Collectors.toList());
    }

    // Método utilitário para converter Entidade Prato para DTO
    private PratoResponseDTO converterParaDTO(Prato prato) {
        if (prato == null) return null;
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getPreco()
        );
    }
    // Busca um prato por ID e converte para DTO
    public PratoResponseDTO findById(long id) {
        Prato prato = pratoDAO.findById(id);

        if (prato != null) {
            return converterParaDTO(prato);
        }
        return null;
    }

    // Atualiza um prato existente a partir de um DTO
    public PratoResponseDTO updateById(long id, PratoRequestDTO pratoRequestDTO) {

        Prato prato = pratoDAO.findById(id);

        if (prato == null) {
            return null;
        }

        prato.setNome(pratoRequestDTO.getNome());
        prato.setDescricao(pratoRequestDTO.getDescricao());
        prato.setPreco(pratoRequestDTO.getPreco());

        Prato pratoAtualizado = pratoDAO.update(prato);

        return converterParaDTO(pratoAtualizado);
    }
    // Deleta um prato por ID
    public boolean deleteById(long id) {

        Prato prato = pratoDAO.findById(id);

        if (prato == null) {
            return false;
        }
        pratoDAO.delete(id);
        return true;
    }
}