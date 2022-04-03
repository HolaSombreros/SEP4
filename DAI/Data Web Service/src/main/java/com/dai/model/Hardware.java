package com.dai.model;

import javax.persistence.*;

@Entity
@Table(name = "entity")
public class Hardware {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "hardware_id")
    private int id;
    @Column(name = "port_number")
    private int portNumber;

    protected Hardware() {
    }

    public Hardware(int portNumber) {
        this.portNumber = portNumber;
    }
}
