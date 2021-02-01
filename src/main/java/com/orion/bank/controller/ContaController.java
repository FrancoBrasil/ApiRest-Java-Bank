package com.orion.bank.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.orion.bank.controller.dto.ContaDTO;
import com.orion.bank.controller.dto.ContaDetalharDTO;
import com.orion.bank.controller.dto.ContaFormDTO;
import com.orion.bank.controller.dto.ContaTranferenciaFormDTO;
import com.orion.bank.controller.dto.ContaTransacoesFormDTO;
import com.orion.bank.model.Conta;
import com.orion.bank.model.TransacoesConta;
import com.orion.bank.repository.ClienteRepository;
import com.orion.bank.repository.ContaRepository;
import com.orion.bank.repository.TransacoesContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TransacoesContaRepository transacoesRepository;
	
	@GetMapping
	public Page<ContaDTO> lista(@PageableDefault(sort = "agencia", direction = Direction.ASC) Pageable paginacao) {
		
		// Pageable paginacao = PageRequest.of(pagina, qtde, Direction.ASC, ordenacao);
		
		Page<Conta> contas = contaRepository.findAll(paginacao);
		return ContaDTO.converter(contas);
	}
	
	@GetMapping("/findByPeriod/{inicio}/{termino}")
	public ResponseEntity<List<ContaDTO>> findByPeriod(@PathVariable String inicio, @PathVariable String termino) {
		LocalDate dtInicio = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dtFim = LocalDate.parse(termino, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Conta> collection = contaRepository.findByPeriod(dtInicio, dtFim);
		return ResponseEntity.ok().body(ContaDTO.converter(collection));
	}
	/*
	@GetMapping(value = "/findByDate/{data}")
	public ResponseEntity<List<Conta>> findByDate(@PathVariable String data) {
		List<Conta> collection = contaRepository.findByDate(data);
		return ResponseEntity.ok().body(collection);
	}
	*/
	@PostMapping
	@Transactional
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
			@RequestBody @Valid ContaTransacoesFormDTO form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			Conta conta = form.depositar(id, contaRepository);
			TransacoesConta t = new TransacoesConta();
			t.setData(LocalDateTime.now());
			t.setTipo("Depósito");
			t.setValor(form.getValor());
			t.setConta(conta);
			transacoesRepository.save(t);
			return ResponseEntity.ok(new ContaDTO(conta));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/sacar/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> sacar(@PathVariable Long id, 
			@RequestBody @Valid ContaTransacoesFormDTO form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			Conta conta = form.sacar(id, contaRepository);
			TransacoesConta t = new TransacoesConta();
			t.setData(LocalDateTime.now());
			t.setTipo("Saque");
			t.setValor(form.getValor());
			t.setConta(conta);
			transacoesRepository.save(t);
			return ResponseEntity.ok(new ContaDTO(conta));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/transferir/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> transferir(@PathVariable Long id, 
			@RequestBody @Valid ContaTranferenciaFormDTO form) {
		Optional<Conta> optional = contaRepository.findById(id);
		if (optional.isPresent()) {
			Conta conta = form.transferir(id, contaRepository);
			TransacoesConta t = new TransacoesConta();
			t.setData(LocalDateTime.now());
			t.setTipo("Transferência");
			t.setValor(form.getValor());
			t.setConta(conta);
			transacoesRepository.save(t);
			return ResponseEntity.ok(new ContaDTO(conta));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Conta> contas = contaRepository.findById(id);
		if(contas.isPresent()) {
			contaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
