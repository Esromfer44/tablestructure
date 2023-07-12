package com.tablelandia.spring.tablestructure.service;

import com.tablelandia.spring.tablestructure.dto.ClienteDTO;
import com.tablelandia.spring.tablestructure.mapper.DozerMapper;
import com.tablelandia.spring.tablestructure.model.Cliente;
import com.tablelandia.spring.tablestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return DozerMapper.parseListObject(clientes, ClienteDTO.class);
    }

    public ClienteDTO getClienteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(cliente -> DozerMapper.parseObject(cliente, ClienteDTO.class)).orElse(null);
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = DozerMapper.parseObject(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return DozerMapper.parseObject(cliente, ClienteDTO.class);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.getNome());
            clienteRepository.save(cliente);
            return clienteDTO;
        }
        return null;
    }

    public boolean deleteCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
