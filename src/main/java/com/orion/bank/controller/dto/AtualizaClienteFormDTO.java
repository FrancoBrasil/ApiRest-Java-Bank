package com.orion.bank.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;

import com.orion.bank.model.Cliente;
import com.orion.bank.repository.ClienteRepository;

public class AtualizaClienteFormDTO {
	
	private String nome;
	private LocalDateTime dataNascimento;
	@Email
	private String email;

	public AtualizaClienteFormDTO() {
	}

	public AtualizaClienteFormDTO(Cliente cliente) {
		nome = cliente.getNome();
		dataNascimento = cliente.getDataNascimento();
		email = cliente.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNacimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setNome(this.nome);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEmail(this.email);
		return cliente;
	}

}
