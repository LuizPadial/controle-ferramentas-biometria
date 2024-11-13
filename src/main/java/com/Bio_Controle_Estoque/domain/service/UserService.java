package com.Bio_Controle_Estoque.domain.service;

import com.Bio_Controle_Estoque.domain.model.User;
import com.Bio_Controle_Estoque.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> buscarUsuarioPorId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> buscarUsuarioPorMatricula(String registration) {
        return userRepository.findByRegistration(registration);
    }

    public List<User> buscarUsuarioPorNome(String name) {
        return userRepository.findByNameContaining(name);
    }

    public User salvarUsuario(User user) {
        return userRepository.save(user);
    }

    public Optional<User> atualizarUsuario(Long id, User userAtualizado) {
        return userRepository.findById(id).map(user -> {
            user.setName(userAtualizado.getName());
            user.setRegistration(userAtualizado.getRegistration());
            user.setUsername(userAtualizado.getUsername());
            user.setPassword(userAtualizado.getPassword());
            user.setBiometricData(userAtualizado.getBiometricData());
            user.setManager(userAtualizado.isManager());
            return userRepository.save(user);
        });
    }

    public boolean deletarUsuario(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}