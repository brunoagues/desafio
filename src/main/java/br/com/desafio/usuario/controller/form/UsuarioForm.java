package br.com.desafio.usuario.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

public class UsuarioForm {
	
	@NotNull @NotEmpty
	private String nomeUsuario;
	@CPF
	private String cpf;
	@NotNull @NotEmpty
	private String sexo;
	@NotNull @NotEmpty @DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dataNascimento;
	@NotNull 
	private Long idCargo;
	@NotNull 
	private Long idPerfil;
	
	
	
	public UsuarioForm() {
		super();
	}
	public UsuarioForm(@NotEmpty String nomeUsuario, @CPF String cpf, @NotEmpty String sexo,
			@NotEmpty String dataNascimento, Long idCargo, Long idPerfil) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idCargo = idCargo;
		this.idPerfil = idPerfil;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

}
