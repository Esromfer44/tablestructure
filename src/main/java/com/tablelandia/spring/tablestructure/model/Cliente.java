package com.tablelandia.spring.tablestructure.model;



import com.tablelandia.spring.tablestructure.dto.ClienteDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente extends ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;

    @ManyToMany(mappedBy = "clientes")
    private Set<Restaurante> restaurantes = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public void adicionarRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
        restaurante.getClientes().add(this);
    }

    public void removerRestaurante(Restaurante restaurante) {
        restaurantes.remove(restaurante);
        restaurante.getClientes().remove(this);
    }

    // Getters e setters

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
