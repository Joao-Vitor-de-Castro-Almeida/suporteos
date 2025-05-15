package com.curso.domains.enums;

public enum OrderPriority {

    LOW(0,"ROLE_LOW"),MEDIUM(1,"ROLE_MEDIUM"),
    HIGH(2,"ROLE_HIGH");

    private Integer id;
    private String personType;

    OrderPriority(Integer id, String personType) {
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

    public static OrderPriority toEnum(Integer id){
        if(id==null) return null;
        for(OrderPriority x : OrderPriority.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("OrderPriority Invalido");
    }
}
