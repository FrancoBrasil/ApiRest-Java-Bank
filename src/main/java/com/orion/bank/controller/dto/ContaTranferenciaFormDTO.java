package com.orion.bank.controller.dto;

import javax.validation.constraints.NotNull;

import com.orion.bank.model.Conta;
import com.orion.bank.repository.ContaRepository;

public class ContaTranferenciaFormDTO {
	
	@NotNull
	private Double valor;
	
	@NotNull
	private Long contaDestino;

	public ContaTranferenciaFormDTO() {
	}

	public ContaTranferenciaFormDTO(Conta conta) {
		valor = conta.getValor();
		contaDestino = conta.getId();
	}

	public double getValor() {
		return valor;
	}
	
	public Long getContaDestino() {
		return contaDestino;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setContaDestino(Long contaDestino) {
		this.contaDestino = contaDestino;
	}
	
	public Conta transferir(Long id, ContaRepository repository) {
		Conta contaOrigem = repository.getOne(id);
		double saldoOrigem = contaOrigem.getSaldo();
		if (this.valor > saldoOrigem) {
			throw new RuntimeException("Saldo Insuficiente!");
		}
		contaOrigem.setSaldo(saldoOrigem - this.valor);
		Conta contaDestino = repository.getOne(this.contaDestino);
		double saldoDestino = contaDestino.getSaldo();
		contaDestino.setSaldo(this.valor + saldoDestino);
		return contaOrigem;
	}
}
