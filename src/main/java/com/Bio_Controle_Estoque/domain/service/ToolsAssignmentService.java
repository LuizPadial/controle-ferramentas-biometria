package com.Bio_Controle_Estoque.domain.service;

import com.Bio_Controle_Estoque.domain.model.ToolsAssignment;
import com.Bio_Controle_Estoque.domain.repository.ToolsAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolsAssignmentService {

    @Autowired
    private ToolsAssignmentRepository toolsAssignmentRepository;

    // Criar um novo registro de empréstimo
    public ToolsAssignment createAssignment(ToolsAssignment assignment) {
        return toolsAssignmentRepository.save(assignment);
    }

    // Buscar todos os registros
    public List<ToolsAssignment> getAllAssignments() {
        return toolsAssignmentRepository.findAll();
    }

    // Buscar um registro por ID
    public Optional<ToolsAssignment> getAssignmentById(Long id) {
        return toolsAssignmentRepository.findById(id);
    }

    // Atualizar um registro
    public ToolsAssignment updateAssignment(ToolsAssignment assignment) {
        return toolsAssignmentRepository.save(assignment);
    }

    // Deletar um registro
    public void deleteAssignment(Long id) {
        toolsAssignmentRepository.deleteById(id);
    }

    // Buscar ferramentas emprestadas não devolvidas
    public List<ToolsAssignment> getOpenAssignments() {
        return toolsAssignmentRepository.findByReturnDateIsNull();
    }

}
