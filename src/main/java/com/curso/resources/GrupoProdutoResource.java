package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.services.GrupoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/grupoproduto")
@Tag(name = "Grupo de Produtos", description = "API para gerenciamento de Grupo de Produtos")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @GetMapping
    @Operation(summary = "Listar todos os Grupo de Produtos",
            description = "Retorna uma lista com todos os Grupo de Produtos cadastrados")
    public ResponseEntity<List<GrupoProdutoDTO>> findAll(){
        return ResponseEntity.ok(grupoProdutoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Grupo de Produto por id",
            description = "Realizar a busca de um Grupo de Produto cadastrado por id")
    public ResponseEntity<GrupoProdutoDTO> findById(@PathVariable Integer id){
        GrupoProduto obj = this.grupoProdutoService.findById(id);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Grupo de Produto",
            description = "Criar um novo Grupo de Produto com base nos dados fornecidos")
    public ResponseEntity<GrupoProdutoDTO> create(@Valid @RequestBody GrupoProdutoDTO objOdto){
        GrupoProduto newObj = grupoProdutoService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Grupo de Produto",
            description = "Altera um Grupo de Produto existente")
    public ResponseEntity<GrupoProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody GrupoProdutoDTO objDto){
        GrupoProduto Obj = grupoProdutoService.update(id, objDto);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Grupo de Produto",
            description = "Remove um Grupo de Produto apatir do seu id")
    public ResponseEntity<GrupoProdutoDTO> delete(@PathVariable Integer id){
        grupoProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
