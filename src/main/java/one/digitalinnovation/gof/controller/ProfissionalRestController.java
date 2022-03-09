package one.digitalinnovation.gof.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Profissional;
import one.digitalinnovation.gof.service.ProfissionalService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author Adriel-Cochito
 */
@RestController
@RequestMapping("Profissionais")
public class ProfissionalRestController {

	@Autowired
	private ProfissionalService profissionalService;

	@GetMapping
	public ResponseEntity<Iterable<Profissional>> buscarTodos() {
		return ResponseEntity.ok(profissionalService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(profissionalService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Profissional> inserir(@RequestBody Profissional Profissional) {
		profissionalService.inserir(Profissional);
		return ResponseEntity.ok(Profissional);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody Profissional Profissional) {
		profissionalService.atualizar(id, Profissional);
		return ResponseEntity.ok(Profissional);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		profissionalService.deletar(id);
		return ResponseEntity.ok().build();
	}
}