package com.Bio_Controle_Estoque.domain.repository;

import com.Bio_Controle_Estoque.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Busca exata:
    List<User> findByName(String name);

    //Busca usando palavra chave:
    List<User> findByNameContaining(String name);

}
