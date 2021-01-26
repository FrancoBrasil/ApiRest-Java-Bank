package com.orion.bank.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

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

	public static List<ClienteDTO> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

}
