package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.Produto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.domains.dtos.ProdutoDTO;
import com.curso.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
@Tag(name = "Produtos", description = "API para gerenciamento de Produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar todos os Produtos",
            description = "Retorna uma lista com todos os Produtos cadastrados")
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Produto por id",
            description = "Realizar a busca de um Produto cadastrado por id")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        Produto obj = this.produtoService.findById(id);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @GetMapping(value = "/codigobarra/{codigoBarra}")
    @Operation(summary = "Buscar um Produto por codigoBarra",
            description = "Realizar a busca de um Produto cadastrado por codigoBarra")
    public ResponseEntity<ProdutoDTO> findByCodigoBarra(@PathVariable String codigoBarra){
        Produto obj = this.produtoService.findByCodigoBarra(codigoBarra);
        return ResponseEntity.ok().body(new ProdutoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Produto",
            description = "Criar um novo Produto com base nos dados fornecidos")
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO dto){
        Produto produto = produtoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getIdProduto()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Produto",
            description = "Altera um Produto existente")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO objDto){
        Produto Obj = produtoService.update(id, objDto);
        return ResponseEntity.ok().body(new ProdutoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Produto",
            description = "Remove um Produto apatir do seu id")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
