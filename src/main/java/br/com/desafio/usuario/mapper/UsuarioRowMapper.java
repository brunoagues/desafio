package br.com.desafio.usuario.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import br.com.desafio.usuario.Enums.StatusEnum;
import br.com.desafio.usuario.model.Cargo;
import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.model.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Usuario usuario = new Usuario();
		Cargo cargo = new Cargo();
		Perfil perfil = new Perfil();
		
		usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
		usuario.setNome(rs.getString("NOME_USUARIO"));
		usuario.setCpf(rs.getString("CPF"));
		usuario.setDataNasc(rs.getObject("DATA_NASCIMENTO", LocalDate.class));
		usuario.setSexo(rs.getString("SEXO"));
		
		if(rs.getString("STATUS").equalsIgnoreCase("ATIVO")) { 
			usuario.setStatus(StatusEnum.ATIVO);
		}else {
			usuario.setStatus(StatusEnum.INATIVO);
		}
		
		//perfil.setId(rs.getLong("ID_PERFIL"));
		perfil.setNome(rs.getString("NOME_PERFIL"));
		
		//cargo.setId(rs.getLong("ID_CARGO"));
		cargo.setNome(rs.getString("NOME_CARGO"));
		
		usuario.setPerfil(perfil);
		usuario.setCargo(cargo);
		
		return usuario;

	}
}