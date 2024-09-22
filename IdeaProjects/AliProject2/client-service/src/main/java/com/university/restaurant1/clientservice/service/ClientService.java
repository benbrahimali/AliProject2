package com.university.restaurant1.clientservice.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.university.restaurant1.clientservice.dto.ClientRequest;
import com.university.restaurant1.clientservice.dto.ClientResponse;
import com.university.restaurant1.clientservice.model.Client;
import com.university.restaurant1.clientservice.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;







    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        client.setPhoneNumber(clientRequest.getPhoneNumber());

        Client savedClient = clientRepository.save(client);
        return convertToClientResponse(savedClient);
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToClientResponse)
                .collect(Collectors.toList());
    }

    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        return convertToClientResponse(client);
    }

    public ClientResponse updateClient(Long id, ClientRequest clientRequest) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        client.setPhoneNumber(clientRequest.getPhoneNumber());

        Client updatedClient = clientRepository.save(client);
        return convertToClientResponse(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        clientRepository.delete(client);
    }

    private ClientResponse convertToClientResponse(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getEmail(), client.getPhoneNumber());
    }
}
