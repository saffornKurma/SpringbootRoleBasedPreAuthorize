package com.security.rolebasedannotations.security;


import jakarta.persistence.*;

@Entity
@Table(name="user_permission")
public class UserPermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;//e.g, ORDER_NAME ,SALES_READ


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
