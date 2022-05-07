package com.dai.shared;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "barn")
public class Barn {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "barn_id")
    private int id;
    @Column(name = "name")
    private String name;

    public Barn() {
    }

    public Barn(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
