package com.orion.bank.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orion.bank.model.Cliente;

public class ClienteFormDTO {
	
	@NotNull @NotBlank
	private String nome;
	@Digits(fraction = 0, integer = 11)
	private Long cpf;
	@Email
	private String email;
	private LocalDateTime dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataNacimento() {
		return dataNascimento;
	}

	public void setDataNacimento(LocalDateTime dataNacimento) {
		this.dataNascimento = dataNacimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Cliente converter() {
		return new Cliente(nome, cpf, email, dataNascimento);
	}
}
