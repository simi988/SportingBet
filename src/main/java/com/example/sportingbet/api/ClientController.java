package com.example.sportingbet.api;

import com.example.sportingbet.model.Client;
import com.example.sportingbet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/client")
@RestController
public class ClientController {
    private static ClientService clientService;

@Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void insertClient(@Valid @NonNull @RequestBody Client client) {
        clientService.insertClient(client);
    }

    @GetMapping
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping(path = "all/{id}")
    public Client getClientById(@PathVariable("id") UUID id) {
        return clientService.getClientById(id)
                .orElse(null);
    }

   @GetMapping(path = "{id}")
    public double getClientMoneyById(@PathVariable("id") UUID id){
        return clientService.getClientMoneyById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteClientById(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path = "{id}")
    public void updateClient(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Client clientToUpdate) {
        clientService.updateClient(id, clientToUpdate);
    }
    @PutMapping(path = "{id}/{money}")
    public void updateClientMoneyById (@PathVariable("id") UUID id, @PathVariable("money") double money){
    clientService.updateClientMoneyById(id, money);
    }

}
