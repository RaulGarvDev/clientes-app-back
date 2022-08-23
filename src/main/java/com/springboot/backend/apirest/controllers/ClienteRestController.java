package com.springboot.backend.apirest.controllers;


import com.springboot.backend.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index(){

        return  clienteService.findAll();
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) {

        System.out.println(clienteService.findById(id));
        return  new ResponseEntity<Cliente>(clienteService.findById(id), HttpStatus.OK);
    }


    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>create(@Valid @RequestBody Cliente cliente, BindingResult result){

        Cliente clienteNew = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){

           // List<String> errors1 = new ArrayList<>();

          /*  for (FieldError err : result.getFieldErrors()){
                errors.add("El campo" + err.getField()+" "+err.getDefaultMessage());
            }*/

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map( err -> "El campo"+ err.getField()+" "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        try{

            clienteNew = clienteService.save(cliente);


        }catch(DataAccessException e){

            response.put("mensaje", "Error al realizar el insert en la bbdd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "el cliente ha sido creado con exito");
        response.put("cliente", clienteNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update (@RequestBody Cliente cliente, @PathVariable Long id){
        Cliente clienteActual = clienteService.findById(id);

        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setEmail(cliente.getEmail());

         clienteService.save(clienteActual);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){

        clienteService.delete(id);
    }

}
