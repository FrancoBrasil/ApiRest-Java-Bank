package com.orion.bank.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.orion.bank.controller.dto.ContaDTO;
import com.orion.bank.controller.dto.ContaTransacoesFormDTO;
import com.orion.bank.controller.dto.ContaDetalharDTO;
import com.orion.bank.controller.dto.ContaFormDTO;
import com.orion.bank.model.Conta;
import com.orion.bank.repository.ClienteRepository;
import com.orion.bank.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ContaDTO> lista() {
		List<Conta> contas = contaRepository.findAll();
		return ContaDTO.converter(contas);
	}
	
	@PostMapping
	public ResponseEntity<ContaDTO> cadastar(@RequestBody @Valid ContaFormDTO form,
			UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter(clienteRepository);
		contaRepository.save(conta);

		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDTO(conta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDetalharDTO> detalhar(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		if (conta.isPresent()) {
			return ResponseEntity.ok(new ContaDetalharDTO(conta.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/depositar/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> depositar(@PathVariable Long id, 
			@RequestBody ContaTransacoesFormDTO form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			Conta conta = form.depositar(id, contaRepository);
			return ResponseEntity.ok(new ContaDTO(conta));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/sacar/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> sacar(@PathVariable Long id, 
			@RequestBody ContaTransacoesFormDTO form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			Conta conta = form.sacar(id, contaRepository);
			return ResponseEntity.ok(new ContaDTO(conta));
		}
		return ResponseEntity.notFound().build();
	}
}
