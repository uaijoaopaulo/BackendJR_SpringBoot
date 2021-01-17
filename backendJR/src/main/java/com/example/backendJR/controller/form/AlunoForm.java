package com.example.backendJR.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.backendJR.modelo.Aluno;

public class AlunoForm {

	@NotNull(message = "Nome não deve ser nulo") 
	private String nome;
	
	@Email(message = "Email deve ser valido") 
	@NotNull(message = "Email não deve ser nulo")
	private String email;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Aluno converter() {
		return new Aluno(nome, email, telefone);
	}
	
	public Aluno Atualizar(Aluno aluno) {
		if(email != null)
			aluno.setEmail(email);
		if(nome != null)
			aluno.setNome(nome);
		if(telefone != null)
			aluno.setTelefone(telefone);
		
		return aluno;
	}
	
}
