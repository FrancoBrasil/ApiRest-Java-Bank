package com.orion.bank.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.orion.bank.model.Conta;
import com.orion.bank.model.Tipo;

public class ContaDetalharDTO {
	
	private Long id;
	private Byte agencia;
	private Byte numero;
	private Tipo tipo;
	private String cliente;
	
	public ContaDetalharDTO(Conta conta) {
		id = conta.getId();
		agencia = conta.getAgencia();
		numero = conta.getNumero();
		tipo = conta.getTipo();
		cliente = conta.getCliente().getNome();
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
	
	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public static List<ContaDetalharDTO> converter(List<Conta> contas) {
		return contas.stream().map(ContaDetalharDTO::new).collect(Collectors.toList());
	}
	
}