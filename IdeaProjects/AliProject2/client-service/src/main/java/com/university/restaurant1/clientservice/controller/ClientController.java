package com.university.restaurant1.clientservice.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.university.restaurant1.clientservice.dto.ClientRequest;
import com.university.restaurant1.clientservice.dto.ClientResponse;
import com.university.restaurant1.clientservice.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;




    @PostMapping
    public ClientResponse createClient(@RequestBody ClientRequest clientRequest) {
        return clientService.createClient(clientRequest);
    }

    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientResponse getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(id, clientRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
