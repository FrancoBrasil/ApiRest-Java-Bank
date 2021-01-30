package com.orion.bank.controller.dto;

import java.time.LocalDateTime;

import com.orion.bank.model.TransacoesConta;

public class TransacoesContaDTO {
	
	private Long id;
	private LocalDateTime data;
	private String tipo;
	private Double valor;
	
	public TransacoesContaDTO(TransacoesConta transacoes) {
		id = transacoes.getId();
		data = transacoes.getData();
		tipo = transacoes.getTipo();
		valor = transacoes.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
		
}
