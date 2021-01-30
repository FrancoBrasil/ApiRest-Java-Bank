package com.orion.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orion.bank.model.Conta;
import com.orion.bank.model.TransacoesConta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	void save(TransacoesConta t);
	
	
	
	/*
	 * Realizando busca por um atributo que é um relacionamento e não um campo da classe
	 * findBy + <nome do atributo ref o relacionamento> + <nome do campo>
	 * exemplo1: findByClienteNome -> Curso é um atributo referente relacionamento das classes
	 * Cliente e Conta.
	 * IMPORTANTE: supondo que na classe Conta houvesse um atritubo clienteNome para referenciar o 
	 * relacionamento -> findByCliente_Nome
	 * 
	 * Fazendo q query na mão
	 * @Query("SELECT t FROM Conta t WHERE t.cliente.nome = :nome")
	 * List<Cliente> carregarPorNome(@Param("nome")(String nome);
	 */
	
}
