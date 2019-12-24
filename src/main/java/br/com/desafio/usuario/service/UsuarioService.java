package br.com.desafio.usuario.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.desafio.usuario.controller.form.UsuarioForm;
import br.com.desafio.usuario.model.Usuario;

@Service
public interface UsuarioService {

	public List<Usuario> findAll() throws Exception;
	
	public List<Usuario> findByFilter(Usuario usuario) throws Exception;
	
	public Usuario findByIdCurso(Long id) throws Exception;

	public Usuario converterUsuarioFormParaEntity(UsuarioForm form) throws Exception;

	public void save(Usuario usuario) throws Exception;

	public Usuario atualizarUsuario(Long id, @Valid UsuarioForm form) throws Exception;

	public void deleteById(Long id) throws Exception;

	public Optional<Usuario> findById(Long id) throws Exception;

	public Usuario inativar(Long id) throws Exception;

	public List<Usuario> listaFemininoMaioresIdade() throws Exception;

	public List<Usuario> buscaCpfIniciadoZero() throws Exception;

	public List<Usuario> findByNomeUsuarioAndCpf(String nomeUsuario, String cpf)throws Exception;

	
	
	

}
