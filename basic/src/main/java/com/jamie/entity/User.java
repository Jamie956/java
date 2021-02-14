package com.jamie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -3307269962764425802L;
    private Integer id;
    private String name;
    public Address address;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Address ad) {
        this.address = ad;
    }
}
