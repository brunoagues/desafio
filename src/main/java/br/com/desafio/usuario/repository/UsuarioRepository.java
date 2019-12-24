package br.com.desafio.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByIdUsuario(Long id);


	
}
