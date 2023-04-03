package br.com.fiap.dreamcontrol.services;

import java.util.Optional;

import br.com.fiap.dreamcontrol.dtos.UsuarioResponseDTO;
import br.com.fiap.dreamcontrol.dtos.UsuarioUpdateDTO;
import br.com.fiap.dreamcontrol.exceptions.RestNotFoundException;
import br.com.fiap.dreamcontrol.exceptions.RestUnauthorizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.dreamcontrol.dtos.LoginDTO;
import br.com.fiap.dreamcontrol.dtos.LoginResponseDTO;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	Logger log = LoggerFactory.getLogger(RegistroService.class);
	
	private UsuarioRepository repository;

	 @Autowired
 	public UsuarioService(UsuarioRepository repository) {
		 this.repository = repository;
	 }

	public Usuario cadastrar(Usuario usuario)
    {
        log.info("Cadastrando usuario: " + usuario);

		return repository.save(usuario);
    }

	public UsuarioResponseDTO atualizar(UsuarioUpdateDTO usuario, long id)
	{
		log.info("Atualizando cadastro de usuario pelo id: " + id);
		Usuario repositoryResponse = repository
										.findById(id)
										.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		boolean isUpdatable = false;

		if(usuario.nome() != null && usuario.nome() != repositoryResponse.getNome())
		{
			isUpdatable = repositoryResponse.setNome(usuario.nome());
		}

		if(usuario.email() != null && usuario.email() != repositoryResponse.getEmail())
		{
			isUpdatable = repositoryResponse.setEmail(usuario.email());
		}

		if(usuario.senha() != null && usuario.senha() != repositoryResponse.getSenha())
		{
			isUpdatable = repositoryResponse.setSenha(usuario.senha());
		}
			
		if(isUpdatable)
		{
			var respostaAtualizacao = atualizarUsuario(repositoryResponse);

			return new UsuarioResponseDTO(
				respostaAtualizacao.getId(),
				respostaAtualizacao.getNome(),
				respostaAtualizacao.getEmail(),
				respostaAtualizacao.getSenha()
			);
		}

		return null;
	}

	public Usuario recuperar(long id)
	{
		log.info("Recuperando cadastro de usuario pelo id: " + id);

		Usuario usuario = repository
							.findById(id)
							.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		return usuario;
	}

	public LoginResponseDTO logar(LoginDTO credenciais)
	{
		/*
			O ideal básico é utilizar JWT para executar o sistema de validação
			das credenciais, implantaremos ao decorrer do semestre ou alguma 
			outra estratégia de acordo com os conteúdos da aula, por hora retorna id
			e com esse ID a aplicação que consome tem acesso aos demais endpoints
			que necessitam do id do usuário
		*/
		log.info("Validando credenciais informadas");

		Usuario usuario = repository
							.buscarCredenciais(credenciais.email(), credenciais.senha())
							.orElseThrow(() -> new RestUnauthorizedException("Usuário ou Senha incorretos"));
		
		long acesso = usuario.getId();
		return new LoginResponseDTO(acesso);
	} 
	 
	private Usuario atualizarUsuario(Usuario usuario)
    {
        log.info("Atualizando usuario: " + usuario);

		return repository.saveAndFlush(usuario);
    }
}
