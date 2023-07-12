package com.tablelandia.spring.tablestructure.model;


import com.tablelandia.spring.tablestructure.dto.RestauranteDTO;
import com.tablelandia.spring.tablestructure.mapper.DozerMapper;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private int capacidadeMesas;

    @ManyToMany
    @JoinTable(
            name = "restaurante_mesa",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "mesa_id")
    )
    private Set<Mesa> mesas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "restaurante_clientes",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private Collection<Cliente> clientes = new ArrayList<>();

    public Restaurante() {
    }

    public Restaurante(String nome, String endereco, String tipoCozinha, int capacidadeMesas) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.capacidadeMesas = capacidadeMesas;
    }

    public void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
        mesa.getRestaurantes().add(this);
    }

    public void removerMesa(Mesa mesa) {
        mesas.remove(mesa);
        mesa.getRestaurantes().remove(this);
    }

    public RestauranteDTO toDTO() {
        return DozerMapper.parseObject(this, RestauranteDTO.class);
    }

    public static Restaurante fromDTO(RestauranteDTO restauranteDTO) {
        return DozerMapper.parseObject(restauranteDTO, Restaurante.class);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public int getCapacidadeMesas() {
        return capacidadeMesas;
    }

    public void setCapacidadeMesas(int capacidadeMesas) {
        this.capacidadeMesas = capacidadeMesas;
    }

    public Set<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Set<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }
}
