package com.raphael.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.crud.dto.ClientDTO;
import com.raphael.crud.entities.Client;
import com.raphael.crud.repositories.ClientRepository;
import com.raphael.crud.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		return new ClientDTO(c);
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> result = repository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client c = new Client();
		copyDtoToEntity(dto, c);
		c = repository.save(c);
		return new ClientDTO(c);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client c = repository.getReferenceById(id);
			copyDtoToEntity(dto, c);
			c = repository.save(c);
			return new ClientDTO(c);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
	}

	private void copyDtoToEntity(ClientDTO dto, Client c) {
		c.setName(dto.getName());
		c.setCpf(dto.getCpf());
		c.setIncome(dto.getIncome());
		c.setBirthDate(dto.getBirthDate());
		c.setChildren(dto.getChildren());
	}
}
