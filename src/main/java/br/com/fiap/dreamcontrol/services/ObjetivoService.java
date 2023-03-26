package br.com.fiap.dreamcontrol.services;

import java.util.Optional;

import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.repositories.ObjetivoRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoService {

	Logger log = LoggerFactory.getLogger(ObjetivoService.class);
	private UsuarioRepository usuarioRepository;
	private ObjetivoRepository repository;

	@Autowired
	public ObjetivoService(ObjetivoRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}
	 
	 public Boolean cadastrarObjetivo(Objetivo objetivo, long userId)
	    {
			// Busca o usuário correspondente ao ID informado
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
			if (usuarioOptional.isEmpty()) {
				return false;
			}
			Usuario usuario = usuarioOptional.get();

			// Define o usuário como o dono do objetivo
			objetivo.setUsuario(usuario);

			// Salva o objetivo na base de dados
			repository.save(objetivo);

			return true;
	    }
	 
	 public Objetivo recuperarObjetivo(long userId)
	    {
	        log.info("buscando objetivo com id: " + userId);
	        
	        Optional<Usuario> objetivoEncontrado = usuarioRepository.findById(userId);
	        
	        if(objetivoEncontrado.isEmpty())
	        	return null;

			Usuario usuario = objetivoEncontrado.get();


			Objetivo objetivo = usuario.getObjetivo();
	        
			return objetivo;
	    }
	

}
