package br.com.fiap.dreamcontrol.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.repository.RegistroRepository;

public class RegistroService {
	
	Logger log = LoggerFactory.getLogger(RegistroService.class);

	 @Autowired
	 RegistroRepository repository;
	 
	 public ResponseEntity<Registro> registrarSono(Registro registro, int userId)
	    {
	        log.info("registrando um periodo de sono: " + registro);
	        
	        //repository.save(registro);
		        
	        //Add tratamento de erro

	        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	    }
	 
	 public ResponseEntity<Registro> deletarRegistro (long userId){
	        log.info("apagando usuário utilizando id " + userId);
	        
	        repository.deleteById(userId);
	        
	        return ResponseEntity.status(HttpStatus.OK).build();
	    }
	 
}
