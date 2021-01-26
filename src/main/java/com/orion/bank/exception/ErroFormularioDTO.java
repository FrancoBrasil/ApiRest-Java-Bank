package com.orion.bank.exception;

public class ErroFormularioDTO {

	private String campo;
	private String erro;

	public ErroFormularioDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}
