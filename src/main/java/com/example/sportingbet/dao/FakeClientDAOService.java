package com.example.sportingbet.dao;

import com.example.sportingbet.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeClientDAOService implements ClientDao {
    private static List<Client> dataBase = new ArrayList<>();

    @Override
    public int insertClient(UUID id, Client client) {
        dataBase.add(new Client(id, client.getName(), client.getMoney()));
        return 1;
    }

    @Override
    public List<Client> selectAllClient() {
        return dataBase;
    }

    @Override
    public Optional<Client> selectClientById(UUID id) {
        return dataBase.stream().filter(client -> client.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteClientById(UUID id) {
        Optional<Client> deleteClient = selectClientById(id);
        if (deleteClient.isEmpty()) {
            return 0;
        }
        dataBase.remove(deleteClient.get());
        return 1;
    }

    @Override
    public int updateClientById(UUID id, Client updateClient) {
        return selectClientById(id)
                .map(client -> {
                    int indexOfClientToUpdate = dataBase.indexOf(client);
                    if (indexOfClientToUpdate >= 0) {
                        dataBase.set(indexOfClientToUpdate, new Client(id, updateClient.getName(), updateClient.getMoney()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


    @Override
    public double getClientMoneyById(UUID id) {
        Optional<Client> optionalClient = selectClientById(id);
        Client client = optionalClient.get();
        return client.getMoney();
    }

    @Override
    public Client updateClientMoneyById(UUID id, double money) {
        Optional<Client> optionalClient = selectClientById(id);
        Client client = optionalClient.get();
        return client.setMoney(money);
    }

}
