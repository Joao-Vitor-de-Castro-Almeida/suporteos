package com.curso.domains.dtos;

import com.curso.domains.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ServiceOrderDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate starDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @NotNull(message = "O campo Titulo é requirido")
    private String titleOS;

    @NotNull(message = "O campo descriçâo é requirido")
    private String description;

    @NotNull(message = "O campo Prioridade é requirido")
    private Integer orderPriority;

    @NotNull(message = "O campo Status é requirido")
    private Integer orderStatus;

    @NotNull(message = "O campo technician é requirido")
    private Long technician;

    @NotNull(message = "O campo user é requirido")
    private Long user;

    private String nomeTechnician;
    private String nomeUser;

    public ServiceOrderDTO() {
    }

    public ServiceOrderDTO(ServiceOrder serviceOrder) {
        this.id = serviceOrder.getId();
        this.starDate = serviceOrder.getStarDate();
        this.endDate = serviceOrder.getEndDate();
        this.titleOS = serviceOrder.getTitleOS();
        this.description = serviceOrder.getDescription();
        this.orderPriority = serviceOrder.getOrderPriority().getId();
        this.orderStatus = serviceOrder.getOrderStatus().getId();
        this.technician = serviceOrder.getTechnician().getId();
        this.user = serviceOrder.getUser().getId();
        this.nomeTechnician = serviceOrder.getTechnician().getPrimeiroNome()+" "+ serviceOrder.getTechnician().getSegundoNome();
        this.nomeUser = serviceOrder.getUser().getPrimeiroNome()+" "+ serviceOrder.getUser().getSegundoNome();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotNull(message = "O campo Titulo é requirido") String getTitleOS() {
        return titleOS;
    }

    public void setTitleOS(@NotNull(message = "O campo Titulo é requirido") String titleOS) {
        this.titleOS = titleOS;
    }

    public @NotNull(message = "O campo descriçâo é requirido") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "O campo descriçâo é requirido") String description) {
        this.description = description;
    }

    public @NotNull(message = "O campo Prioridade é requirido") Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(@NotNull(message = "O campo Prioridade é requirido") Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public @NotNull(message = "O campo Status é requirido") Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@NotNull(message = "O campo Status é requirido") Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public @NotNull(message = "O campo technician é requirido") Long getTechnician() {
        return technician;
    }

    public void setTechnician(@NotNull(message = "O campo technician é requirido") Long technician) {
        this.technician = technician;
    }

    public @NotNull(message = "O campo user é requirido") Long getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "O campo user é requirido") Long user) {
        this.user = user;
    }

    public String getNomeTechnician() {
        return nomeTechnician;
    }

    public void setNomeTechnician(String nomeTechnician) {
        this.nomeTechnician = nomeTechnician;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }
}
