package com.curso.resources;

import com.curso.domains.ServiceOrder;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.services.ServiceOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/serviceOrder")
@Tag(name = "serviceOrder", description = "API para gerenciamento de serviceOrder")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Service Order por id",
            description = "Realizar a busca de um Service Order cadastrado por id")
    public ResponseEntity<ServiceOrderDTO> findById(@PathVariable UUID id){
        ServiceOrder obj = this.serviceOrderService.findById(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Listar todos os Service Orders",
            description = "Retorna uma lista com todos os Service Orders cadastrados")
    public ResponseEntity<List<ServiceOrderDTO>> findAll(){
        return ResponseEntity.ok().body(serviceOrderService.findAll());
    }

    @PostMapping
    @Operation(summary = "Criar um nova venda",
            description = "Criar um nova venda com base nos dados fornecidos")
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder newObj = serviceOrderService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um venda",
            description = "Altera um venda existente")
    public ResponseEntity<ServiceOrderDTO> update(@PathVariable UUID id,@Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder Obj = serviceOrderService.update(id, objDto);
        return ResponseEntity.ok().body(new ServiceOrderDTO(Obj));
    }

}
