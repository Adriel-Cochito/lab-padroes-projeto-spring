package one.digitalinnovation.gof.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.Profissional;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.repository.ProfissionalRepository;
import one.digitalinnovation.gof.service.ProfissionalService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link Profissional;Service}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author Adriel-Cochito
 */
@Service
public class ProfissionalServiceImpl implements ProfissionalService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ProfissionalRepository profissionalRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Profissional> buscarTodos() {
		// Buscar todos os Clientes.
		return profissionalRepository.findAll();
	}

	@Override
	public Profissional buscarPorId(Long id) {
		// Buscar Cliente por ID.
		Optional<Profissional> profissional = profissionalRepository.findById(id);
		return profissional.get();
	}

	@Override
	public void inserir(Profissional profissional) {
		salvarProfissionalComCep(profissional);
	}

	@Override
	public void atualizar(Long id, Profissional profissional) {
		// Buscar Cliente por ID, caso exista:
		Optional<Profissional> profissionalBd = profissionalRepository.findById(id);
		if (profissionalBd.isPresent()) {
			salvarProfissionalComCep(profissional);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Cliente por ID.
		profissionalRepository.deleteById(id);
	}

	private void salvarProfissionalComCep(Profissional profissional) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = profissional.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		profissional.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		profissionalRepository.save(profissional);
	}

}

