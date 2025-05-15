package com.curso.domains.enums;

public enum OrderStatus {

    OPEN(0,"ROLE_OPEN"), PROGRESS(1,"ROLE_PROGRESS"),
    CLOSED(2,"ROLE_CLOSED");

    private Integer id;
    private String personType;

    OrderStatus(Integer id, String personType) {
        this.id = id;
        this.personType = personType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public static OrderStatus toEnum(Integer id){
        if(id==null) return null;
        for(OrderStatus x : OrderStatus.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("OrderStatus Invalido");
    }
}
