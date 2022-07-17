package br.com.lucas.petshopserviceuser.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.petshopserviceuser.model.Client;
import br.com.lucas.petshopserviceuser.repository.ClientRepository;
import br.com.lucas.petshopserviceuser.service.exception.ObjectNotFoundException;

 

@Service
public class ClientService {

   @Autowired
    private ClientRepository repo;

	public Client findById(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! id: " + id));
	}

    public Client salve(Client client) {

        return client;
    }

}
