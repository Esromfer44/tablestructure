package com.tablelandia.spring.tablestructure.repository;


import com.tablelandia.spring.tablestructure.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}

