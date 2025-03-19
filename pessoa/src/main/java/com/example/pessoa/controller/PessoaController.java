package com.example.pessoa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.pessoa.model.Pessoa;
import com.example.pessoa.service.PessoaService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PessoaController {

	@Autowired
	PessoaService service;

	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> getPessoas() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/pessoa/{cpf}")
	public ResponseEntity<Optional<Pessoa>> getPessoa(@PathVariable String cpf) {
		return ResponseEntity.ok(service.getByCpf(cpf));
	}

	@DeleteMapping("/pessoa/{cpf}")
	public ResponseEntity<Optional<Pessoa>> deletePessoa(@PathVariable String cpf) {
		service.deleteByCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
		URI location = getUri(pessoa.getCpf());
		service.adicionarPessoa(pessoa);
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody List<Pessoa> pessoa) {
		URI location = getUri("");
		service.adicionarPessoas(pessoa);
		return ResponseEntity.created(location).build();
	}

	private URI getUri(String cpf) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(cpf).toUri();
	}

}
