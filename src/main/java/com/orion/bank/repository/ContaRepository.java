package com.orion.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orion.bank.model.Conta;
import com.orion.bank.model.TransacoesConta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	

	void save(TransacoesConta t);


    @Query("SELECT t FROM Conta t WHERE t.date BETWEEN :inicio and :termino")
    public List<Conta> findByPeriod(LocalDate inicio, LocalDate termino);
	
    
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
