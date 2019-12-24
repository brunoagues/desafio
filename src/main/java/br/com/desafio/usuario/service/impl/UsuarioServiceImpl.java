package br.com.desafio.usuario.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.usuario.Enums.StatusEnum;
import br.com.desafio.usuario.controller.form.UsuarioForm;
import br.com.desafio.usuario.model.Cargo;
import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.model.Usuario;
import br.com.desafio.usuario.repository.CargoRepository;
import br.com.desafio.usuario.repository.PerfilRepository;
import br.com.desafio.usuario.repository.UsuarioRepository;
import br.com.desafio.usuario.repository.UsuarioRepositoryJDBCTemplate;
import br.com.desafio.usuario.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepositoryJDBCTemplate usuarioJDBC;
	
	@Override
	public List<Usuario> findAll() throws Exception{
		return usuarioRepository.findAll();
	}

	
	@Override
	public List<Usuario> findByFilter(Usuario usuario) throws Exception{
		return usuarioJDBC.findByFilter(usuario);
	}

	public Usuario findByIdCurso(Long id) throws Exception{
		return usuarioRepository.findByIdUsuario(id);
	}

	public Usuario converterUsuarioFormParaEntity(UsuarioForm form) throws Exception{
	   
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataNasc = LocalDate.parse(form.getDataNascimento(), formatter);
		
		Cargo cargo = cargoRepository.findByIdCargo(form.getIdCargo());
		Perfil perfil = perfilRepository.findByIdPerfil(form.getIdPerfil());
		
		return new Usuario(form.getNomeUsuario(), form.getCpf(), form.getSexo(), dataNasc, cargo, perfil);
	}


	
	@Override
	public void save(Usuario usuario) throws Exception{
		usuario.setStatus(StatusEnum.ATIVO);
		usuarioRepository.save(usuario);
		
	}


	public Usuario atualizarUsuario(Long id, @Valid UsuarioForm form) {
		Usuario usuario = usuarioRepository.getOne(id);
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataNasc = LocalDate.parse(form.getDataNascimento(), formatter);
			
			usuario.setCargo(new Cargo());
			usuario.setPerfil(new Perfil());
			
			usuario.setNome(form.getNomeUsuario());
			usuario.setCpf(form.getCpf());
			usuario.setSexo(form.getSexo());
			usuario.setDataNasc(dataNasc);
			usuario.setCargo(getCargo(form.getIdCargo()));
			usuario.setPerfil(getPerfil(form.getIdPerfil()));
		
			return usuario;
		}
		
		return null;
	}


	private Perfil getPerfil(Long idPerfil) {
		return perfilRepository.getOne(idPerfil);
	}


	private Cargo getCargo(Long idCargo) {
		return cargoRepository.getOne(idCargo);
	}


	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}


	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}


	@Override
	public Usuario inativar(Long id) throws Exception {
		Usuario usuario = usuarioRepository.getOne(id);
		
		if(usuario.getStatus().equals(StatusEnum.ATIVO)) {
			usuario.setStatus(StatusEnum.INATIVO);
		}
		
		return usuario;
	}


	public List<Usuario> listaFemininoMaioresIdade() throws Exception {
		return usuarioJDBC.listaFemininoMaioresIdade();
	}


	public List<Usuario> buscaCpfIniciadoZero() throws Exception {
		return usuarioJDBC.buscaCpfIniciadoZero();
	}


	public List<Usuario> findByNomeUsuarioAndCpf(String nomeUsuario, String cpf) throws Exception {
		return usuarioJDBC.findByNomeUsuarioAndCpf(nomeUsuario,cpf);
	}


	
}
