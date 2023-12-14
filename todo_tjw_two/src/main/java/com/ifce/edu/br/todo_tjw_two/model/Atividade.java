package com.ifce.edu.br.todo_tjw_two.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "A atividade deve ter no mínimo 3 carateres")
	private String descricao_atividade;
	
	@ManyToOne
	private Usuario usuario_id; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao_atividade() {
		return descricao_atividade;
	}
	public void setDescricao_atividade(String descricao_atividade) {
		this.descricao_atividade = descricao_atividade;
	}
	public Usuario getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}
	
}
