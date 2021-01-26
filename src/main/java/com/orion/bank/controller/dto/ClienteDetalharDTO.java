package com.orion.bank.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.orion.bank.model.Cliente;

public class ClienteDetalharDTO {

	private Long id;
	private String nome;
	private Long cpf;
	private LocalDateTime dataNascimento;
	private String email;
	private List<ContaDTO> contas = new ArrayList<>();

	public ClienteDetalharDTO() {
	}

	public ClienteDetalharDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		dataNascimento = cliente.getDataNascimento();
		email = cliente.getEmail();
		contas.addAll(cliente.getContas().stream().map(ContaDTO::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ContaDTO> getContas() {
		return contas;
	}

	public void setContas(List<ContaDTO> contas) {
		this.contas = contas;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static List<ClienteDetalharDTO> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDetalharDTO::new).collect(Collectors.toList());
	}

}
