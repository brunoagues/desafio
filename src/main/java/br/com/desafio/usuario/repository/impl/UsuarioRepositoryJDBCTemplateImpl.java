package br.com.desafio.usuario.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.desafio.usuario.mapper.UsuarioRowMapper;
import br.com.desafio.usuario.model.Usuario;
import br.com.desafio.usuario.repository.UsuarioRepositoryJDBCTemplate;

@Repository
public class UsuarioRepositoryJDBCTemplateImpl implements UsuarioRepositoryJDBCTemplate{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public List<Usuario> findByFilter(Usuario usuario) {
		
		 Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		 if(usuario.getNome()!=null && !usuario.getNome().isEmpty()) {
			 namedParameters.put("nome", "%"+usuario.getNome().toLowerCase()+"%");
		 }
		 if(usuario.getCpf()!=null && !usuario.getCpf().isEmpty()) {
			 namedParameters.put("cpf", usuario.getCpf());
		 }
		 if(usuario.getCargo()!=null && usuario.getCargo().getIdCargo()!=null) {
			 namedParameters.put("cargo", usuario.getCargo().getIdCargo());
		 }
		 if(usuario.getPerfil()!=null && usuario.getPerfil().getIdPerfil()!=null) {
			 namedParameters.put("perfil", usuario.getPerfil().getIdPerfil());
		 } 
		 if(usuario.getStatus()!=null) {
			 namedParameters.put("status", usuario.getStatus().toString());
		 }
			 SqlParameterSource params = new MapSqlParameterSource(namedParameters);
		 List<Usuario> listaUsuario = namedParameterJdbcTemplate.query(
						getHql(usuario), params,
						new UsuarioRowMapper());
			
			return listaUsuario;
	}
	
	private String getHql(Usuario usuario) {
		StringBuffer hql = new StringBuffer();
		
		getSqlComum(hql);
		
		hql.append("	WHERE 1=1 ");
		
		if(usuario.getNome()!=null && !usuario.getNome().isEmpty()) {
			hql.append("AND LOWER (U.NOME_USUARIO) LIKE :nome ");
		}
		if(usuario.getCpf()!=null && !usuario.getCpf().isEmpty()) {
			hql.append("AND U.CPF = :cpf ");
		}
		if(usuario.getCargo()!=null && usuario.getCargo().getIdCargo()!=null) {
			hql.append("AND U.ID_CARGO = :cargo ");
		}
		 if(usuario.getPerfil()!=null && usuario.getPerfil().getIdPerfil()!=null) {
			hql.append("AND U.ID_PERFIL = :perfil ");
		}
		if(usuario.getStatus()!=null) {
			hql.append("AND U.STATUS = :status ");
		}
		
		return hql.toString();
	}

	@Override
	public List<Usuario> listaFemininoMaioresIdade() throws Exception {
		
		 List<Usuario> listaUsuario = namedParameterJdbcTemplate.query(
				 getHqlFemininoMaioresIdade(),
					new UsuarioRowMapper());
		
		return listaUsuario;
	}

	private String getHqlFemininoMaioresIdade() {
		StringBuffer hql = new StringBuffer();
		
		getSqlComum(hql);
		hql.append("	WHERE 	U.DATA_NASCIMENTO < NOW() - INTERVAL 18 YEAR");
		hql.append("			AND U.SEXO = 'F'" );	
		
		return hql.toString();
	}
	
	@Override
	public List<Usuario> buscaCpfIniciadoZero() throws Exception {
		 
		List<Usuario> listaUsuario = namedParameterJdbcTemplate.query(
				 getHqlCpfZero() ,	new UsuarioRowMapper());
		
		return listaUsuario;
	}

	private String getHqlCpfZero() {
		StringBuffer hql = new StringBuffer();
		
		getSqlComum(hql);
		hql.append(" WHERE LEFT (U.CPF, 1) = '0'");
		
		return hql.toString();
	}

	@Override
	public List<Usuario> findByNomeUsuarioAndCpf(String nomeUsuario, String cpf) throws Exception {
		
		 Map<String, Object> namedParameters = new HashMap<String, Object>();
			
		 namedParameters.put("nome", nomeUsuario.toLowerCase());
		 namedParameters.put("cpf", cpf);
		 
		 SqlParameterSource params = new MapSqlParameterSource(namedParameters);
		 List<Usuario> listaUsuario = namedParameterJdbcTemplate.query(
						getHqlNomeCpf(nomeUsuario, cpf), params,
						new UsuarioRowMapper());
		
		return listaUsuario;
	}
	
	private String getHqlNomeCpf(String nomeUsuario, String cpf) {
		StringBuffer hql = new StringBuffer();
		getSqlComum(hql);
		
		hql.append(" WHERE 	NOME_USUARIO =:nome" );
		hql.append(" 		AND CPF =:cpf" );
		
		return hql.toString();
	}

	private void getSqlComum(StringBuffer hql) {
		hql.append("SELECT 	U.ID_USUARIO, ");
		hql.append("	 	U.NOME_USUARIO, ");
		hql.append("		U.CPF, ");
		hql.append("		U.SEXO, ");
		hql.append("		U.DATA_NASCIMENTO, ");
		hql.append("		C.NOME_CARGO, ");
		hql.append(" 		P.NOME_PERFIL, ");
		hql.append("		U.STATUS, ");
		hql.append("	FROM 	USUARIO U");
		hql.append("	INNER 	JOIN CARGO C ON U.ID_CARGO = C.ID_CARGO");
		hql.append("	INNER 	JOIN PERFIL P ON U.ID_PERFIL = P.ID_PERFIL");
	}

	


}
