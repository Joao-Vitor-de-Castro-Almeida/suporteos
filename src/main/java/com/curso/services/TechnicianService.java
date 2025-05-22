package com.curso.services;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.repositories.TechnicianRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepo;

    public List<TechnicianDTO> findAll() {
        return technicianRepo.findAll().stream()
                .map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
    }

    public Technician findById(Long id) {
        Optional<Technician> obj = technicianRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public Technician findByCpf(String cpf) {
        Optional<Technician> obj = technicianRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF:" + cpf));
    }

    public Technician findByEmail(String email) {
        Optional<Technician> obj = technicianRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! E-mail:" + email));
    }

    public Technician create(TechnicianDTO objDto) {
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Technician newObj = new Technician(objDto);
        return technicianRepo.save(newObj);
    }

    public Technician update(Long id, TechnicianDTO objDto) {
        objDto.setId(id);
        Technician oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Technician(objDto);
        return technicianRepo.save(oldObj);
    }

    public void delete(Long id) {
        Technician obj = findById(id);
        if (obj.getServiceOrders().size() > 0) {
            throw new DataIntegrityViolationException("Technician não pode ser deletado pois possui ordems de venda");
        }
        technicianRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(TechnicianDTO objDto) {
        Optional<Technician> obj = technicianRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já está cadastrdo no sistema");
        }

        Optional<Technician> obj2 = technicianRepo.findByEmail(objDto.getEmail());
        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já está cadastrdo no sistema");
        }

    }
}