package com.Bio_Controle_Estoque.api.controller;

import com.Bio_Controle_Estoque.domain.DTO.ToolsAssignmentDTO;
import com.Bio_Controle_Estoque.domain.model.ToolsAssignment;
import com.Bio_Controle_Estoque.domain.service.ToolsAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assignments")
public class ToolsAssignmentController {

    @Autowired
    private ToolsAssignmentService toolsAssignmentService;

    // Criar um novo empréstimo
    @PostMapping
    public ResponseEntity<ToolsAssignment> createAssignment(@RequestBody ToolsAssignment assignment) {
        return ResponseEntity.ok(toolsAssignmentService.createAssignment(assignment));
    }

    // Buscar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<ToolsAssignment>> getAllAssignments() {
        return ResponseEntity.ok(toolsAssignmentService.getAllAssignments());
    }

    // Buscar um empréstimo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ToolsAssignmentDTO> getAssignmentById(@PathVariable Long id) {
        Optional<ToolsAssignment> assignment = toolsAssignmentService.getAssignmentById(id);

        return assignment
                .map(a -> ResponseEntity.ok(toolsAssignmentService.convertToDTO(a)))  // Chama o método de conversão da service
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ferramentas/{id}/historico")
    public ResponseEntity<List<ToolsAssignmentDTO>> getAssignmentHistory(@PathVariable Long id) {
        // Chama o serviço para buscar o histórico de empréstimos dessa ferramenta
        List<ToolsAssignmentDTO> assignments = toolsAssignmentService.getAssignmentHistoryByToolId(id);

        // Verifica se encontrou algum empréstimo
        if (assignments.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 caso não encontre resultados
        }

        return ResponseEntity.ok(assignments);  // Retorna 200 OK com a lista de históricos
    }



    // Atualizar um empréstimo
    @PutMapping("/{id}")
    public ResponseEntity<ToolsAssignment> updateAssignment(@PathVariable Long id, @RequestBody ToolsAssignment assignment) {
        if (!toolsAssignmentService.getAssignmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        assignment.setId(id);
        return ResponseEntity.ok(toolsAssignmentService.updateAssignment(assignment));
    }

    // Deletar um empréstimo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        if (!toolsAssignmentService.getAssignmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        toolsAssignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar ferramentas não devolvidas
    @GetMapping("/open")
    public ResponseEntity<List<ToolsAssignment>> getOpenAssignments() {
        return ResponseEntity.ok(toolsAssignmentService.getOpenAssignments());
    }
}
