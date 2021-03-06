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
	private Long idCliente;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Conta converter(ClienteRepository repository) {
		Cliente clientes = repository.getOne(idCliente);
		return new Conta(tipo, clientes);
	}
}
