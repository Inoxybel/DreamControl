package br.com.fiap.dreamcontrol.services;

import java.util.Optional;

import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
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
	private ObjetivoRepository repository;

	@Autowired
	public ObjetivoService(ObjetivoRepository repository) {
		this.repository = repository;
	}
	 
	 public Boolean cadastrarObjetivo(Objetivo objetivo, long userId)
	    {
			boolean successful;
	        log.info("cadastrando objetivo: " + objetivo);

			if (objetivo == null){
				successful = false;
			}
	        repository.save(objetivo);
			successful = true;

	        return successful;
	    }
	 
	 public Objetivo recuperarObjetivo(long userId)
	    {
	        log.info("buscando objetivo com id: " + userId);
	        
	        Optional<Objetivo> objetivoEncontrado = repository.findById(userId);
	        
	        if(!objetivoEncontrado.isPresent())
	        	return null;

			Objetivo objetivo = objetivoEncontrado.get();
	        
			return objetivo;
	    }
	

}
