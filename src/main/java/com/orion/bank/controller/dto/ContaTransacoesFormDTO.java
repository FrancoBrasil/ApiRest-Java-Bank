package com.orion.bank.controller.dto;

import javax.validation.constraints.NotNull;

import com.orion.bank.model.Conta;
import com.orion.bank.repository.ContaRepository;

public class ContaTransacoesFormDTO {
	
	@NotNull
	private Double valor;

	public ContaTransacoesFormDTO() {
	}

	public ContaTransacoesFormDTO(Conta conta) {
		valor = conta.getValor();
	}

	public double getValor() {
		return valor;
	}

	public Conta depositar(Long id, ContaRepository repository) {
		Conta conta = repository.getOne(id);
		double saldo = conta.getSaldo();
		conta.setSaldo(this.valor + saldo);
		return conta;
	}
	
	public Conta sacar(Long id, ContaRepository repository) {
		Conta conta = repository.getOne(id);
		double saldo = conta.getSaldo();
		double chequeEspecial = conta.getChequeEspecial();
		if (this.valor > saldo + chequeEspecial) {
			throw new RuntimeException("Saldo Insuficiente!");
		}
		conta.setSaldo(saldo - this.valor);
		return conta;
	}
}
