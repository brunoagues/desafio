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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.usuario.Enums.StatusEnum;
import br.com.desafio.usuario.controller.dto.UsuarioDTO;
import br.com.desafio.usuario.controller.form.UsuarioForm;
import br.com.desafio.usuario.model.Cargo;
import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.model.Usuario;
import br.com.desafio.usuario.service.UsuarioService;

@RestController
@RequestMapping(path="/usuario")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<UsuarioDTO> usuarios() {
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		try {
			listUsuario = service.findAll();
		} catch (Exception e) {
			 logger.error("UsuarioController - usuarios: Erro ao buscar usuarios" + e.getMessage());
		}
		
		return UsuarioDTO.converter(listUsuario);
	}
	
	//retornar usuarios do sexo feminino maiores que 18 anos
	@GetMapping(path = "/feminino")
	public List<UsuarioDTO> listaFemininoMaioresIdade() {
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		try {
			listUsuario = service.listaFemininoMaioresIdade();
		} catch (Exception e) {
			 logger.error("UsuarioController - listaFemininoMaioresIdade: Erro ao buscar usuarios" + e.getMessage());
		}
		
		return UsuarioDTO.converter(listUsuario);
	}
	
	

	//retornar usuarios com cpf iniciado por 0
	@GetMapping(path = "/cpfzero")
	public List<UsuarioDTO> buscaCpfIniciadoZero() {
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		try {
			listUsuario = service.buscaCpfIniciadoZero();
		} catch (Exception e) {
			 logger.error("UsuarioController - buscaCpfIniciadoZero: Erro ao buscar usuarios" + e.getMessage());
		}
		
		return UsuarioDTO.converter(listUsuario);
	}
	
	@GetMapping(path="/buscaUsuario")
	public List<UsuarioDTO> buscaUsuario(@RequestParam("nomeUsuario") String nomeUsuario,
										@RequestParam("cpf") String cpf,
										@RequestParam("cargo") Long cargo,
										@RequestParam("perfil") Long perfil,
										@RequestParam("status") String status) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(nomeUsuario);
		
		usuario.setCargo(new Cargo());
		usuario.getCargo().setIdCargo(cargo);
		usuario.setCpf(cpf);
		usuario.setPerfil(new Perfil());
		usuario.getPerfil().setIdPerfil(perfil);
		
		if(status!=null && status.trim()!="" ) {
			if(status.equals("ATIVO")) {
				usuario.setStatus(StatusEnum.ATIVO);
			}else {
				usuario.setStatus(StatusEnum.INATIVO);
			}
		}
		
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		try {
			listUsuario = service.findByFilter(usuario);
		} catch (Exception e) {
			 logger.error("UsuarioController - buscaUsuario: Erro ao buscar usuarios" + e.getMessage());
		}
		
		return UsuarioDTO.converter(listUsuario);
	}
	
	@PostMapping(path="/cadastrar")
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uribBuilder) throws Exception{
	
		Usuario usuario = new Usuario();
		ResponseEntity<UsuarioDTO> rtr;
		
		try {
			
			usuario = service.converterUsuarioFormParaEntity(form);
		} catch (Exception e) {
			logger.error("UsuarioController - cadastrar: Erro ao buscar usuarios" + e.getMessage());
		}
		if(validaUsuario(form, null)) {
			try {
				service.save(usuario);
				URI uri = uribBuilder.path("/usuarios/{idUsuario}").buildAndExpand(usuario.getIdUsuario()).toUri();
				rtr =  ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
				
			} catch (Exception e) {
				logger.error("UsuarioController - cadastrar: Erro ao cadastrar usuarios" + e.getMessage());
				rtr = ResponseEntity.badRequest().build();
				
				
			}
		}else {
			rtr = ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		return rtr;
		
	}
	
	private Boolean validaUsuario(UsuarioForm form, Long id) {
		
		Boolean rtr = false;
		try {
			List<Usuario> usuario = service.findByNomeUsuarioAndCpf(form.getNomeUsuario(),form.getCpf());
			if(usuario.isEmpty()) {
				rtr = true;
			}else {
				if(id!=null) {
					rtr = usuario.get(0).getIdUsuario().equals(id)? true:false;
				}
			}
		} catch (Exception e) {
			logger.error("UsuarioController - cadastrar: Erro ao buscar usuarios" + e.getMessage());
		}
		
		return rtr;
	}

	@PutMapping(path="/atualizar/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form){
		ResponseEntity<UsuarioDTO> rtr;
		Usuario usuario = new Usuario();
		
		if(validaUsuario(form, id)) {
			
			try {
				usuario = service.atualizarUsuario(id, form);
				if(usuario==null) {
					rtr =  ResponseEntity.notFound().build();
				}
			} catch (Exception e) {
				logger.error("UsuarioController - atualizar: Erro ao atualizars usuarios" + e.getMessage());
			}
			
			rtr =  ResponseEntity.ok(new UsuarioDTO(usuario));
		}else {
			rtr = ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		return rtr;

	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		Optional<Usuario> optional;
		try {
			optional = service.findById(id);
			if(optional.isPresent()) {
				service.deleteById(id);
				return ResponseEntity.ok().build();
			}
		} catch (Exception e) {
			logger.error("UsuarioController - remover: Erro ao remover usuarios" + e.getMessage());
		}
		
		return ResponseEntity.notFound().build();

	}
	
	@PatchMapping("/inativar/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> inativar(@PathVariable Long id){
		Usuario usuario = new Usuario();
		try {
			 usuario = service.inativar(id);
		} catch (Exception e) {
			logger.error("UsuarioController - inativar: Erro ao atualizars usuarios" + e.getMessage());
		}
		
		return ResponseEntity.ok(new UsuarioDTO(usuario));
	}
}
