package com.ifce.edu.br.todo_tjw_two.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "A atividade deve ter no mínimo 3 carateres")
	private String descricao_tarefa;
	
	@ManyToOne
	private Atividade atividade_id; 
	
	private boolean situacao_tarefa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao_tarefa() {
		return descricao_tarefa;
	}
	public void setDescricao_tarefa(String descricao_tarefa) {
		this.descricao_tarefa = descricao_tarefa;
	}
	public boolean isSituacao_tarefa() {
		return situacao_tarefa;
	}
	public void setSituacao_tarefa(boolean situacao_tarefa) {
		this.situacao_tarefa = situacao_tarefa;
	}
	public Atividade getAtividade_id() {
		return atividade_id;
	}
	public void setAtividade_id(Atividade atividade_id) {
		this.atividade_id = atividade_id;
	}
	
	
	
	
}

