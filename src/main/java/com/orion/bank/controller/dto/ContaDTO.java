package com.orion.bank.controller.dto;

import org.springframework.data.domain.Page;

import com.orion.bank.model.Conta;
import com.orion.bank.model.Tipo;

public class ContaDTO {
	
	private Long id;
	private Byte agencia;
	private Byte numero;
	private Tipo tipo;
	private double saldo;
	private double chequeEspecial;
	
	public ContaDTO(Conta conta) {
		id = conta.getId();
		agencia = conta.getAgencia();
		numero = conta.getNumero();
		tipo = conta.getTipo();
		saldo = conta.getSaldo();
		chequeEspecial = conta.getChequeEspecial();
	}
	public double getChequeEspecial() {
		return chequeEspecial;
	}
	
	public void setChequeEspecial(double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
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
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public static Page<ContaDTO> converter(Page<Conta> contas) {
		return contas.map(ContaDTO::new);
	}
	
}
