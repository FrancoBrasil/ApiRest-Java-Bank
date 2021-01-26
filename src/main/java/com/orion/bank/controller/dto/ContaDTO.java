package com.orion.bank.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.orion.bank.model.Conta;
import com.orion.bank.model.Tipo;

public class ContaDTO {
	
	private Long id;
	private Byte agencia;
	private Byte numero;
	private Tipo tipo;
	
	public ContaDTO(Conta conta) {
		id = conta.getId();
		agencia = conta.getAgencia();
		numero = conta.getNumero();
		tipo = conta.getTipo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getAgencia() {
		return agencia;
	}

	public void setAgencia(Byte agencia) {
		this.agencia = agencia;
	}

	public Byte getNumero() {
		return numero;
	}

	public void setNumero(Byte numero) {
		this.numero = numero;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public static List<ContaDTO> converter(List<Conta> contas) {
		return contas.stream().map(ContaDTO::new).collect(Collectors.toList());
	}
	
}
