package com.dai.shared;

import javax.persistence.*;

@Entity
@Table(name = "barn")
public class Barn {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "barn_id")
    private int id;
    @Column(name = "name")
    private String name;

    public Barn() {
    }

    public Barn(int id, String name) {
        this.id = id;
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
