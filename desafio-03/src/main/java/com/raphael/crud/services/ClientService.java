package com.raphael.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphael.crud.dto.ClientDTO;
import com.raphael.crud.entities.Client;
import com.raphael.crud.repositories.ClientRepository;
import com.raphael.crud.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional
	public ClientDTO findById(Long id) {
		Client c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		return new ClientDTO(c);
	}
}
