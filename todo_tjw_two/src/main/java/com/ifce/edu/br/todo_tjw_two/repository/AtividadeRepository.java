package com.ifce.edu.br.todo_tjw_two.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifce.edu.br.todo_tjw_two.model.Atividade;
import com.ifce.edu.br.todo_tjw_two.model.Usuario;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
	@Query(value = "SELECT * FROM usuario WHERE id = ?", nativeQuery = true)
	Optional<Usuario> buscaPorUsuario_idComParam(@Param("id") Long id);
}
