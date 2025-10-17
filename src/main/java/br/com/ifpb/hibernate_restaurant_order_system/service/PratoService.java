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
        Prato prato = new Prato(pratoRequestDTO.getNome(),
                pratoRequestDTO.getDescricao(),
                pratoRequestDTO.getPreco());

        Prato pratoSalvo = pratoDAO.save(prato);
        return converterPrato(pratoSalvo);
    }

    public PratoResponseDTO converterPrato(Prato prato) {
        return new PratoResponseDTO(prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getPreco());
    }

    public List<PratoResponseDTO> findAll() {
        List<Prato> pratos = pratoDAO.findAll();

        return pratos.stream().map(prato -> converterPrato(prato)).collect(Collectors.toList());
    }
}
