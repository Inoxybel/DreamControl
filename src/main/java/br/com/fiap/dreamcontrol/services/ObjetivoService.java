package br.com.fiap.dreamcontrol.services;


import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.dreamcontrol.exceptions.RestNotFoundException;
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
	 
	 public Objetivo cadastrarObjetivo(Objetivo objetivo, long userId)
	    {
			Usuario usuario = usuarioRepository
									.findById(userId)
									.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

			objetivo.setUsuario(usuario);
			objetivo.setDataCriacao();

			return repository.save(objetivo);
	    }
	 
	 public Objetivo recuperarObjetivo(long userId)
	    {
	        log.info("buscando objetivo com id: " + userId);
	        
	        Usuario usuario = usuarioRepository
									.findById(userId)
									.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

			Objetivo objetivo = usuario.getObjetivo();
	        
			return objetivo;
	    }
}
