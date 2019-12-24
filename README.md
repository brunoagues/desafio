##Gerenciador de usuários:

Aplicação com a finalidade de manter usuários.


##Funcinalidades:

###UsuarioController:

*  	[buscaUsuario] 	busca usuários por  nome, cpf, cargo, perfil e/ou status. <p/>

*	[cadastar] 		cadastra usuarios, não sendo permitido usuarios com nome e cpf repetidos.<p/>
	
*	[usuarios] 		lista todos os usuários;<p/>
	
*	[listaFemininoMaioresIdade] 	retorna os usuários do sexo feminino e que sejam maiores de 18 anos<p/>

*   [buscaCpfIniciadoZero]		busca usuários com cpf iniciado por zero<p/>

*	[atualizar]		atualiza usuario.<p/>

*   [remover]		remove usuário.<p/>

*	[inativa] 		verifica se o usuário esta ativo e inativa o mesmo.

###CargoController

*	[cargos] 	lista todos os cargos.<p/>

*	[cadastrarCargo]	reliza o cadastro de novos cargos <p/>

*	[remover] 	remove o cargo.<p/>

###PerfilController

*	[perfil] 	lista todos os perfis.<p/>

*	[cadastrarPerfil]	reliza o cadastro de novos perfis <p/>

*	[remover] 	remove o perfil.<p/>



##Chamadas dos metodos

###UsuarioController: 

*  	[listar usuarios GET](http://localhost:8080/usuario) <p/>

* 	[buscar usuarios por filtro GET](http://localhost:8080/usuario/buscaUsuario?nomeUsuario=Usuario1&cpf=01370444702&cargo=1&perfil=2&status=ATIVO)
 <p/>
 
* 	[cadastrar POST]: 	http://localhost:8080/usuario/cadastrar <p/>

		{
			"nomeUsuario": "Usuario2",
			"cpf": "39272457041",
			"sexo": "M",
			"dataNascimento":"2005-12-25",
			"idCargo": 1,
			"idPerfil":2	
		}

*	[atualizar PUT]: http://localhost:8080/usuario/atualizar/2

			{
				"nomeUsuario": "Usuario atualizado",
				"cpf": "01370444702",
				"sexo": "F",
				"dataNascimento":"2001-12-25",
				"idCargo": 2,
				"idPerfil":2			
			}
			
		
* 	[inativar PATCH](http://localhost:8080/usuario/inativar/1) <p/>

*	[remover DELETE](http://localhost:8080/usuario/1) <p/>

*	[buscar cpf iniciado por zero GET](http://localhost:8080/usuario//cpfzero)

*	[buscar usuarios do sexo feminino maiores de 18 GET](http://localhost:8080/usuario/feminino)

###CargoController: 

*	[lista cargos GET](http://localhost:8080/cargos).<p/>

*	[cadastrar cargo POST](http://localhost:8080/cargos)<p/>

		{
			"nome": "GERENTE"
		}

*	[remover cargo DELETE](http://localhost:8080/cargos/1)<p/>


###PerfilController: 

*	[lista perfis GET](http://localhost:8080/perfil).<p/>

*	[cadastrar perfil POST](http://localhost:8080/perfil)<p/>

		{
			"nome": "ADMINISTRADOR"
		}

*	[remover perfil DELETE](http://localhost:8080/perfil/1)<p/>
