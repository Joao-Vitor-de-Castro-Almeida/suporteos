package com.curso.domains;

import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Pessoa")
    @SequenceGenerator(name = "seq_Pessoa", sequenceName = "seq_Pessoa", allocationSize = 1)
    protected Long id;

    protected String primeiroNome;

    protected String segundoNome;

    @Column(unique = true)
    protected String cpf;

    @Column(unique = true)
    protected String email;

    protected String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createAt = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "person_type")
    protected Set<Integer> personType = new HashSet<>();

    public Pessoa() {
        addTipoPessoa(PersonType.USER);
    }

    public Pessoa(Long id, String primeiroNome, String segundoNome, String CPF,
                  String email, String senha) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.cpf = CPF;
        this.email = email;
        this.senha = senha;
        addTipoPessoa(PersonType.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Set<PersonType> getPersonType() {
        return personType.stream().map(PersonType::toEnum).collect(Collectors.toSet());
    }

    public void addTipoPessoa(PersonType tipoPessoa) {
        this.personType.add(tipoPessoa.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
