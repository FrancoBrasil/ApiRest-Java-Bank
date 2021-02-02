package com.orion.bank.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orion.bank.controller.dto.TransacoesContaDTO;
import com.orion.bank.model.Conta;
import com.orion.bank.model.TransacoesConta;
import com.orion.bank.repository.ContaRepository;
import com.orion.bank.repository.TransacoesContaRepository;

@RestController
@RequestMapping("/extrato")
public class TransacoesContaController {
	
	@Autowired
	private TransacoesContaRepository repository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/conta/{id}/findByPeriod/{inicio}/{termino}")
	public ResponseEntity<List<TransacoesContaDTO>> findByPeriod(@PathVariable Long id, 
			@PathVariable String inicio, @PathVariable String termino) {
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			LocalDate dtInicio = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate dtFim = LocalDate.parse(termino, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			List<TransacoesConta> collection = optional.get().getTransacoesConta();
			collection = repository.findByPeriod(dtInicio, dtFim);
			return ResponseEntity.ok().body(TransacoesContaDTO.converter(collection));
		}
		return ResponseEntity.notFound().build();
	}

}
