package br.com.desafio.usuario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.desafio.usuario.controller.dto.PerfilDTO;
import br.com.desafio.usuario.controller.form.PerfilForm;

@Entity
public class Perfil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -647485783271213469L;
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERFIL")
	private Long idPerfil;
	@Column(name = "NOME_PERFIL", nullable = false)
	private String nome;

	
	public Perfil() {
		super();
	}

	public Perfil(Long idPerfil, String nome) {
		super();
		this.idPerfil = idPerfil;
		this.nome = nome;
	}

	public Perfil(PerfilForm form) {
		this.nome = form.getNome();
		
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}



	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	
}
