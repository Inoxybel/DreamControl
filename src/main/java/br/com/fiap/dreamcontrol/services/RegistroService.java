package br.com.fiap.dreamcontrol.services;

import br.com.fiap.dreamcontrol.models.Objetivo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroService {

	private RegistroRepository repository;
	Logger log = LoggerFactory.getLogger(RegistroService.class);

	@Autowired
	public RegistroService(RegistroRepository repository) {
		this.repository = repository;
	}
	 
	 public Boolean registrarSono(Registro registro, long userId)
	{
		boolean successful;
		log.info("registrando um periodo de sono: " + registro);

		if (registro == null) {
			successful = false;
		}

		repository.save(registro);
		successful = true;

		return successful;
	}
	 
	 public Boolean deletarRegistro (long userId)
	 {
		 boolean sucessful;
		 log.info("apagando usuário utilizando id " + userId);

		 Optional<Registro> registroEncontrado = repository.findById(userId);
		 if (registroEncontrado.isEmpty()) {
			 sucessful = false;
		 }

		 repository.deleteById(userId);
		 sucessful = true;

		 return sucessful;
	}
	
}
