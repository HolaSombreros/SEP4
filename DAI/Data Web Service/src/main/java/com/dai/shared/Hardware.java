package com.dai.shared;

import javax.persistence.*;

@Entity
@Table(name = "hardware")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
