package br.com.desafio.usuario.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.usuario.controller.dto.PerfilDTO;
import br.com.desafio.usuario.controller.form.PerfilForm;
import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.service.PerfilService;

@RestController
@RequestMapping(path = "/perfil")
public class PerfilController {
	
	 private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired 
	private PerfilService service;
	
	@GetMapping
	public List<PerfilDTO> perfil() {
		
		
		List<Perfil> listPerfil = new ArrayList<Perfil>();
		try {
			listPerfil = service.findAll();
		} catch (Exception e) {
			 logger.error("PerfilController - perfil: Erro ao buscar perfil" + e.getMessage());
		}
		
		return PerfilDTO.converter(listPerfil);
	}
	
	@PostMapping
	public ResponseEntity<PerfilDTO> cadastrarPerfil(@RequestBody @Valid PerfilForm form,  UriComponentsBuilder uribBuilder){
		
		Perfil perfil = new Perfil();
		try {
			perfil = service.converterCargoFormParaEntity(form);
		} catch (Exception e) {
			logger.error("PerfilController - cadastrarPerfil: Erro ao converter perfil" + e.getMessage());
		}
		
		try {
			service.save(perfil);
		} catch (Exception e) {
			logger.error("PerfilController - cadastrarPerfil: Erro ao cadastrar perfil" + e.getMessage());
		}
		
		URI uri = uribBuilder.path("/{id}").buildAndExpand(perfil.getIdPerfil()).toUri();
		return ResponseEntity.created(uri).body(new PerfilDTO(perfil));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		Optional<Perfil> optional;
		try {
			optional = service.findById(id);
			if(optional.isPresent()) {
				service.deleteById(id);
				return ResponseEntity.ok().build();
			}
		} catch (Exception e) {
			logger.error("PerfilController - remover: Erro ao remover perfil" + e.getMessage());
		}
		
		return ResponseEntity.notFound().build();

	}
}
