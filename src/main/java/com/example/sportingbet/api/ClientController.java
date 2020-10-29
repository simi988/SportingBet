package com.example.sportingbet.api;

import com.example.sportingbet.model.Client;
import com.example.sportingbet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/client")
@RestController
public class ClientController {
    private static ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void addClient(@Valid @NotNull @RequestBody Client client) {
        clientService.addClient(client);
    }

    @GetMapping
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping(path = "{id}")
    public Client getClientById(@PathVariable("id") UUID id) {
        return clientService.getClientById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteClientById(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path = "{id}")
    public void updateClient(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Client clientToUpdate) {
        clientService.updateClient(id, clientToUpdate);
    }
}
