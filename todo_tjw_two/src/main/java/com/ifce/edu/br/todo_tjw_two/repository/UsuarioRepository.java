package com.ifce.edu.br.todo_tjw_two.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifce.edu.br.todo_tjw_two.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
