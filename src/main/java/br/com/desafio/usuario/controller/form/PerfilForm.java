package br.com.desafio.usuario.controller.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class PerfilForm {

	@NotNull @NotEmpty
	private String nome;
	
	

	public PerfilForm() {
		super();
	}

	public PerfilForm(@NotEmpty String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
