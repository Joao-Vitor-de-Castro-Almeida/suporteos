package com.curso.domains;

import com.curso.domains.dtos.TechnicianDTO;
import com.curso.domains.dtos.UserDTO;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public User(Long id, String primeiroNome, String segundoNome, String CPF, String email, String senha) {
        super(id, primeiroNome, segundoNome, CPF, email, senha);
        addTipoPessoa(PersonType.USER);
    }

    public User(UserDTO obj) {
        this.id = obj.getId();
        this.primeiroNome = obj.getPrimeiroNome();
        this.segundoNome = obj.getSegundoNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.createAt = obj.getCreatedAt();
        this.personType = obj.getPersonType().stream().map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(PersonType.USER);
    }

    public User(){
        super();
        addTipoPessoa(PersonType.USER);
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}
