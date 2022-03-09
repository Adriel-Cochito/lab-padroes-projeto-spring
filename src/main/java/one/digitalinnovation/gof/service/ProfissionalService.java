package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Profissional;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author Adriel-Cochito
 */
public interface ProfissionalService {

	Iterable<Profissional> buscarTodos();

	Profissional buscarPorId(Long id);

	void inserir(Profissional profissional);

	void atualizar(Long id, Profissional profissional);

	void deletar(Long id);

}
