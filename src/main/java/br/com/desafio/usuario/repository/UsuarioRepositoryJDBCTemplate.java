package br.com.desafio.usuario.repository;

import java.util.List;

import br.com.desafio.usuario.model.Usuario;

public interface UsuarioRepositoryJDBCTemplate {

	public List<Usuario> findByFilter(Usuario usuario) throws Exception;

	public  List<Usuario> listaFemininoMaioresIdade() throws Exception;

	public List<Usuario> buscaCpfIniciadoZero() throws Exception;

	public List<Usuario> findByNomeUsuarioAndCpf(String nomeUsuario, String cpf) throws Exception;

	

}
