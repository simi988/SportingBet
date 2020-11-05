package com.example.sportingbet.dao;

import com.example.sportingbet.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDao {
    int insertClient(UUID id, Client client);

    default int insertClient(Client client) {
        UUID id = UUID.randomUUID();
        return insertClient(id, client);
    }

    List<Client> selectAllClient();

    Optional<Client> selectClientById(UUID id);

    int deleteClientById(UUID id);

    int updateClientById(UUID id, Client client);

    double getClientMoneyById(UUID id);

    Client updateClientMoneyById(UUID id, double money);
}
