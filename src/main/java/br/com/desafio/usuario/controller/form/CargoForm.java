package br.com.desafio.usuario.controller.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class CargoForm {

	@NotNull @NotEmpty
	private String nomeCargo;
	

	public CargoForm() {
		super();
	}

	public CargoForm(String nomeCargo) {
		super();
		this.nomeCargo = nomeCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}
	
	
}
