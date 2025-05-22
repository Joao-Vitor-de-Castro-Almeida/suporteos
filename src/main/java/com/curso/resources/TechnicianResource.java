package com.curso.resources;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.services.TechnicianService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

    @Autowired
    private TechnicianService TechnicianService;

    @GetMapping
    @Operation(summary = "Listar todos os technicians",
            description = "Retorna uma lista com todos os technicians cadastrados")
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        return ResponseEntity.ok().body(TechnicianService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um technician por id",
            description = "Realizar a busca de um technician cadastrado por id")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id){
        Technician obj = this.TechnicianService.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um technician por cpf",
            description = "Realizar a busca de um technician cadastrado por cpf")
    public ResponseEntity<TechnicianDTO> findByCpf(@PathVariable String cpf){
        Technician obj = this.TechnicianService.findByCpf(cpf);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um technician por email",
            description = "Realizar a busca de um technician cadastrado por email")
    public ResponseEntity<TechnicianDTO> findByEmail(@PathVariable String email){
        Technician obj = this.TechnicianService.findByEmail(email);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo technician",
            description = "Criar um novo technician com base nos dados fornecidos")
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objOdto){
        Technician newObj = TechnicianService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um technician",
            description = "Altera um technician existente")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Long id, @Valid @RequestBody TechnicianDTO objOdto){
        Technician Obj = TechnicianService.update(id, objOdto);
        return ResponseEntity.ok().body(new TechnicianDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um technician",
            description = "Remove um technician apatir do seu id")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Long id){
        TechnicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
