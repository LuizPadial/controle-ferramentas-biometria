package com.Bio_Controle_Estoque.api.controller;

import com.Bio_Controle_Estoque.domain.service.UserService;
import com.Bio_Controle_Estoque.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> listar() {
        return userService.listarUsuarios();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> buscarPorNomeOuMatricula(@RequestParam String query) {
        // Chama o serviço para buscar usuários por nome ou matrícula
        List<User> users = userService.buscarPorNomeOuMatricula(query);

        // Verifica se a lista está vazia
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 caso não encontre resultados
        }
        return ResponseEntity.ok(users); // Retorna 200 OK com os usuários encontrados
    }



    @PostMapping
    public User cadastrar(@RequestBody User user) {
        return userService.salvarUsuario(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> atualizar(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> usuarioAtualizado = userService.atualizarUsuario(userId, user);
        return usuarioAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = userService.deletarUsuario(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
