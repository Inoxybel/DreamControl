package br.com.fiap.dreamcontrol.services;


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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	Logger log = LoggerFactory.getLogger(RegistroService.class);

	private UsuarioRepository repository;
	private AuthenticationManager manager;
	private PasswordEncoder encoder;
	private TokenService tokenService;

	@Autowired
	public UsuarioService(UsuarioRepository repository, AuthenticationManager manager, PasswordEncoder encoder, TokenService tokenService) {
		this.repository = repository;
		this.manager = manager;
		this.encoder = encoder;
		this.tokenService = tokenService;
	}

	public Usuario cadastrar(Usuario usuario)
    {
        log.info("Cadastrando usuario.");
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);

		return repository.save(usuario);
    }

	public UsuarioResponseDTO atualizar(UsuarioUpdateDTO usuario, long id) {
		log.info("Atualizando cadastro de usuario pelo id: " + id);
		Usuario repositoryResponse = repository
				.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		boolean isUpdatable = false;

		if(usuario.nome() != null && !usuario.nome().equals(repositoryResponse.getNome())) {
			isUpdatable = repositoryResponse.setNome(usuario.nome());
		}

		if(usuario.email() != null && !usuario.email().equals(repositoryResponse.getEmail())) {
			isUpdatable = repositoryResponse.setEmail(usuario.email());
		}

		if(usuario.senha() != null && !usuario.senha().equals(repositoryResponse.getSenha())) {
			isUpdatable = repositoryResponse.setSenha(usuario.senha());
		}

		if(isUpdatable) {
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

	public Usuario recuperar(long id) {
		log.info("Recuperando cadastro de usuario pelo id: " + id);

		Usuario usuario = repository
				.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		return usuario;
	}

	public LoginResponseDTO logar(LoginDTO credenciais) {
		var authetication = manager.authenticate(credenciais.toAuthentication());
		var token = tokenService.generateToken(credenciais);

		var userDetails = (Usuario)authetication.getPrincipal();
		long acesso = userDetails.getId();

		return new LoginResponseDTO(acesso);
	}

	private Usuario atualizarUsuario(Usuario usuario)
    {
        log.info("Atualizando usuario: " + usuario);

		return repository.saveAndFlush(usuario);
    }
}
