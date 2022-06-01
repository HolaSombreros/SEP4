package com.dai.model;

import javax.persistence.*;

@Entity
@Table(name = "barn")
public class Barn {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "barn_id")
    private int barnId;
    @Column(name = "name")
    private String name;

    public Barn() {
    }

    public Barn(String name) {
        this.name = name;
    }

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
