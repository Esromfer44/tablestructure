package com.tablelandia.spring.tablestructure.service;

import com.tablelandia.spring.tablestructure.dto.MesaDTO;
import com.tablelandia.spring.tablestructure.mapper.DozerMapper;
import com.tablelandia.spring.tablestructure.model.Mesa;
import com.tablelandia.spring.tablestructure.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;

    @Autowired
    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<MesaDTO> getAllMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return DozerMapper.parseListObject(mesas, MesaDTO.class);
    }

    public MesaDTO getMesaById(Long id) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        return mesaOptional.map(mesa -> DozerMapper.parseObject(mesa, MesaDTO.class)).orElse(null);
    }

    public MesaDTO createMesa(MesaDTO mesaDTO) {
        Mesa mesa = DozerMapper.parseObject(mesaDTO, Mesa.class);
        mesa = mesaRepository.save(mesa);
        return DozerMapper.parseObject(mesa, MesaDTO.class);
    }
}
