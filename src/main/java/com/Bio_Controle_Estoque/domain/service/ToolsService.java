package com.Bio_Controle_Estoque.domain.service;

import com.Bio_Controle_Estoque.domain.model.Tools;
import com.Bio_Controle_Estoque.domain.model.User;
import com.Bio_Controle_Estoque.domain.repository.ToolsRepository;
import com.Bio_Controle_Estoque.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository toolsRepository;

    public List<Tools> listarFerramentas() {
        return toolsRepository.findAll();
    }

    public Optional<Tools> buscarFerramentaPorId(Long id) {
        return toolsRepository.findById(id);
    }

    public List<Tools> buscarPorNomeOuCodigo(String query) {
        return toolsRepository.findByNameContaining(query);
    }


    public Tools salvarFerramenta(Tools tools) {
        return toolsRepository.save(tools);
    }

    public Optional<Tools> atualizarFerramenta(Long id, Tools toolsAtualizado) {
        return toolsRepository.findById(id).map(tools -> {
            tools.setName(toolsAtualizado.getName());
            tools.setDescription(toolsAtualizado.getDescription());
            return toolsRepository.save(tools);
        });
    }

    public boolean deletarFerramenta(Long id) {
        return toolsRepository.findById(id).map(tools -> {
            toolsRepository.delete(tools);
            return true;
        }).orElse(false);
    }
}