package com.curso.resources;

import com.curso.domains.Technician;
import com.curso.domains.User;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.domains.dtos.UserDTO;
import com.curso.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Listar todos os users",
            description = "Retorna uma lista com todos os users cadastrados")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um user por id",
            description = "Realizar a busca de um user cadastrado por id")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User obj = this.userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um user por cpf",
            description = "Realizar a busca de um user cadastrado por cpf")
    public ResponseEntity<UserDTO> findByCpf(@PathVariable String cpf){
        User obj = this.userService.findByCpf(cpf);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um user por email",
            description = "Realizar a busca de um user cadastrado por email")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        User obj = this.userService.findByEmail(email);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo user",
            description = "Criar um novo user com base nos dados fornecidos")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objOdto){
        User newObj = userService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um user",
            description = "Altera um user existente")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO objOdto){
        User Obj = userService.update(id, objOdto);
        return ResponseEntity.ok().body(new UserDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um user",
            description = "Remove um user apatir do seu id")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
