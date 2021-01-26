package com.orion.bank.controller.dto;

import com.orion.bank.model.Conta;
import com.orion.bank.repository.ContaRepository;

public class ContaDepositarFormDTO {

	private double valorDeposito;

	public ContaDepositarFormDTO() {
	}

	public ContaDepositarFormDTO(Conta conta) {
		valorDeposito = conta.getValorDeposito();
	}

	public double getValorDeposito() {
		return valorDeposito;
	}

	public Conta atualizar(Long id, ContaRepository repository) {
		Conta conta = repository.getOne(id);
		double saldo = 0.0;
		saldo = conta.getSaldo();
		conta.setSaldo(this.valorDeposito + saldo);
		return conta;
	}

}
