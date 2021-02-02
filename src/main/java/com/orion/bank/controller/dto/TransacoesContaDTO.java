package com.orion.bank.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.orion.bank.model.TransacoesConta;

public class TransacoesContaDTO {
	
	private Long id;
	private LocalDate date;
	private String tipo;
	private Double valor;
	
	public TransacoesContaDTO(TransacoesConta transacoes) {
		id = transacoes.getId();
		date = transacoes.getDate();
		tipo = transacoes.getTipo();
		valor = transacoes.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setData(LocalDate date) {
		this.date = date;
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

	public static List<TransacoesContaDTO> converter(List<TransacoesConta> extrato) {
		return extrato.stream().map(TransacoesContaDTO::new).collect(Collectors.toList());
	}
		
}
