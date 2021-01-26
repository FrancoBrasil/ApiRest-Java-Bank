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

import com.orion.bank.controller.dto.AtualizaClienteFormDTO;
import com.orion.bank.controller.dto.ClienteDTO;
import com.orion.bank.controller.dto.ClienteDetalharDTO;
import com.orion.bank.controller.dto.ClienteFormDTO;
import com.orion.bank.model.Cliente;
import com.orion.bank.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<ClienteDTO> lista(Long cpf) {
		if (cpf == null) {
			List<Cliente> clientes = clienteRepository.findAll();
			return ClienteDTO.converter(clientes);
		} else {
			List<Cliente> clientesPorCpf = clienteRepository.findByCpf(cpf);
			return ClienteDTO.converter(clientesPorCpf);
		}
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> cadastar(@RequestBody @Valid ClienteFormDTO form,
			UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter();
		clienteRepository.save(cliente);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDetalharDTO> detalhar(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDetalharDTO(cliente.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizaClienteFormDTO form) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}

		return ResponseEntity.notFound().build();
	}
}
