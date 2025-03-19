package com.example.pessoa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pessoa.model.Pessoa;
import com.example.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;

	public Optional<Pessoa> getByCpf(String cpf) {
		return repository.findById(cpf);
	}

	public Pessoa adicionarPessoa(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	public List<Pessoa> adicionarPessoas(List<Pessoa> pessoa) {
		return repository.saveAll(pessoa);
	}

	public List<Pessoa> getAll() {
		return repository.findAll();
	}

	public void deleteByCpf(String cpf) {
		repository.deleteById(cpf);
	}

}
