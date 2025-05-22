package com.curso.services;

import com.curso.domains.Technician;
import com.curso.domains.User;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.domains.dtos.UserDTO;
import com.curso.repositories.UserRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<UserDTO> findAll() {
        return userRepo.findAll().stream()
                .map(obj -> new UserDTO(obj)).collect(Collectors.toList());
    }

    public User findById(Long id) {
        Optional<User> obj = userRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public User findByCpf(String cpf) {
        Optional<User> obj = userRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF:" + cpf));
    }

    public User findByEmail(String email) {
        Optional<User> obj = userRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! E-mail:" + email));
    }

    public User create(UserDTO objDto) {
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        User newObj = new User(objDto);
        return userRepo.save(newObj);
    }

    public User update(Long id, UserDTO objDto) {
        objDto.setId(id);
        User oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new User(objDto);
        return userRepo.save(oldObj);
    }

    public void delete(Long id) {
        User obj = findById(id);
        if (obj.getServiceOrders().size() > 0) {
            throw new DataIntegrityViolationException("User não pode ser deletado pois possui ordems de venda");
        }
        userRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(UserDTO objDto) {
        Optional<User> obj = userRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já está cadastrdo no sistema");
        }

        Optional<User> obj2 = userRepo.findByEmail(objDto.getEmail());
        if (obj2.isPresent() && obj2.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já está cadastrdo no sistema");
        }

    }
}
