package com.ifce.edu.br.todo_tjw_two.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifce.edu.br.todo_tjw_two.model.Atividade;
import com.ifce.edu.br.todo_tjw_two.model.Tarefa;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	@Query(value = "SELECT * FROM tarefa WHERE atividade_id_id = ?", nativeQuery = true)
	List<Tarefa> buscaPorAtividade_idComParam(@Param("atividade_id") Long atividade_id);
	
	@Query(value = "SELECT * FROM atividade WHERE id = ?", nativeQuery = true)
	Optional<Atividade> buscaPorUnicaAtividade_idComParam(@Param("id") Long id);
	
	@Query(value = "SELECT usuario_id_id FROM atividade WHERE id = ?", nativeQuery = true)
	Long buscaPorUsuarioIdComParam(@Param("id") Long id);
}