package com.example.backendJR.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.backendJR.controller.form.AlunoForm;
import com.example.backendJR.modelo.Aluno;
import com.example.backendJR.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public Page<Aluno> lista(@PageableDefault(sort= "nome", direction= Direction.ASC, page = 0, size = 10) Pageable paginacao)
	{
		return alunoRepository.findAll(paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Aluno> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder)
	{
		Aluno aluno = form.converter();
		alunoRepository.save(aluno);
		URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(aluno);
	}
	
	@GetMapping("/buscarById/{id}")
	public ResponseEntity<Aluno> detalhar(@PathVariable Long id){
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarByEmail/{email}")
	public ResponseEntity<Aluno> detalharEmail(@PathVariable String email){
		Optional<Aluno> aluno = alunoRepository.findByEmail(email);
		if(aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody @Valid AlunoForm form){
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(aluno.isPresent()) { 
			return ResponseEntity.ok(form.Atualizar(aluno.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(aluno.isPresent()) {
			alunoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
