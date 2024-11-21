package com.Bio_Controle_Estoque.api.controller;

import com.Bio_Controle_Estoque.domain.model.ToolsAssignment;
import com.Bio_Controle_Estoque.domain.service.ToolsAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ToolsAssignment> getAssignmentById(@PathVariable Long id) {
        return toolsAssignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
