package com.orion.bank.controller.dto;

import org.springframework.data.domain.Page;

import com.orion.bank.model.Cliente;

public class ClienteDTO {

	private Long id;
	private String nome;
	
	public ClienteDTO() {}

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDTO::new);
	}

}
