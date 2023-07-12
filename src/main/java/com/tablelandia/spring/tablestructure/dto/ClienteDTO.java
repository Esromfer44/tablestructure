package com.tablelandia.spring.tablestructure.dto;

import com.tablelandia.spring.tablestructure.model.Restaurante;

import java.util.HashSet;
import java.util.Set;


public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Set<Restaurante> restaurantes = new HashSet<>();

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String telefone, String email, Set<Restaurante> restaurantes) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.restaurantes = restaurantes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(Set<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
