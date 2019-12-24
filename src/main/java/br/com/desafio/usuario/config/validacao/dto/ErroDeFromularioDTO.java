package br.com.desafio.usuario.config.validacao.dto;

public class ErroDeFromularioDTO {

	private String campo;
	private String erro;
	
	public ErroDeFromularioDTO() {
		super();
	}
	public ErroDeFromularioDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}
	
	
}
