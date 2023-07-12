package com.tablelandia.spring.tablestructure.controller;

import com.tablelandia.spring.tablestructure.dto.ClienteDTO;
import com.tablelandia.spring.tablestructure.mapper.DozerMapper;
import com.tablelandia.spring.tablestructure.model.Cliente;
import com.tablelandia.spring.tablestructure.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = DozerMapper.parseObject(clienteDTO, Cliente.class);
        ClienteDTO novoCliente = clienteService.createCliente(cliente);
        ClienteDTO novoClienteDTO = DozerMapper.parseObject(novoCliente, ClienteDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoClienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = DozerMapper.parseObject(clienteDTO, Cliente.class);
        Cliente clienteAtualizado = clienteService.updateCliente(id, cliente);
        if (clienteAtualizado != null) {
            ClienteDTO clienteAtualizadoDTO = DozerMapper.parseObject(clienteAtualizado, ClienteDTO.class);
            return ResponseEntity.ok(clienteAtualizadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        boolean clienteExcluido = clienteService.deleteCliente(id);
        if (clienteExcluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.getAllClientes();
        List<ClienteDTO> clientesDTO = DozerMapper.parseListObject(clientes, ClienteDTO.class);
        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            ClienteDTO clienteDTO = DozerMapper.parseObject(cliente, ClienteDTO.class);
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

