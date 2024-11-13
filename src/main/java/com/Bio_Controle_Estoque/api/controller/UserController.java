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

    @GetMapping("/{userId}")
    public ResponseEntity<User> buscar(@PathVariable Long userId) {
        Optional<User> user = userService.buscarUsuarioPorId(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/registration/{registration}")
    public ResponseEntity<User> buscarPorMatricula(@PathVariable String registration) {
        Optional<User> user = userService.buscarUsuarioPorMatricula(registration);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public List<User> buscarPorNome(@PathVariable String name) {
        return userService.buscarUsuarioPorNome(name);
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
