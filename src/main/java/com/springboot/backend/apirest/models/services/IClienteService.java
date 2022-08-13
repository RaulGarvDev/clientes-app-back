package com.springboot.backend.apirest.models.services;

import com.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();

    public Cliente findById(Long id);

    public Cliente save( Cliente cliente);

    public void delete(Long id);
}
