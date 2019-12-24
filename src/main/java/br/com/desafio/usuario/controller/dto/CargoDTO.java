package br.com.desafio.usuario.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.usuario.model.Cargo;

public class CargoDTO {

	private Long id;
	private String nome;
	
	public CargoDTO() {
		super();
	}
	public CargoDTO(Cargo cargo) {
		super();
		this.id = cargo.getIdCargo();
		this.nome = cargo.getNome();
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public static List<CargoDTO> converter(List<Cargo> listCargos) {
		
		return listCargos.stream().map(CargoDTO::new).collect(Collectors.toList());
	}
	
	
}
