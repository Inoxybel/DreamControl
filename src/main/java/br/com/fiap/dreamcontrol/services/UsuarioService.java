package br.com.fiap.dreamcontrol.services;

import java.util.Optional;

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


	public void cadastrar(Usuario usuario)
    {
        log.info("cadastrando usuario: " + usuario);

		repository.save(usuario);
    }

	public Usuario atualizar(Usuario usuario, long id)
    {
        log.info("atualizando cadastro de usuario pelo id: " + id);
        
		Optional<Usuario> repositoryResponse = repository.findById(id);
		
		if(repositoryResponse.isPresent())
		{
			Usuario usuarioExistente = repositoryResponse.get();
			boolean isUpdatable = false;
			String novoNome = usuario.getNome();
			String novoEmail = usuario.getEmail();
			String novaSenha = usuario.getSenha();

			if(usuarioExistente.getNome() != novoNome && !novoNome.isEmpty())
			{
				usuarioExistente.setNome(novoNome);
				isUpdatable = true;
			}

			if(usuarioExistente.getEmail() != novoEmail && !novoEmail.isEmpty())
			{
				usuarioExistente.setEmail(novoEmail);
				isUpdatable = true;
			}

			if(usuarioExistente.getSenha() != novaSenha)
				isUpdatable = usuarioExistente.setSenha(novaSenha);

			if(isUpdatable)
			{
				repository.save(usuarioExistente);
				return usuario;
			}
		}

        return null;
    }

	public Usuario recuperar(long id)
	{
		log.info("recuperando cadastro de usuario pelo id: " + id);

		Optional<Usuario> repositoryResponse = repository.findById(id);

		if(repositoryResponse.isPresent())
			return repositoryResponse.get();
		
		return new Usuario("", "", "");
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
		Optional<Usuario> repositoryResponse = repository.buscarCredenciais(credenciais.email(), credenciais.senha());
		
		if(repositoryResponse.isPresent())
		{
			long acesso = repositoryResponse.get().getId();
			return new LoginResponseDTO(acesso);
		}
			
		return new LoginResponseDTO(0);
	} 
	 
}
