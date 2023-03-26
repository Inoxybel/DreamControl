package br.com.fiap.dreamcontrol.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

	private RegistroRepository repository;
	Logger log = LoggerFactory.getLogger(RegistroService.class);

	@Autowired
	public RegistroService(RegistroRepository repository) {
		this.repository = repository;
	}
	 
	 public ResponseEntity<Registro> registrarSono(Registro registro, int userId)
	{
		log.info("registrando um periodo de sono: " + registro);
		
		repository.save(registro);

		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	 
	 public ResponseEntity<Registro> deletarRegistro (long userId){
		log.info("apagando usuário utilizando id " + userId);
		
		repository.deleteById(userId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
