package com.orion.bank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Conta {

	Random random = new Random();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();
	private Byte agencia = geradorDeAgencia();
	private Byte numero = geradorDeNumero();
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	protected Double saldo = 0.0;
	private Boolean isAtiva = true;
	private Double valor;
	private Double chequeEspecial = 0.0;

	@OneToOne
	private Conta contaDestino;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
	private List<TransacoesConta> transacoesConta = new ArrayList<>();

	public Conta() {
	}

	public Conta(Tipo tipo, Cliente cliente) {
		this.tipo = tipo;
		this.cliente = cliente;
		if (this.tipo.equals(Tipo.CONTA_CORRENTE)) {
			this.chequeEspecial = 1000.0;
		}

	}

	public Conta(Double valor, Conta contaDestino) {
		this.valor = valor;
		this.contaDestino = contaDestino;
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

	public void setDate(LocalDate date) {
		this.date = date;
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

	public Boolean getIsAtiva() {
		return isAtiva;
	}

	public void setIsAtiva(Boolean isAtiva) {
		this.isAtiva = isAtiva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	public void setChequeEspecial(Double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}
	
	public List<TransacoesConta> getTransacoesConta() {
		return transacoesConta;
	}
	
	public void setTransacoesConta(List<TransacoesConta> transacoesConta) {
		this.transacoesConta = transacoesConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private Byte geradorDeAgencia() {
		byte x = (byte) random.nextInt();
		byte r = (byte) ((x * 2) + x);
		if (r < 0) {
			return (byte) (r * (-1));
		}
		return r;
	}

	private Byte geradorDeNumero() {
		byte x = (byte) random.nextInt();
		byte r = (byte) ((x * 2) + x);

		if (r < 0) {
			return (byte) (r * (-1));
		}
		return r;
	}

}
