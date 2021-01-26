package com.orion.bank.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.orion.bank.model.Cliente;
import com.orion.bank.repository.ClienteRepository;

public class AtualizaClienteFormDTO {
	
	@NotNull @NotEmpty
	private String nome;
	private LocalDateTime dataNascimento;

	public AtualizaClienteFormDTO() {
	}

	public AtualizaClienteFormDTO(Cliente cliente) {
		nome = cliente.getNome();
		dataNascimento = cliente.getDataNascimento();
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

	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setNome(this.nome);
		cliente.setDataNascimento(this.dataNascimento);
		return cliente;
	}

}
