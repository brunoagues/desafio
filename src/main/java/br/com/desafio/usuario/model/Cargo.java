package br.com.desafio.usuario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cargo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3845020813092680954L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ID_CARGO")
	private Long idCargo;
	@Column(name = "NOME_CARGO")
	private String nome;
	
	
	public Cargo() {
		super();
	}
	
	public Cargo(String nome) {
		super();
		this.nome = nome;
	}

	public Cargo(Long idCargo, String nome) {
		super();
		this.idCargo = idCargo;
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getIdCargo() {
		return idCargo;
	}


	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	
	
}
