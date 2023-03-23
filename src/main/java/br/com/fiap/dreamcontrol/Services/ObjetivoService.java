package br.com.fiap.dreamcontrol.Services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Objetivo;
import br.com.fiap.dreamcontrol.repository.ObjetivoRepository;

public class ObjetivoService {
	
	Logger log = LoggerFactory.getLogger(ObjetivoService.class);
	
	 @Autowired
	 ObjetivoRepository repository;
	 
	 public ResponseEntity<Objetivo> cadastrarObjetivo(Objetivo objetivo, int userId)
	    {
	        log.info("cadastrando objetivo: " + objetivo);
	        
	        repository.save(objetivo);
	        
	        //Add tratamento de erro
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(objetivo);
	    }
	 
	 public ResponseEntity<Objetivo> recuperarObjetivo(long userId)
	    {
	        log.info("buscando objetivo com id: " + userId);
	        
	        Optional<Objetivo> objetivo = repository.findById(userId);
	        
	        if(objetivo.isPresent() == false)
	        	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Objetivo(0, 0));
	        
	        return ResponseEntity.status(HttpStatus.OK).body(objetivo.get());
	    }
	

}
