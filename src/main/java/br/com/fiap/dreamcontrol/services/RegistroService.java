package br.com.fiap.dreamcontrol.services;


import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import br.com.fiap.dreamcontrol.models.Registro;
import br.com.fiap.dreamcontrol.repositories.RegistroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class RegistroService {

	private RegistroRepository repository;
	private UsuarioRepository usuarioRepository;

	Logger log = LoggerFactory.getLogger(RegistroService.class);

	@Autowired
	public RegistroService(RegistroRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	public Boolean registrarSono(Registro registro, long userId) {
		log.info("registrando um periodo de sono: " + registro);

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
		if (usuarioOptional.isEmpty()) {
			return false;
		}

		Usuario usuario = usuarioOptional.get();
		for (Registro reg : usuario.getRegistros()) {
			if (reg.getData().equals(registro.getData())) {
				reg.getId();
				reg.setData(registro.getData());
				reg.setTempo(registro.getTempo());
				repository.save(reg);
				return true;
			}
		}
		registro.setUsuario(usuario);
		usuario.getRegistros().add(registro);
		repository.save(registro);
		return true;
	}



	@Transactional
	public Boolean deletarRegistro(long userId, long registroId) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
		if (usuarioOptional.isEmpty()) {
			log.info("empty");
			return false;
		}
		Usuario usuario = usuarioOptional.get();

		Optional<Registro> registroOptional = repository.findById(registroId);
		if (registroOptional.isEmpty()) {
			return false;
		}
		Registro registro = registroOptional.get();

		if (!registro.getUsuario().equals(usuario)) {
			log.info("getid: " + registro.getUsuario().getId());
			return false;
		}

		usuario.getRegistros().remove(registro);
		usuarioRepository.save(usuario);
		repository.delete(registro);

		return true;
	}

}
