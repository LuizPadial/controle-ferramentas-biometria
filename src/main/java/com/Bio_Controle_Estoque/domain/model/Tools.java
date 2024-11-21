package com.Bio_Controle_Estoque.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "available")  // Mapeando a coluna is_available
    private boolean available;

    @OneToOne(mappedBy = "tools", cascade = CascadeType.ALL, orphanRemoval = true)
    private ToolsAssignment currentAssignment;


}
