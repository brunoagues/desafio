package br.com.desafio.usuario.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.usuario.model.Perfil;

public class PerfilDTO {

	private Long id;
	private String nome;

	public PerfilDTO() {
		super();
	}
	
	public PerfilDTO(Perfil perfil) {
		super();
		this.id = perfil.getIdPerfil();
		this.nome = perfil.getNome();
	}
	
	public static List<PerfilDTO> converter(List<Perfil> listPerfil) {
		return listPerfil.stream().map(PerfilDTO::new).collect(Collectors.toList());
	}
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	
}
