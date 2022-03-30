package com.dai.model;

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
}
