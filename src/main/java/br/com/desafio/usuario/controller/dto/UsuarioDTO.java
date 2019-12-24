package br.com.desafio.usuario.controller.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.usuario.Enums.StatusEnum;
import br.com.desafio.usuario.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String sexo;
	private String cargo;
	private String perfil;
	private StatusEnum status;
	private  String dataNascFormatada;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getIdUsuario();
		this.nome =usuario.getNome();
		this.cpf = usuario.getCpf();
		this.sexo = usuario.getSexo();
		this.cargo = usuario.getCargo().getNome();
		this.perfil = usuario.getPerfil().getNome();
		this.status =  usuario.getStatus();
		this.dataNascFormatada = formatarData(usuario.getDataNasc());
	}
	
	
	public static List<UsuarioDTO> converter(List<Usuario> listUsuario) {

		return listUsuario.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
	private String formatarData(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return localDate.format(formatter);
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getSexo() {
		return sexo;
	}



	public StatusEnum getStatus() {
		return status;
	}

	public String getDataNascFormatada() {
		return dataNascFormatada;
	}

	public String getCargo() {
		return cargo;
	}

	public String getPerfil() {
		return perfil;
	}

	
	
	
	
}
