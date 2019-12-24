package br.com.desafio.usuario.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.desafio.usuario.Enums.StatusEnum;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6199282851769966218L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name= "NOME_USUARIO",nullable=false)
	private String nome;
	
	@Column(name= "CPF",nullable=false)
	private String cpf;
	
	@Column(name= "SEXO",nullable=false)
	private String sexo;
	
	@Column(name= "DATA_NASCIMENTO" ,nullable=false) 
	private LocalDate dataNasc;
	
	@ManyToOne
	@JoinColumn(name="ID_CARGO",nullable=false)
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name="ID_PERFIL",nullable=false)
	private Perfil perfil; 
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status ;
	
	public Usuario() {
		super();
	}

	
	
	public Usuario(String nome, String cpf, String sexo, LocalDate dataNasc, Cargo cargo, Perfil perfil) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.cargo = cargo;
		this.perfil = perfil;
	}



	public Usuario(Long idUsuario, String nome, String cpf, String sexo, LocalDate dataNasc, Cargo cargo, Perfil perfil,
			StatusEnum status, String dataNascFormatada) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.cargo = cargo;
		this.perfil = perfil;
		this.status = status;
	}


	public Usuario(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}


	public Usuario(String nome, String cpf, Cargo cargo, Perfil perfil) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.perfil = perfil;
	}




	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
