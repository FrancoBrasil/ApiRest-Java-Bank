package com.orion.bank.controller.dto;

import javax.validation.constraints.NotNull;

import com.orion.bank.model.Cliente;
import com.orion.bank.model.Conta;
import com.orion.bank.model.Tipo;
import com.orion.bank.repository.ClienteRepository;

public class ContaFormDTO {
	
	@NotNull
	private Tipo tipo;
	
	@NotNull
	private String cliente;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Conta converter(ClienteRepository repository) {
		Cliente clientes = repository.findByNome(cliente);
		return new Conta(tipo, clientes);
	}
}
