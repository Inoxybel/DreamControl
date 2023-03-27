package br.com.fiap.dreamcontrol.services;


import br.com.fiap.dreamcontrol.models.Historico;
import br.com.fiap.dreamcontrol.models.Usuario;
import br.com.fiap.dreamcontrol.repositories.HistoricoRepository;
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
	private HistoricoRepository historicoRepository;

	Logger log = LoggerFactory.getLogger(RegistroService.class);

	@Autowired
	public RegistroService(RegistroRepository repository, UsuarioRepository usuarioRepository, HistoricoRepository historicoRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
		this.historicoRepository = historicoRepository;
	}

	public Boolean registrarSono(Registro registro, long userId) {
		log.info("registrando um periodo de sono: " + registro);

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
		if (usuarioOptional.isEmpty()) {
			return false;
		}

		Usuario usuario = usuarioOptional.get();
		registro.setUsuario(usuario);
		usuario.getRegistros().add(registro);
		repository.save(registro);

		return true;
	}

	@Transactional
	public Boolean deletarRegistro(long userId, long registroId) {
		log.info("apagando registro utilizando id " + registroId);

		if (!usuarioRepository.existsById(userId)) {
			return false;
		}

		Optional<Registro> registroOptional = repository.findById(registroId);
		if (registroOptional.isEmpty()) {
			return false;
		}

		Registro registro = registroOptional.get();
		if (registro.getUsuario().getId() != userId) {
			return false;
		}

		// Deleta o registro na tabela de Registros
		repository.delete(registro);

		// Deleta o registro correspondente na tabela de Historico
		Optional<Historico> historicoOptional = historicoRepository.findByRegistroId(registroId);
		historicoOptional.ifPresent(historico -> historicoRepository.delete(historico));

		return true;
	}

}
